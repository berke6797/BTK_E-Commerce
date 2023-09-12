package com.btk.entity;

import com.btk.entity.enums.ERole;
import com.btk.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class UserProfile extends Base{
    @Id
    private String userId;
    private Long authId;
    private String password;
    @Indexed(unique = true)
    private String email;
    private String name;
    private String middleName;
    private String surname;
    private List<ERole> role = new ArrayList<>();
    private EStatus status ;
}
