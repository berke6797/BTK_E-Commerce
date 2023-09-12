package com.btk.mapper;

import com.btk.dto.request.ForgotPasswordUserRequestDto;
import com.btk.dto.request.NewCreateUserRequestDto;
import com.btk.dto.request.RegisterUserRequestDto;
import com.btk.entity.Auth;
import com.btk.rabbitmq.model.RegisterMailModel;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth fromUserRequestDtoToAuth(final RegisterUserRequestDto dto);
    NewCreateUserRequestDto fromAuthToNewCreateUserDto(final RegisterUserRequestDto dto);

    ForgotPasswordUserRequestDto fromAuthToForgotPasswordUserRequestDto(final Auth auth);

    NewCreateUserRequestDto fromAuthNewCreateUserRequestDto(final Auth auth);
    RegisterMailModel fromAuthToRegisterMailModel(final Auth auth);
}
