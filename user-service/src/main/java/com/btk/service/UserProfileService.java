package com.btk.service;

import com.btk.dto.request.UserChangePasswordRequestDto;
import com.btk.dto.response.ForgotPasswordUserResponseDto;
import com.btk.dto.response.NewCreateUserResponseDto;
import com.btk.dto.response.UserProfileResponseDto;
import com.btk.entity.UserProfile;
import com.btk.entity.enums.ERole;
import com.btk.entity.enums.EStatus;
import com.btk.exception.ErrorType;
import com.btk.exception.UserProfileManagerException;
import com.btk.manager.IAuthManager;
import com.btk.mapper.IUserProfileMapper;
import com.btk.repository.IUserRepository;
import com.btk.util.JwtTokenProvider;
import com.btk.util.ServiceManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile, String> {
    private final IUserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final IAuthManager authManager;

    public UserProfileService(IUserRepository userRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, IAuthManager authManager) {
        super(userRepository);
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
    }

    //AuthService'den openfeign ile gelen metod
    public Boolean createUser(NewCreateUserResponseDto dto) {
        UserProfile userProfile = IUserProfileMapper.INSTANCE.fromNewCreateUserResponseDtoToUserProfile(dto);
        List<ERole> roles = new ArrayList<>();
        roles.add(ERole.USER);
        userProfile.setRole(roles);
        save(userProfile);
        return true;
    }

    //AuthService'den openfeign ile gelen metod
    public Boolean createSiteManager(NewCreateUserResponseDto dto) {
        UserProfile userProfile = IUserProfileMapper.INSTANCE.fromNewCreateUserResponseDtoToUserProfile(dto);
        List<ERole> roles = new ArrayList<>();
        roles.add(ERole.SITE_MANAGER);
        userProfile.setRole(roles);
        save(userProfile);
        return true;
    }

    //AuthService'den openfeign ile gelen metod
    public Boolean activateStatus(Long authId) {
        Optional<UserProfile> userProfile = userRepository.findByAuthId(authId);
        if (userProfile.isEmpty())
            throw new UserProfileManagerException(ErrorType.USER_NOT_FOUND);
        userProfile.get().setStatus(EStatus.ACTIVE);
        update(userProfile.get());
        return true;
    }

    //AuthService'den openfeign ile gelen metod
    public Boolean forgotPassword(ForgotPasswordUserResponseDto dto) {
        Optional<UserProfile> optionalUserProfile = userRepository.findByAuthId(dto.getAuthId());
        if (optionalUserProfile.isEmpty())
            throw new UserProfileManagerException(ErrorType.USER_NOT_FOUND);
        optionalUserProfile.get().setPassword(dto.getPassword());
        update(optionalUserProfile.get());
        return true;
    }

    @Transactional
    public String changePassword(UserChangePasswordRequestDto dto) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(dto.getToken());
        if (authId.isEmpty()) {
            throw new UserProfileManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> userProfile = userRepository.findByAuthId(authId.get());
        if (userProfile.isPresent()) {
            if (passwordEncoder.matches(dto.getOldPassword(), userProfile.get().getPassword())) {
                String newPass = dto.getNewPassword();
                userProfile.get().setPassword(passwordEncoder.encode(newPass));
                update(userProfile.get());
                authManager.changePassword(IUserProfileMapper.INSTANCE.fromUserProfilToAuthPasswordChangeDto(userProfile.get()));
            } else {
                throw new UserProfileManagerException(ErrorType.PASSWORD_ERROR);
            }
        } else {
            throw new UserProfileManagerException(ErrorType.USER_NOT_FOUND);
        }
        return dto.getNewPassword();
    }

    public String findByAuthId(Long authId) {
        return userRepository.findByAuthId(authId).get().getUserId();
    }


}
