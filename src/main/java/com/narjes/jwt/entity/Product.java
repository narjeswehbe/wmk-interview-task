package com.narjes.jwt.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "name cannot be null")
    private String name ;
    @NotNull(message = "description cannot be null")
    private String description;
    @NotNull(message = "price cannot be null")
    private BigDecimal price;
    @NotNull(message = "quantity cannot be null")
    private Long quantity;
    @NotNull(message = "categories cannot be null")
    @OneToMany(cascade=CascadeType.ALL)
    private List<Category> categories ;




}

