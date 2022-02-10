package com.aplazo.api.repositories;

import com.aplazo.api.dto.Input;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputRepository extends JpaRepository<Input,Integer> {
}
