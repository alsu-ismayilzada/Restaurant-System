package com.example.restaurantsystem.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@jakarta.persistence.Table(name = "reservations")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double price;
    LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="table_id",referencedColumnName = "id")
    Table table;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    User user;
}
