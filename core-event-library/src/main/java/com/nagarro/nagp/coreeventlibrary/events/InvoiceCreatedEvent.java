package com.nagarro.nagp.coreeventlibrary.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceCreatedEvent implements Serializable {

    private String paymentId;

    private String orderId;

}
