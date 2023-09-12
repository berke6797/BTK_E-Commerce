package com.btk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@SuperBuilder
@Data
@NoArgsConstructor
public class Base {
    private Long createdDate;
    private Long updatedDate;
}
