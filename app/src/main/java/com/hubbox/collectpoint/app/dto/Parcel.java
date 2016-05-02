package com.hubbox.collectpoint.app.dto;

/**
 * Created by VMakarenko on 01.05.2016.
 */
public class Parcel {
    private String name;
    private Long hubboxCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Parcel name(String name) {
        this.name = name;
        return this;
    }

    public Long getHubboxCode() {
        return hubboxCode;
    }

    public void setHubboxCode(Long hubboxCode) {
        this.hubboxCode = hubboxCode;
    }

    public Parcel hubboxCode(Long hubboxCode) {
        this.hubboxCode = hubboxCode;
        return this;
    }
}
