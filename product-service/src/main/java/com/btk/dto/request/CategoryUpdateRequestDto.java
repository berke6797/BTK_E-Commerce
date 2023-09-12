package com.btk.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateRequestDto {
    @NotBlank(message = "Category id boş bırakılamaz!!")
    private String categoryId;
    @NotBlank(message = "Category name boş bırakılamaz!!")
    private String categoryName;

}
