package com.btk.mapper;

import com.btk.dto.request.AddProductToBasketRequestDto;
import com.btk.dto.request.UpdateBasketRequestDto;
import com.btk.entity.Basket;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBasketMapper {

    IBasketMapper INSTANCE = Mappers.getMapper(IBasketMapper.class);

    Basket addProductToBasketToBasket (AddProductToBasketRequestDto dto);
}
