package com.nagarro.nagp.userservice.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationUserDTO {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;
}
