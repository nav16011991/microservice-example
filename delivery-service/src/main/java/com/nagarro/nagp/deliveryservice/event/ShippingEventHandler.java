package com.nagarro.nagp.deliveryservice.event;

import com.nagarro.nagp.coreeventlibrary.commands.CreateShippingCommand;
import com.nagarro.nagp.coreeventlibrary.events.OrderShippedEvent;
import com.nagarro.nagp.coreeventlibrary.events.ShipmentDeniedEvent;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;


@Component
public class ShippingEventHandler {
    
    @Autowired
    private EventBus eventBus;

    @EventHandler
    public void on(CreateShippingCommand createShippingCommand){
        if(Integer.parseInt(createShippingCommand.getOrderId()) % 2 == 0 ) {
            System.out.println("Denied shipping for: "+createShippingCommand.getOrderId());
            this.eventBus.publish(asEventMessage(new ShipmentDeniedEvent(createShippingCommand.getShippingId(),createShippingCommand.getOrderId(), createShippingCommand.getPaymentId())));
        }else {
            System.out.println("Order shipped for: "+createShippingCommand.getOrderId());
            this.eventBus.publish(asEventMessage(new OrderShippedEvent(createShippingCommand.getShippingId(), createShippingCommand.getOrderId(), createShippingCommand.getPaymentId())));
        }
    }

}
