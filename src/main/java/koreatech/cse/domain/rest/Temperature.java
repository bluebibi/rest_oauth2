package koreatech.cse.domain.rest;

import java.sql.Date;

public class Temperature {
    private int id;
    private String sensorId;
    private float temperature;
    private Date datetime;
    private String location;

    public int getId() {
        return id;
    }

    public String getSensorId() {
        return sensorId;
    }

    public float getTemperature() {
        return temperature;
    }

    public Date getDatetime() {
        return datetime;
    }

    public String getLocation() {
        return location;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
