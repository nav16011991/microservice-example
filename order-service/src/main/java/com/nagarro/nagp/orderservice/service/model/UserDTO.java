package com.nagarro.nagp.orderservice.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private String id;

    private String username;
}
