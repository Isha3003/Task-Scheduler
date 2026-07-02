package com.isha.cpu_scheduler.algorithm;

import com.isha.cpu_scheduler.dto.GanttChartDTO;
import com.isha.cpu_scheduler.dto.ProcessDTO;
import com.isha.cpu_scheduler.dto.SimulationResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class PriorityScheduler {

    public SimulationResponse run(List<ProcessDTO> processes) {

        List<ProcessDTO> remaining = new ArrayList<>(processes);
        List<ProcessDTO> completed = new ArrayList<>();
        List<GanttChartDTO> ganttChart = new ArrayList<>();

        int currentTime = 0;

        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        double totalBurstTime = 0;

        while (!remaining.isEmpty()) {

            List<ProcessDTO> available = new ArrayList<>();

            for (ProcessDTO process : remaining) {
                if (process.getArrivalTime() <= currentTime) {
                    available.add(process);
                }
            }

            if (available.isEmpty()) {

                int nextArrival = Integer.MAX_VALUE;

                for (ProcessDTO process : remaining) {
                    nextArrival = Math.min(nextArrival, process.getArrivalTime());
                }

                ganttChart.add(
                        new GanttChartDTO(
                                "Idle",
                                currentTime,
                                nextArrival
                        )
                );

                currentTime = nextArrival;
                continue;
            }

            available.sort(
                    Comparator
                            .comparingInt(ProcessDTO::getPriority)
                            .thenComparingInt(ProcessDTO::getArrivalTime)
            );

            ProcessDTO process = available.get(0);

            int startTime = currentTime;

            currentTime += process.getBurstTime();

            process.setCompletionTime(currentTime);

            process.setTurnaroundTime(
                    process.getCompletionTime()
                            - process.getArrivalTime()
            );

            process.setWaitingTime(
                    process.getTurnaroundTime()
                            - process.getBurstTime()
            );

            process.setResponseTime(
                    startTime - process.getArrivalTime()
            );

            totalWaitingTime += process.getWaitingTime();
            totalTurnaroundTime += process.getTurnaroundTime();
            totalBurstTime += process.getBurstTime();

            ganttChart.add(
                    new GanttChartDTO(
                            process.getProcessId(),
                            startTime,
                            currentTime
                    )
            );

            completed.add(process);
            remaining.remove(process);
        }

        completed.sort(
                Comparator.comparing(ProcessDTO::getProcessId)
        );

        SimulationResponse response = new SimulationResponse();

        response.setProcesses(completed);

        response.setAverageWaitingTime(
                totalWaitingTime / completed.size()
        );

        response.setAverageTurnaroundTime(
                totalTurnaroundTime / completed.size()
        );

        response.setCpuUtilization(
                (totalBurstTime / currentTime) * 100
        );

        response.setThroughput(
                (double) completed.size() / currentTime
        );

        response.setGanttChart(ganttChart);

        return response;
    }
}