package com.btk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@SuperBuilder
@Data
@NoArgsConstructor
@Document
public class Brand extends Base{
    @Id
    private String brandId;
    private String brandName;
}
