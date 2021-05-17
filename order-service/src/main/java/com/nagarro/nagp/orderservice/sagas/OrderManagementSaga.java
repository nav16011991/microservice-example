package com.nagarro.nagp.orderservice.sagas;

import com.nagarro.nagp.coreeventlibrary.commands.CancelInvoiceCommand;
import com.nagarro.nagp.coreeventlibrary.commands.CreateInvoiceCommand;
import com.nagarro.nagp.coreeventlibrary.commands.CreateShippingCommand;
import com.nagarro.nagp.coreeventlibrary.commands.UpdateOrderStatusCommand;
import com.nagarro.nagp.coreeventlibrary.events.*;
import com.nagarro.nagp.orderservice.service.model.OrderStatus;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;


@Component
class OrderManagementEventHandler {

    @Autowired
    private EventBus eventBus;


    @EventHandler
    public void handle(OrderCreatedEvent orderCreatedEvent){
        String paymentId = UUID.randomUUID().toString();
        System.out.println("[SAGA-Start] OrderCreatedEvent: "+orderCreatedEvent.getOrderId());
        

        eventBus.publish(asEventMessage(new CreateInvoiceCommand(paymentId, orderCreatedEvent.getOrderId())));
        
    }

    @EventHandler
    public void handle(InvoiceCreatedEvent invoiceCreatedEvent){
        String shippingId = UUID.randomUUID().toString();

        System.out.println("[SAGA] InvoiceCreatedEvent: "+invoiceCreatedEvent.getOrderId());
        
        //send the create shipping command
        eventBus.publish(asEventMessage(new CreateShippingCommand(shippingId, invoiceCreatedEvent.getOrderId(), invoiceCreatedEvent.getPaymentId())));
    }

    @EventHandler
    public void handle(OrderShippedEvent orderShippedEvent){

        System.out.println("[SAGA] OrderShippedEvent: "+orderShippedEvent.getOrderId());
        eventBus.publish(asEventMessage((new UpdateOrderStatusCommand(orderShippedEvent.getOrderId(), String.valueOf(OrderStatus.SHIPPED)))));
    }
    
    @EventHandler
    public void handle(ShipmentDeniedEvent shipmentDeniedEvent){
        System.out.println("[SAGA] ShipmentDeniedEvent: "+shipmentDeniedEvent.getOrderId());
        eventBus.publish(asEventMessage((new CancelInvoiceCommand(shipmentDeniedEvent.getPaymentId(),shipmentDeniedEvent.getOrderId()))));
        eventBus.publish(asEventMessage((new UpdateOrderStatusCommand(shipmentDeniedEvent.getOrderId(), String.valueOf(OrderStatus.REJECTED)))));
     }

    @EventHandler
    public void handle(OrderUpdatedEvent orderUpdatedEvent){
        System.out.println("[SAGA-End] OrderUpdatedEvent: "+orderUpdatedEvent.getOrderId());
    }
}
