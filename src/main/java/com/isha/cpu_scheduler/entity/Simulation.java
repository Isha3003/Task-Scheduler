package com.isha.cpu_scheduler.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "simulation")
public class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String algorithm;
    private Integer timeQuantum;
    private Double avgWaitingTime;
    private Double avgTurnaroundTime;
    private Double cpuUtilization;
    private Double throughput;

    public Simulation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public Integer getTimeQuantum() {
        return timeQuantum;
    }

    public void setTimeQuantum(Integer timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public Double getAvgWaitingTime() {
        return avgWaitingTime;
    }

    public void setAvgWaitingTime(Double avgWaitingTime) {
        this.avgWaitingTime = avgWaitingTime;
    }

    public Double getAvgTurnaroundTime() {
        return avgTurnaroundTime;
    }

    public void setAvgTurnaroundTime(Double avgTurnaroundTime) {
        this.avgTurnaroundTime = avgTurnaroundTime;
    }

    public Double getCpuUtilization() {
        return cpuUtilization;
    }

    public void setCpuUtilization(Double cpuUtilization) {
        this.cpuUtilization = cpuUtilization;
    }

    public Double getThroughput() {
        return throughput;
    }

    public void setThroughput(Double throughput) {
        this.throughput = throughput;
    }
}