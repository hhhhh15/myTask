package com.example.cutivatingapp1_java.Model;

import java.time.LocalDateTime;

public class timing {
    private String timeId;
    private String taskid;
    private LocalDateTime setTime;
    private LocalDateTime remainTime;

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public LocalDateTime getSetTime() {
        return setTime;
    }

    public void setSetTime(LocalDateTime setTime) {
        this.setTime = setTime;
    }

    public LocalDateTime getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(LocalDateTime remainTime) {
        this.remainTime = remainTime;
    }
}
