package com.nagarro.nagp.userservice.service.mapper;

import com.nagarro.nagp.userservice.persistent.model.ApplicationUserEntity;
import com.nagarro.nagp.userservice.service.model.ApplicationUserDTO;
import org.springframework.util.ObjectUtils;

public class ApplicationUserMapper {

    public static ApplicationUserDTO toDTO(ApplicationUserEntity applicationUserEntity) {
        if (ObjectUtils.isEmpty(applicationUserEntity)) {
            return null;
        }
        return ApplicationUserDTO.builder()
                .id(applicationUserEntity.getId())
                .username(applicationUserEntity.getUsername())
                .firstName(applicationUserEntity.getFirstName())
                .lastName(applicationUserEntity.getLastName())
                .build();
    }
}
