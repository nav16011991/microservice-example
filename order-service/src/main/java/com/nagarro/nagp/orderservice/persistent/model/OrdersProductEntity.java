package com.nagarro.nagp.orderservice.persistent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORDERS_PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_ORDERS")
    @SequenceGenerator(name = "SEQ_ID_ORDERS", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ORDERS_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_PRODUCT_ORDERS"))
    private OrdersEntity order;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "QUANTITY")
    private Long quantity;
}
