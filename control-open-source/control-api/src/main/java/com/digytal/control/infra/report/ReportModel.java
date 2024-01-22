package com.digytal.control.infra.report;


import java.io.Serializable;
import java.time.LocalDateTime;

public class ReportModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private LocalDateTime dateTime;
    private String path;
    private String url;
    private ReportFormat format;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public ReportFormat getFormat() {
        return format;
    }
    public void setFormat(ReportFormat format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return String.format("ReportModel(id=%s, name=%s)", id, name);
    }

}

