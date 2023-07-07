package com.implemantation.dynamicpublisher.dto;

public class Reply {
    String response;

    public Reply(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "response='" + response + '\'' +
                '}';
    }
}
