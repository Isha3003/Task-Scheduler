package com.isha.cpu_scheduler.algorithm;

import com.isha.cpu_scheduler.dto.GanttChartDTO;
import com.isha.cpu_scheduler.dto.ProcessDTO;
import com.isha.cpu_scheduler.dto.SimulationResponse;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoundRobinScheduler {

    public SimulationResponse run(List<ProcessDTO> processes, int timeQuantum) {

        List<ProcessDTO> result = new ArrayList<>();

        List<GanttChartDTO> ganttChart = new ArrayList<>();

        Map<String, Integer> remainingBurst = new HashMap<>();

        for (ProcessDTO process : processes) {
            remainingBurst.put(
                    process.getProcessId(),
                    process.getBurstTime()
            );
        }

        Queue<ProcessDTO> queue = new LinkedList<>(processes);

        int currentTime = 0;

        while (!queue.isEmpty()) {

            ProcessDTO process = queue.poll();

            int remaining =
                    remainingBurst.get(process.getProcessId());

            if (remaining > timeQuantum) {

                int startTime = currentTime;

                currentTime += timeQuantum;

                ganttChart.add(
                        new GanttChartDTO(
                                process.getProcessId(),
                                startTime,
                                currentTime
                        )
                );

                remainingBurst.put(
                        process.getProcessId(),
                        remaining - timeQuantum
                );

                queue.offer(process);

            } else {

                int startTime = currentTime;

                currentTime += remaining;

                ganttChart.add(
                        new GanttChartDTO(
                                process.getProcessId(),
                                startTime,
                                currentTime
                        )
                );

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
                        process.getWaitingTime()
                );

                result.add(process);
            }
        }

        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        double totalBurstTime = 0;

        for (ProcessDTO process : result) {

            totalWaitingTime += process.getWaitingTime();
            totalTurnaroundTime += process.getTurnaroundTime();
            totalBurstTime += process.getBurstTime();
        }

        SimulationResponse response =
                new SimulationResponse();

        response.setProcesses(result);

        response.setAverageWaitingTime(
                totalWaitingTime / result.size()
        );

        response.setAverageTurnaroundTime(
                totalTurnaroundTime / result.size()
        );

        response.setCpuUtilization(
                (totalBurstTime / currentTime) * 100
        );

        response.setThroughput(
                (double) result.size() / currentTime
        );

        response.setGanttChart(ganttChart);

        return response;
    }
}