package com.nagarro.nagp.paymentservice.event;

import com.nagarro.nagp.coreeventlibrary.commands.CancelInvoiceCommand;
import com.nagarro.nagp.coreeventlibrary.commands.CreateInvoiceCommand;
import com.nagarro.nagp.coreeventlibrary.events.InvoiceCancelledEvent;
import com.nagarro.nagp.coreeventlibrary.events.InvoiceCreatedEvent;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;



@Component
public class InvoiceEventHandler {

    @Autowired
    private EventBus eventBus;
    
    @EventHandler
    public void handleCreateInvoice(CreateInvoiceCommand createInvoiceCommand) {
        System.out.println("Creating invoice for: " + createInvoiceCommand.getOrderId());
        this.eventBus.publish(asEventMessage(new InvoiceCreatedEvent(createInvoiceCommand.getPaymentId(),
            createInvoiceCommand.getOrderId())));
    }

    @EventHandler
    public void handle(CancelInvoiceCommand cancelInvoiceCommand){
        System.out.println("Cancelling invoice for: "+cancelInvoiceCommand.getOrderId());
        this.eventBus.publish(asEventMessage(new InvoiceCancelledEvent(cancelInvoiceCommand.getPaymentId(), cancelInvoiceCommand.getOrderId())));
    }

}
