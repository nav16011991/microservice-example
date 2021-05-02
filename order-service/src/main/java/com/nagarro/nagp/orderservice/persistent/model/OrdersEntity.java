package com.nagarro.nagp.orderservice.persistent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_ORDERS")
    @SequenceGenerator(name = "SEQ_ID_ORDERS", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "TOTAL_AMOUNT")
    private Double totalAmount;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrdersProductEntity> ordersProductEntityList;
}
