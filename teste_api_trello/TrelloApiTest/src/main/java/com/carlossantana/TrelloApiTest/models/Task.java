package com.carlossantana.TrelloApiTest.models;

import kong.unirest.json.JSONObject;

import java.util.Map;

public class Task extends JSONObject {

    private String id;
    private Double points;
    private Boolean status;
    private String approverId;

    public Task(String id, Double points, Boolean status, String approverId) {
        this.id = id;
        this.points = points;
        this.status = status;
        this.approverId = approverId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }
}
