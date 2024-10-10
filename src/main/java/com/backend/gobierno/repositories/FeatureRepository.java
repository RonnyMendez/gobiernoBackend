package com.backend.gobierno.repositories;

import com.backend.gobierno.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    // Aquí puedes agregar métodos de consulta personalizados si los necesitas
}