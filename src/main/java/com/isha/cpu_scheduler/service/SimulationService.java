package com.isha.cpu_scheduler.service;

import com.isha.cpu_scheduler.entity.Simulation;
import com.isha.cpu_scheduler.repository.SimulationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimulationService {

    private final SimulationRepository simulationRepository;

    public SimulationService(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    public Simulation saveSimulation(Simulation simulation) {
        return simulationRepository.save(simulation);
    }

    public List<Simulation> getAllSimulations() {
        return simulationRepository.findAll();
    }

    public Simulation getSimulationById(Long id) {
        return simulationRepository.findById(id).orElse(null);
    }

    public void deleteSimulation(Long id) {
        simulationRepository.deleteById(id);
    }

    public Simulation updateSimulation(Long id, Simulation updatedSimulation) {

        Simulation simulation =
                simulationRepository.findById(id).orElse(null);

        if (simulation != null) {

            simulation.setAlgorithm(updatedSimulation.getAlgorithm());
            simulation.setTimeQuantum(updatedSimulation.getTimeQuantum());
            simulation.setAvgWaitingTime(updatedSimulation.getAvgWaitingTime());
            simulation.setAvgTurnaroundTime(updatedSimulation.getAvgTurnaroundTime());
            simulation.setCpuUtilization(updatedSimulation.getCpuUtilization());
            simulation.setThroughput(updatedSimulation.getThroughput());

            return simulationRepository.save(simulation);
        }

        return null;
    }
}