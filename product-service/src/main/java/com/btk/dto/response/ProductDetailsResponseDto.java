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
public class ProductDetailsResponseDto {
    private String productName;
    private List<String> categoryName;
    private String brandName;
    private Double price;
    private List<String> photoImages;
    private String description;
}
