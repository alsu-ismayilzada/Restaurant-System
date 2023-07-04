package com.example.restaurantsystem.repository;

import com.example.restaurantsystem.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table,Integer> {
}
