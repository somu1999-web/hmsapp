package com.hmsapp_test.repository;

import com.hmsapp_test.entity.Property;
import com.hmsapp_test.entity.Reviews;
import com.hmsapp_test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    List<Reviews> findByUser(User user);
    Reviews findByPropertyAndUser(Property property , User user);
}