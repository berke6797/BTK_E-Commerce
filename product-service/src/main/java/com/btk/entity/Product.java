package com.btk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@SuperBuilder
@Data
@NoArgsConstructor
@Document
public class Product extends Base {
    @Id
    private String productId;
    private String productName;
    private Double price;
    private String description;
    private List<String> categoryIds;
    private String brandId;
    private List<String> photoImages;
}
