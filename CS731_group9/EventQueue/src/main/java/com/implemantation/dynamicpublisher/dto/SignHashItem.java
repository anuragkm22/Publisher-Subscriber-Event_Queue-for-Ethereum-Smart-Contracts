package com.implemantation.dynamicpublisher.dto;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SignHash")
public class SignHashItem {
    private String topic_id;
    private byte[] signHash;
    private byte[] key;
    public SignHashItem(String topic_id, byte[] signHash, byte[] key) {
        this.topic_id = topic_id;
        this.signHash = signHash;
        this.key = key;
    }

    public String getTopic_id() {
        return topic_id;
    }
    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }
    public byte[] getSignHash() {
        return signHash;
    }
    public void setSignHash(byte[] signHash) {
        this.signHash = signHash;
    }
    public byte[] getKey() {
        return key;
    }
    public void setKey(byte[] key) {this.key = key; }
}
