package com.isha.cpu_scheduler.algorithm;

import com.isha.cpu_scheduler.dto.GanttChartDTO;
import com.isha.cpu_scheduler.dto.ProcessDTO;
import com.isha.cpu_scheduler.dto.SimulationResponse;

import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class SJFScheduler {

   public SimulationResponse run(List<ProcessDTO> processes) {

    List<ProcessDTO> result = new ArrayList<>(processes);

    result.sort(Comparator.comparingInt(ProcessDTO::getBurstTime));

    int currentTime = 0;

    double totalWaitingTime = 0;
    double totalTurnaroundTime = 0;
    double totalBurstTime = 0;

    List<GanttChartDTO> ganttChart = new ArrayList<>();

    for (ProcessDTO process : result) {

        if (currentTime < process.getArrivalTime()) {
            currentTime = process.getArrivalTime();
        }

        int startTime = currentTime;

        currentTime += process.getBurstTime();

        process.setCompletionTime(currentTime);

        process.setTurnaroundTime(
                process.getCompletionTime() - process.getArrivalTime());

        process.setWaitingTime(
                process.getTurnaroundTime() - process.getBurstTime());

        process.setResponseTime(process.getWaitingTime());

        totalWaitingTime += process.getWaitingTime();
        totalTurnaroundTime += process.getTurnaroundTime();
        totalBurstTime += process.getBurstTime();

        GanttChartDTO block = new GanttChartDTO();
        block.setProcessId(process.getProcessId());
        block.setStartTime(startTime);
        block.setEndTime(currentTime);

        ganttChart.add(block);
    }

    SimulationResponse response = new SimulationResponse();

    response.setProcesses(result);

    response.setAverageWaitingTime(
            totalWaitingTime / result.size());

    response.setAverageTurnaroundTime(
            totalTurnaroundTime / result.size());

    response.setCpuUtilization(
            (totalBurstTime / currentTime) * 100);

    response.setThroughput(
            (double) result.size() / currentTime);

    response.setGanttChart(ganttChart);

    return response;
}
}