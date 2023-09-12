package com.btk.entity;

import com.btk.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Basket extends Base {
    @Id
    private String basketId;
    private String userId;
    private List<String> productIds; //?
    //private Double totalPrice; //Şuanlık yorumda kalsın.
    @Builder.Default
    private EStatus status = EStatus.ACTIVE;
}
