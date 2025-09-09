package com.eastnets.taskmanagement.manager.model;

public class UrgentTasks extends Tasks{
    private Tasks urgentTask;
    private Tasks urgentOnTask;
    public Tasks getUrgentTask() {
        return urgentTask;
    }
    public void setUrgentTask(Tasks urgentTask) {
        this.urgentTask = urgentTask;
    }
    public Tasks getUrgentOnTask() {
        return urgentOnTask;
    }
    public void setUrgentOnTask(Tasks urgentOnTask) {
        this.urgentOnTask = urgentOnTask;
    }
    public boolean isHighPriority() {
        return getPriority() == Priority.High;
    }
}
