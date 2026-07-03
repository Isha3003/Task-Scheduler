package com.isha.cpu_scheduler.controller;

import com.isha.cpu_scheduler.algorithm.FCFSScheduler;
import com.isha.cpu_scheduler.algorithm.PriorityScheduler;
import com.isha.cpu_scheduler.algorithm.RoundRobinScheduler;
import com.isha.cpu_scheduler.algorithm.SJFScheduler;
import com.isha.cpu_scheduler.dto.ProcessDTO;
import com.isha.cpu_scheduler.dto.SimulationRequest;
import com.isha.cpu_scheduler.dto.SimulationResponse;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/simulation")
public class SimulationController {

    
    private final FCFSScheduler fcfsScheduler;
    private final SJFScheduler sjfScheduler;
    private final RoundRobinScheduler roundRobinScheduler;
    private final PriorityScheduler priorityScheduler;

   public SimulationController(
        FCFSScheduler fcfsScheduler,
        SJFScheduler sjfScheduler,
        RoundRobinScheduler roundRobinScheduler,
        PriorityScheduler priorityScheduler) {

    this.fcfsScheduler = fcfsScheduler;
    this.sjfScheduler = sjfScheduler;
    this.roundRobinScheduler = roundRobinScheduler;
    this.priorityScheduler = priorityScheduler;
}

    @GetMapping
    public String test() {
        return "Simulation Controller Working";
    }

    
    
    

    


    // =========================
    // REAL SIMULATION API
    // =========================

    @PostMapping("/run")
    public SimulationResponse runSimulation(
            @RequestBody SimulationRequest request) {

        String algorithm =
                request.getAlgorithm().toUpperCase();

        switch (algorithm) {

            case "FCFS":
                return fcfsScheduler.run(
                        request.getProcesses());

            case "SJF":
                return sjfScheduler.run(
                        request.getProcesses());

            case "ROUND_ROBIN":
                return roundRobinScheduler.run(
                        request.getProcesses(),
                        request.getTimeQuantum());

            case "PRIORITY":
                return priorityScheduler.run(
                        request.getProcesses());

            default:
                throw new RuntimeException(
                        "Invalid Algorithm");
        }
    }

    // =========================
    // FCFS TEST
    // =========================

    @GetMapping("/fcfs-test")
    public SimulationResponse testFCFS() {

        List<ProcessDTO> processes = new ArrayList<>();

        ProcessDTO p1 = new ProcessDTO();
        p1.setProcessId("P1");
        p1.setArrivalTime(0);
        p1.setBurstTime(5);

        ProcessDTO p2 = new ProcessDTO();
        p2.setProcessId("P2");
        p2.setArrivalTime(1);
        p2.setBurstTime(3);

        ProcessDTO p3 = new ProcessDTO();
        p3.setProcessId("P3");
        p3.setArrivalTime(2);
        p3.setBurstTime(8);

        processes.add(p1);
        processes.add(p2);
        processes.add(p3);

        return fcfsScheduler.run(processes);
    }

    // =========================
    // SJF TEST
    // =========================

    @GetMapping("/sjf-test")
    public SimulationResponse testSJF() {

        List<ProcessDTO> processes = new ArrayList<>();

        ProcessDTO p1 = new ProcessDTO();
        p1.setProcessId("P1");
        p1.setArrivalTime(0);
        p1.setBurstTime(6);

        ProcessDTO p2 = new ProcessDTO();
        p2.setProcessId("P2");
        p2.setArrivalTime(0);
        p2.setBurstTime(2);

        ProcessDTO p3 = new ProcessDTO();
        p3.setProcessId("P3");
        p3.setArrivalTime(0);
        p3.setBurstTime(8);

        processes.add(p1);
        processes.add(p2);
        processes.add(p3);

        return sjfScheduler.run(processes);
    }

    // =========================
    // ROUND ROBIN TEST
    // =========================

    @GetMapping("/rr-test")
    public SimulationResponse testRoundRobin() {

        List<ProcessDTO> processes = new ArrayList<>();

        ProcessDTO p1 = new ProcessDTO();
        p1.setProcessId("P1");
        p1.setArrivalTime(0);
        p1.setBurstTime(5);

        ProcessDTO p2 = new ProcessDTO();
        p2.setProcessId("P2");
        p2.setArrivalTime(1);
        p2.setBurstTime(4);

        ProcessDTO p3 = new ProcessDTO();
        p3.setProcessId("P3");
        p3.setArrivalTime(2);
        p3.setBurstTime(2);

        processes.add(p1);
        processes.add(p2);
        processes.add(p3);

        return roundRobinScheduler.run(processes, 2);
    }

    // =========================
    // PRIORITY TEST
    // =========================

    @GetMapping("/priority-test")
    public SimulationResponse testPriority() {

        List<ProcessDTO> processes = new ArrayList<>();

        ProcessDTO p1 = new ProcessDTO();
        p1.setProcessId("P1");
        p1.setArrivalTime(0);
        p1.setBurstTime(5);
        p1.setPriority(2);

        ProcessDTO p2 = new ProcessDTO();
        p2.setProcessId("P2");
        p2.setArrivalTime(1);
        p2.setBurstTime(3);
        p2.setPriority(1);

        ProcessDTO p3 = new ProcessDTO();
        p3.setProcessId("P3");
        p3.setArrivalTime(2);
        p3.setBurstTime(8);
        p3.setPriority(3);

        processes.add(p1);
        processes.add(p2);
        processes.add(p3);

        return priorityScheduler.run(processes);
    }
}