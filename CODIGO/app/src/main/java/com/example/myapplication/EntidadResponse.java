package com.example.myapplication;

public class EntidadResponse {
    private String type_events;
    private String state;
    private String description;
    private Integer group;

    public String getType_events() {
        return type_events;
    }

    public void setType_events(String type_events) {
        this.type_events = type_events;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "EntidadResponse{" +
                "typeevents='" + type_events + '\'' +
                ", state='" + state + '\'' +
                ", description='" + description + '\'' +
                ", group=" + group +
                '}';
    }
}
