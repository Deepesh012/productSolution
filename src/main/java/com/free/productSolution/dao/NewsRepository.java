package com.free.productSolution.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.free.productSolution.entities.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}

