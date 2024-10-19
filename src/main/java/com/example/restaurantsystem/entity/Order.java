package com.example.restaurantsystem.entity;

import com.example.restaurantsystem.enums.OrderState;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(mappedBy = "order")
    List<Item> item;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    User user;
    LocalDateTime date;
    Double bill;
    OrderState status;
}
