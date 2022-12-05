package com.htpt.server.Models.Form;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log")
public class LogModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ipClient;
    private String description;
    private String time;

    public LogModel() {}

    public LogModel(String ipClient, String description, String time) {
        this.ipClient = ipClient;
        this.description = description;
        this.time = time;
    }

    public LogModel(Integer id, String ipClient, String description, String time) {
        this.id = id;
        this.ipClient = ipClient;
        this.description = description;
        this.time = time;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIpClient(String ipClient) {
        this.ipClient = ipClient;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    
}
