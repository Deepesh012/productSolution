package com.free.productSolution.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.free.productSolution.entities.product.Fastners;

@Repository
public interface FastnersRepository extends JpaRepository<Fastners, Integer> {
}