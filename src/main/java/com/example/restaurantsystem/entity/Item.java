package com.example.restaurantsystem.entity;
import com.example.restaurantsystem.enums.ItemType;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@Table(name = "items")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String photo;
    Double price;

    @Enumerated(EnumType.STRING)
    ItemType itemType;

}
