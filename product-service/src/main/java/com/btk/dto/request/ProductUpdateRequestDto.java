package com.btk.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateRequestDto {
    @NotBlank(message = "Product id boş bırakılamaz!!")
    private String productId;
    private String productName;
    private Double price;
    private String description;
    private List<String> categoryIds;
    private String brandId;
    private List<String> photoImages;
}
