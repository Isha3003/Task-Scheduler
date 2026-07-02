package com.isha.cpu_scheduler.algorithm;

import com.isha.cpu_scheduler.dto.GanttChartDTO;
import com.isha.cpu_scheduler.dto.ProcessDTO;
import com.isha.cpu_scheduler.dto.SimulationResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class FCFSScheduler {

    public SimulationResponse run(List<ProcessDTO> processes) {

        processes.sort(Comparator.comparing(ProcessDTO::getArrivalTime));

        int currentTime = 0;

        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        double totalBurstTime = 0;

        List<GanttChartDTO> ganttChart = new ArrayList<>();

        for (ProcessDTO process : processes) {

            if (currentTime < process.getArrivalTime()) {

    GanttChartDTO idle = new GanttChartDTO();
    idle.setProcessId("Idle");
    idle.setStartTime(currentTime);
    idle.setEndTime(process.getArrivalTime());

    ganttChart.add(idle);

    currentTime = process.getArrivalTime();
}

            int startTime = currentTime;

            currentTime += process.getBurstTime();

            int completionTime = currentTime;
            int turnaroundTime =
                    completionTime - process.getArrivalTime();
            int waitingTime =
                    turnaroundTime - process.getBurstTime();

            process.setCompletionTime(completionTime);
            process.setTurnaroundTime(turnaroundTime);
            process.setWaitingTime(waitingTime);
            process.setResponseTime(waitingTime);

            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;
            totalBurstTime += process.getBurstTime();

            GanttChartDTO block = new GanttChartDTO();
            block.setProcessId(process.getProcessId());
            block.setStartTime(startTime);
            block.setEndTime(completionTime);

            ganttChart.add(block);
        }

        SimulationResponse response =
                new SimulationResponse();

        response.setProcesses(processes);

        response.setAverageWaitingTime(
                totalWaitingTime / processes.size());

        response.setAverageTurnaroundTime(
                totalTurnaroundTime / processes.size());

        response.setThroughput(
                (double) processes.size() / currentTime);

        response.setCpuUtilization(
                (totalBurstTime / currentTime) * 100);

        response.setGanttChart(ganttChart);

        return response;
    }
}