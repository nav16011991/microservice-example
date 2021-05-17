package com.nagarro.nagp.coreeventlibrary.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderShippedEvent implements Serializable {

    private String shippingId;

    private String orderId;

    private String paymentId;

}
