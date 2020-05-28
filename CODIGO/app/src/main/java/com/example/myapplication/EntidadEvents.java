package com.example.myapplication;

public class EntidadEvents {
    private String env;
    private String type_events;
    private String state;
    private String description;

    public EntidadEvents() {
    }

    public EntidadEvents(String env, String type_events, String state, String desc) {
        this.env = env;
        this.type_events = type_events;
        this.state = state;
        this.description = desc;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

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

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }
}
