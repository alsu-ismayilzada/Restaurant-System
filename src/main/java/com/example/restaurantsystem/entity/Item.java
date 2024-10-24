package com.example.restaurantsystem.entity;
import com.example.restaurantsystem.enums.ItemType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
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

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    @JsonManagedReference
    List<ItemInfo> itemInfos;
}
