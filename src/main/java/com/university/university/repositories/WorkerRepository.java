package com.university.university.repositories;

import com.university.university.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long>{
    List<Worker> findByNameContainingIgnoreCase(String template);
}
