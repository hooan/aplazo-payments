package com.aplazo.api.repositories;

import com.aplazo.api.dto.Output;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputRepository extends JpaRepository<Output,Integer> {
}
