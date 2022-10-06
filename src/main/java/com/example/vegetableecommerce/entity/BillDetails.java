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
@Table(name = "bill_details")
public class BillDetails extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double unitPrice;
    private Double totalPrice;
    private Integer quantity;
    private String address;
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bill_details_id")
    private List<Bill> bills;
}
