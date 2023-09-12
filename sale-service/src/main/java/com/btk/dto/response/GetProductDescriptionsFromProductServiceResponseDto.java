package com.btk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProductDescriptionsFromProductServiceResponseDto {
    private String productName;
    private Double price;
    private String description;
   private List<String> photoImages;

}
