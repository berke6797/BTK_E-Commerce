package com.btk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Balance extends Base {
    @Id
    private String balanceId;
    private Double amountOfBalance;
    private String userId;
}
