package com.example.restaurantsystem.entity;

import com.example.restaurantsystem.enums.TableStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@jakarta.persistence.Table(name = "tables")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer capacity;

    @Enumerated(EnumType.STRING)
    TableStatus status;
}
