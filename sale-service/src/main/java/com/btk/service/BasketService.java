package com.btk.service;

import com.btk.dto.request.AddProductToBasketRequestDto;
import com.btk.dto.request.DeleteProductFromBasketRequestDto;
import com.btk.dto.request.TotalPriceRequestDto;
import com.btk.dto.request.UpdateBasketRequestDto;
import com.btk.dto.response.GetProductDescriptionsFromProductServiceResponseDto;
import com.btk.entity.Basket;
import com.btk.entity.enums.ERole;
import com.btk.entity.enums.EStatus;
import com.btk.exception.ErrorType;
import com.btk.exception.SaleManagerException;
import com.btk.manager.IProductManager;
import com.btk.manager.IUserManager;
import com.btk.mapper.IBasketMapper;
import com.btk.repository.IBasketRepository;
import com.btk.utility.JwtTokenProvider;
import com.btk.utility.ServiceManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BasketService extends ServiceManager<Basket, String> {
    private final IBasketRepository basketRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final IUserManager userManager;
    private final IProductManager productManager;

    public BasketService(IBasketRepository basketRepository, JwtTokenProvider jwtTokenProvider, IUserManager userManager, IProductManager productManager) {
        super(basketRepository);
        this.basketRepository = basketRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userManager = userManager;
        this.productManager = productManager;
    }
    @Transactional
    public Boolean addProductToBasket(String token, AddProductToBasketRequestDto dto) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new SaleManagerException(ErrorType.INVALID_TOKEN);
        }
        String user = userManager.findByAuthId(authId.get()).getBody();
        List<String> roles = jwtTokenProvider.getRoleFromToken(token);
        if (roles.contains(ERole.USER.toString())) {
            Basket basket = IBasketMapper.INSTANCE.addProductToBasketToBasket(dto);
            basket.setUserId(user);
            save(basket);
            return true;
        } else {
            throw new SaleManagerException(ErrorType.INVALID_ROLE);
        }
    }

    //@Cacheable(value = "findBasketForUser")
    @Transactional(readOnly = true)
    public List<GetProductDescriptionsFromProductServiceResponseDto> findBasketForUser(String token){

        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new SaleManagerException(ErrorType.INVALID_TOKEN);
        }
        List<String> roles = jwtTokenProvider.getRoleFromToken(token);
        if (roles.contains(ERole.USER.toString())) {
            String userId = userManager.findByAuthId(authId.get()).getBody();
            Optional<Basket> basket = basketRepository.findOptionalByUserId(userId);
            if (basket.isEmpty()) {
                throw new SaleManagerException(ErrorType.HAS_NOT_ACTIVE_BASKET);
            }
            if (basket.get().getStatus().equals(EStatus.ACTIVE)) {
                List<GetProductDescriptionsFromProductServiceResponseDto> productDescriptions = basket.get().getProductIds().stream()
                        .map(productId -> productManager.findDescriptionsByProductId(productId).getBody())
                        .collect(Collectors.toList());
                return productDescriptions;
            } else {
                throw new SaleManagerException(ErrorType.HAS_NOT_ACTIVE_BASKET);
            }
        } else {
            throw new SaleManagerException(ErrorType.INVALID_ROLE);
        }
    }

    @Transactional
    public Double totalPriceInBasket(String token, TotalPriceRequestDto dto){

        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new SaleManagerException(ErrorType.INVALID_TOKEN);
        }
        List<String> roles = jwtTokenProvider.getRoleFromToken(token);
        if (roles.contains(ERole.USER.toString())) {
            Basket basket = basketRepository.findOptionalByBasketId(dto.getBasketId()).get();
            List<String> productIds = basket.getProductIds();
            Double totalPrice = productIds.stream()
                    .map(productId -> productManager.findPriceByProductId(productId).getBody())
                    .mapToDouble(Double::doubleValue)
                    .sum();
            return totalPrice;
        }
        return 0.0;
    }

    @Transactional
    public List<GetProductDescriptionsFromProductServiceResponseDto> updateBasket(String token, UpdateBasketRequestDto dto){

        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new SaleManagerException(ErrorType.INVALID_TOKEN);
        }

        List<String> roles = jwtTokenProvider.getRoleFromToken(token);
        if (roles.contains(ERole.USER.toString())) {
            Basket basket = findById(dto.getBasketId()).get();

            List<String> newProductIds = dto.getProductIds();
            basket.getProductIds().addAll(newProductIds);
            update(basket);
            List<GetProductDescriptionsFromProductServiceResponseDto> productDescriptions = basket.getProductIds().stream()
                    .map(productId -> productManager.findDescriptionsByProductId(productId).getBody())
                    .collect(Collectors.toList());
            return productDescriptions;
        } else {
            throw new SaleManagerException(ErrorType.INVALID_ROLE);
        }

        }
    @Transactional
    public List<GetProductDescriptionsFromProductServiceResponseDto> deleteProductFromBasket(String token, DeleteProductFromBasketRequestDto dto) {

        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new SaleManagerException(ErrorType.INVALID_TOKEN);
        }
        List<String> roles = jwtTokenProvider.getRoleFromToken(token);
        if (roles.contains(ERole.USER.toString())) {
            Basket basket = findById(dto.getBasketId()).get();
            basket.getProductIds().removeAll(dto.getProductIds());
            update(basket);
            List<GetProductDescriptionsFromProductServiceResponseDto> productDescriptions = basket.getProductIds().stream()
                    .map(productId -> productManager.findDescriptionsByProductId(productId).getBody())
                    .collect(Collectors.toList());
            return productDescriptions;
        } else {
            throw new SaleManagerException(ErrorType.INVALID_ROLE);
        }
    }

    public Optional<Basket> getBasketIdByUserId(String userId) {
        Optional<Basket> optionalBasket = basketRepository.findOptionalByUserIdAndStatus(userId,EStatus.ACTIVE);
        if(optionalBasket.isEmpty()){
            throw new SaleManagerException(ErrorType.HAS_NOT_ACTIVE_BASKET);
        }
        return optionalBasket;
    }
    public List<Basket> getHistoryBasketList(String userId){
        Optional<List<Basket>> historyOfBasket= basketRepository.findAllByUserIdAndStatus(userId,EStatus.PASSIVE);
        if (historyOfBasket.isEmpty()){
            throw new SaleManagerException(ErrorType.HAS_NOT_PASSIVE_BASKET);
        }
        return historyOfBasket.get();
    }

}
