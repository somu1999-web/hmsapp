package com.hmsapp_test.repository;

import com.hmsapp_test.entity.Property;
import com.hmsapp_test.entity.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {

    List<PropertyImage> findByProperty(Property property);
}