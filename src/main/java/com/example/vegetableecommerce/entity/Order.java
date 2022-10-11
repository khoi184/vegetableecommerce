package com.example.vegetableecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Double unitPrice;
    private Double totalPrice;
    private Integer quantity;
    private Long order_details_id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order_id", fetch = FetchType.LAZY)
    private List<Cart> cart;
}
