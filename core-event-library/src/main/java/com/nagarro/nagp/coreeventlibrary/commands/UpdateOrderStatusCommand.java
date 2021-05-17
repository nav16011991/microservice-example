package com.nagarro.nagp.coreeventlibrary.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStatusCommand implements Serializable {
    
    private String orderId;

    private String orderStatus;
}
