package com.btk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSaveResponseDto {

    private String productName;
    private Double price;
    private String description;
    private List<String> categoryIds;
    private String brandId;
    private List<String> photoImages;
}
