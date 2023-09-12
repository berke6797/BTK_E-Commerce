package com.btk.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSaveRequestDto {
    @NotBlank(message = "Ürün ismi boş bırakılamaz!!")
    private String productName;
    @NotNull(message = "Ürün fiyatı boş bırakılamaz!!")
    private Double price;
    private String description;
    @NotNull(message = "Kategori ekleyiniz.")
    private List<String> categoryIds;
    @NotBlank(message = "Lütfen ürün için marka seçimi yapınız!!")
    private String brandId;
    private List<String> photoImages;
}
