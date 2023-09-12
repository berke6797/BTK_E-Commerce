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
public class Order extends Base {
    @Id
    private String orderId;
    private String basketId;
    private String balanceId;
    private String orderNumber;
}
