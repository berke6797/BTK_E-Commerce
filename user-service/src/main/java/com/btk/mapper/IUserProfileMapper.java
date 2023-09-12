package com.btk.mapper;

import com.btk.dto.request.ToAuthPasswordChangeDto;
import com.btk.dto.response.NewCreateUserResponseDto;
import com.btk.dto.response.UserProfileResponseDto;
import com.btk.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {
    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);

    UserProfile fromNewCreateUserResponseDtoToUserProfile(final NewCreateUserResponseDto dto);
    ToAuthPasswordChangeDto fromUserProfilToAuthPasswordChangeDto (final UserProfile userProfile);
UserProfileResponseDto authIdToUserProfileResponseDto(Long authId);
}
