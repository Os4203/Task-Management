package com.eastnets.taskmanagement.manager.model;

public class DefaultTasks extends Tasks{
    private Tasks defaultTask;
    private Tasks defaultOnTask;
    public Tasks getDefaultTask() {
        return defaultTask;
    }
    public void setDefaultTask(Tasks defaultTask) {
        this.defaultTask = defaultTask;
    }
    public Tasks getDefaultOnTask() {
        return defaultOnTask;
    }
    public void setDefaultOnTask(Tasks defaultOnTask) {
        this.defaultOnTask = defaultOnTask;
    }
}
