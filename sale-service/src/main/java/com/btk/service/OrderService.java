package com.btk.service;


import com.btk.dto.request.TotalPriceRequestDto;
import com.btk.dto.response.GetProductDescriptionsFromProductServiceResponseDto;
import com.btk.entity.Balance;
import com.btk.entity.Basket;
import com.btk.entity.Order;
import com.btk.entity.enums.ERole;
import com.btk.entity.enums.EStatus;
import com.btk.exception.ErrorType;
import com.btk.exception.SaleManagerException;
import com.btk.manager.IProductManager;
import com.btk.manager.IUserManager;
import com.btk.repository.IOrderRepository;
import com.btk.utility.CodeGenerator;
import com.btk.utility.JwtTokenProvider;
import com.btk.utility.ServiceManager;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService extends ServiceManager<Order, String> {

    private final IOrderRepository orderRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BasketService basketService;
    private final BalanceService balanceService;
    private final IUserManager userManager;
    private final IProductManager productManager;


    public OrderService(IOrderRepository orderRepository, JwtTokenProvider jwtTokenProvider, BasketService basketService, BalanceService balanceService, IUserManager userManager, IProductManager productManager) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.basketService = basketService;
        this.balanceService = balanceService;
        this.userManager = userManager;
        this.productManager = productManager;
    }


    @Transactional
    public String createOrder(String balanceId, String token) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        String userId = userManager.findByAuthId(authId.get()).getBody();

        List<String> roles = jwtTokenProvider.getRoleFromToken(token);
        Optional<Balance> optionalBalance = balanceService.findById(balanceId);
        if (!roles.contains(ERole.USER.toString())) {
            throw new SaleManagerException(ErrorType.INVALID_ROLE);
        } else {
            Optional<Basket> optionalBasket = basketService.getBasketIdByUserId(userId);
            Order order = Order.builder()
                    .balanceId(balanceId)
                    .basketId(optionalBasket.get().getBasketId())
                    .orderNumber(CodeGenerator.generateCode()).build();
            Double totalPriceInBasket = basketService.totalPriceInBasket(
                    token,
                    TotalPriceRequestDto.builder().basketId(optionalBasket.get().getBasketId()).build());
            if (optionalBalance.get().getAmountOfBalance() >= totalPriceInBasket) {
                save(order);
                Double newBalance = optionalBalance.get().getAmountOfBalance() - totalPriceInBasket;
                optionalBalance.get().setAmountOfBalance(newBalance);
                balanceService.update(optionalBalance.get());
                optionalBasket.get().setStatus(EStatus.PASSIVE);
                basketService.update(optionalBasket.get());
            } else {
                throw new SaleManagerException(ErrorType.INSUFFICIENT_BALANCE);
            }
            return "Sipariş numaranız: " + order.getOrderNumber() + "\nKalan bakiyeniz : " + optionalBalance.get().getAmountOfBalance();

        }


    }

    public List<GetProductDescriptionsFromProductServiceResponseDto> showHistoryOfOrders(String token) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        String userId = userManager.findByAuthId(authId.get()).getBody();
        List<String> roles = jwtTokenProvider.getRoleFromToken(token);
        List<String> productIdList = new ArrayList<>();
        List<Basket> historyBasketList = basketService.getHistoryBasketList(userId);

        if (!roles.contains(ERole.USER.toString())) {
            throw new SaleManagerException(ErrorType.INVALID_ROLE);
        } else {
            historyBasketList
                    .forEach(historyBasket -> {
                        historyBasket.getProductIds().forEach(productId -> {
                            productIdList.add(productId);
                        });
                    });
            // FlatMap kullanılabilirdi.İç içe listelerde kullanılır.
            List<GetProductDescriptionsFromProductServiceResponseDto> productsDetails = productIdList.stream().map(productId -> {
                GetProductDescriptionsFromProductServiceResponseDto productDetails = productManager.findDescriptionsByProductId(productId).getBody();
                return productDetails;
            }).collect(Collectors.toList());
            return productsDetails;
        }
    }


        public List<GetProductDescriptionsFromProductServiceResponseDto> filterHistoryOfOrdersWithDate(String token, String date1, String date2) throws ParseException {
            Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
            String userId = userManager.findByAuthId(authId.get()).getBody();
            List<String> roles = jwtTokenProvider.getRoleFromToken(token);
            List<GetProductDescriptionsFromProductServiceResponseDto> filteredProductList = new ArrayList<>();
            //değiştirmek yerine gelen basket verileri içindn filtreleme yaptık
            List<Basket> historyBasketList = basketService.getHistoryBasketList(userId);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date firstDate = formatter.parse(date1);
            Date secondDate = formatter.parse(date2);
            Long firstDateInLong = firstDate.getTime();
            Long secondDateInLong = secondDate.getTime();
            if (!roles.contains(ERole.USER.toString())) {
                throw new SaleManagerException(ErrorType.INVALID_ROLE);
            } else {
                List<Basket> filteredBaskets = historyBasketList.stream()
                        .filter(basket -> {
                            Long basketDate = basket.getUpdatedDate();
                            return basketDate >= firstDateInLong && basketDate <= secondDateInLong;
                        })
                        .collect(Collectors.toList());
                filteredBaskets.stream().flatMap(baskets -> baskets.getProductIds()
                        .stream().map(productId -> filteredProductList.add(productManager.findDescriptionsByProductId(productId).getBody())))
                        .collect(Collectors.toList());
                return filteredProductList;

            }
        }


}


