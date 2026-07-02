package com.isha.cpu_scheduler.dto;

public class GanttChartDTO {

    private String processId;
    private Integer startTime;
    private Integer endTime;

    public GanttChartDTO() {
    }

    public GanttChartDTO(
            String processId,
            Integer startTime,
            Integer endTime) {

        this.processId = processId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }
}