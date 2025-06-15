package com.hmsapp_test.repository;

import com.hmsapp_test.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("SELECT p FROM Property p JOIN p.city c JOIN p.country co WHERE c.name = :searchParam OR co.name = :searchParam")
    List<Property> searchProperty(@Param("searchParam") String searchParam);

}