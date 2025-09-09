package com.eastnets.taskmanagement.manager.model;

public class GroupedTasks extends Tasks{
    private Tasks groupedTask;
    private Tasks groupedOnTask;
    public Tasks getGroupedTask() {
        return groupedTask;
    }
    public void setGroupedTask(Tasks groupedTask) {
        this.groupedTask = groupedTask;
    }
    public Tasks getGroupedOnTask() {
        return groupedOnTask;
    }
}
