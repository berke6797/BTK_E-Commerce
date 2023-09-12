package com.btk.entity;

import com.btk.entity.enums.ERole;
import com.btk.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Auth extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String middleName;
    private String surname;
    private String activationCode;
    @ElementCollection(targetClass = ERole.class)
    @JoinTable(name = "tblRoleTypes", joinColumns = @JoinColumn(name = "authId"))
    @Column(name = "roleType", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<ERole> roles;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private EStatus status = EStatus.PENDING;
}
