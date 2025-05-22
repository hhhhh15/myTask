package com.example.cutivatingapp1_java.Model;

public class Task {
    private String taskid;
    private String tasktype;
    private String taskContent;
    private String setTime;
    private String remainTime;
    public Task(String taskid, String tasktype, String taskContent, String setTime, String remainTime) {
        this.taskid = taskid;
        this.tasktype = tasktype;
        this.taskContent = taskContent;
        this.setTime = setTime;
        this.remainTime = remainTime;
    }
    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getTasktype() {
        return tasktype;
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }
}
