package com.btk.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddProductToBasketRequestDto {
    @NotNull(message = "Eklemek istediğiniz ürünlerin id'si boş olamaz")
    private List<String> productIds;


}
