package com.example.backend.model.repositories;

import com.example.backend.model.entities.Vantagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VantagemRepository extends JpaRepository<Vantagem, Long> {
}
