package com.example.myapplication;

public class EventsResponse {
    private String state;
    private String env;
    private EntidadResponse event;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public EntidadResponse getEvent() {
        return event;
    }

    public void setEvent(EntidadResponse event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "EventsResponse{" +
                "state='" + state + '\'' +
                ", env='" + env + '\'' +
                ", event=" + event +
                '}';
    }
}
