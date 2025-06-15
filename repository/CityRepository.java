package com.hmsapp_test.repository;

import com.hmsapp_test.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}