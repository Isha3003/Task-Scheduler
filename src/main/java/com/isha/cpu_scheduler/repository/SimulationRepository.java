package com.isha.cpu_scheduler.repository;

import com.isha.cpu_scheduler.entity.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulationRepository
        extends JpaRepository<Simulation, Long> {
}
