package com.ohgiraffers.model.dto;

public class JobDTO {
    private int code;
    private String jobCode;
    private String jobName;

    public JobDTO() {
    }

    public JobDTO(int code, String jobCode, String jobName) {
        this.code = code;
        this.jobCode = jobCode;
        this.jobName = jobName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public String toString() {
        return "JobDTO{" +
                "code=" + code +
                ", jobCode='" + jobCode + '\'' +
                ", jobName='" + jobName + '\'' +
                '}';
    }

}
