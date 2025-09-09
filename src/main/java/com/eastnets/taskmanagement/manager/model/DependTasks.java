package com.eastnets.taskmanagement.manager.model;

public class DependTasks extends Tasks{
    private Tasks dependentTask;
    private Tasks dependentOnTask;
    public Tasks getDependentTask() {
        return dependentTask;
    }
    public void setDependentTask(Tasks dependentTask) {
        this.dependentTask = dependentTask;
    }
    public Tasks getDependentOnTask() {
        return dependentOnTask;
    }
    public void setDependentOnTask(Tasks dependentOnTask) {
        this.dependentOnTask = dependentOnTask;
    }
}
