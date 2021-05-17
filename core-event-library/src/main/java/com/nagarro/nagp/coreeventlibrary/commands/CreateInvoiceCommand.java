package com.nagarro.nagp.coreeventlibrary.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceCommand implements Serializable {

    private String paymentId;

    private String orderId;

}
