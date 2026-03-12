package com.free.productSolution.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.free.productSolution.entities.Enquiry;
import com.free.productSolution.entities.EnquiryForm;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
}