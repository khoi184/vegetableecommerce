package com.example.vegetableecommerce.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Name must be filled!")
    private String name;

    private Long price;
    private String description;
    private Integer quantity;
    private String category;
    private Integer discount;
    private String producer;
    private String avatarImage;
    private Long cart_id;
    private Long category_id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product_id")
    private List<Image> image;
}
