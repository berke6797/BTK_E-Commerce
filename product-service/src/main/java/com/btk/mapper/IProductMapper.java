package com.btk.mapper;

import com.btk.dto.request.ProductSaveRequestDto;
import com.btk.dto.request.ProductUpdateRequestDto;
import com.btk.dto.response.GetProductDescriptionsFromProductServiceResponseDto;
import com.btk.dto.response.ProductSaveResponseDto;
import com.btk.dto.response.ProductUpdateResponseDto;
import com.btk.entity.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IProductMapper {
    IProductMapper INSTANCE= Mappers.getMapper(IProductMapper.class);

    Product toProductFromSaveDto(final ProductSaveRequestDto productSaveRequestDto);
    ProductSaveResponseDto toSaveDtoFromProduct(final Product product);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product updateFromDtoToProduct(final ProductUpdateRequestDto dto, @MappingTarget Product product);
    ProductUpdateResponseDto toUpdateDtoFromProduct(final Product product);
    GetProductDescriptionsFromProductServiceResponseDto toGetProductDescriptionsFromProductServiceResponseDtoFromProduct(final Product product);
    List<GetProductDescriptionsFromProductServiceResponseDto> toListGetProductsDescriptionFromProductList(final List<Product> productList );

}
