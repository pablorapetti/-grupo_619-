package com.example.myapplication;

public class RegistroResponse {
    private String state;
    private String env;
    private String token;
    private EntidadRegistro user;

    public EntidadRegistro getUser() {
        return user;
    }

    public void setUser(EntidadRegistro user) {
        this.user = user;
    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "RegistroResponse{" +
                "state='" + state + '\'' +
                ", env='" + env + '\'' +
                ", token='" + token + '\'' +
                ", user=" + user +
                '}';
    }
}
