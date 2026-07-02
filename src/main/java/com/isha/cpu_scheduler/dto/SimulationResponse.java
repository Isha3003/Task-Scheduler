package com.isha.cpu_scheduler.dto;

import java.util.List;

public class SimulationResponse {

    private List<ProcessDTO> processes;

    private Double averageWaitingTime;
    private Double averageTurnaroundTime;
    private Double cpuUtilization;
    private Double throughput;

    private List<GanttChartDTO> ganttChart;

    public SimulationResponse() {
    }

    public List<ProcessDTO> getProcesses() {
        return processes;
    }

    public void setProcesses(List<ProcessDTO> processes) {
        this.processes = processes;
    }

    public Double getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public void setAverageWaitingTime(Double averageWaitingTime) {
        this.averageWaitingTime = averageWaitingTime;
    }

    public Double getAverageTurnaroundTime() {
        return averageTurnaroundTime;
    }

    public void setAverageTurnaroundTime(Double averageTurnaroundTime) {
        this.averageTurnaroundTime = averageTurnaroundTime;
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

    public List<GanttChartDTO> getGanttChart() {
        return ganttChart;
    }

    public void setGanttChart(List<GanttChartDTO> ganttChart) {
        this.ganttChart = ganttChart;
    }
}