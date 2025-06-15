package com.hmsapp_test.repository;

import com.hmsapp_test.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}