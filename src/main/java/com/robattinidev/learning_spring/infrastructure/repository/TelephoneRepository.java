package com.robattinidev.learning_spring.infrastructure.repository;

import com.robattinidev.learning_spring.infrastructure.entity.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
}
