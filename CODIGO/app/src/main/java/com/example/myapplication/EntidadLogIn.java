package com.example.myapplication;

public class EntidadLogIn {
    private String env;
    private String email;
    private String password;

    public EntidadLogIn(String env, String email, String password) {
        this.env = env;
        this.email = email;
        this.password = password;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

