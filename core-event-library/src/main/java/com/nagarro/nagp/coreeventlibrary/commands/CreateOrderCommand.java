package com.nagarro.nagp.coreeventlibrary.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderCommand implements Serializable {

    private String orderId;

    private BigDecimal price;

    private String currency;

    private String orderStatus;
}
