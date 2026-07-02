package com.isha.cpu_scheduler.dto;

public class ProcessDTO {

    private String processId;
    private Integer arrivalTime;
    private Integer burstTime;
    private Integer priority;

    private Integer completionTime;
    private Integer turnaroundTime;
    private Integer waitingTime;
    private Integer responseTime;

    public ProcessDTO() {
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(Integer burstTime) {
        this.burstTime = burstTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Integer completionTime) {
        this.completionTime = completionTime;
    }

    public Integer getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(Integer turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public Integer getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Integer waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }
}