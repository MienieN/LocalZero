package shared;

import java.io.Serializable;

public class CO2Status implements Serializable {
    private String location;
    private int co2;

    public String getLocation() { return location;}
    public void setLocation(String location) { this.location = location;}
    public int getCo2() { return co2; }
    public void setCo2(int co2) { this.co2 = co2;}
}
