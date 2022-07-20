package com.example.demo.repositories;

import com.example.demo.entities.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Integer> {
}
