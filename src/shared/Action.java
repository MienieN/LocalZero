package shared;

import java.io.Serializable;

public interface Action extends Serializable {
    int savedCo2 = 0;
    String username = "";
    String location = "";

    void calculateSavedCo2();

    int getSavedCo2();
    String getUsername();
    String getLocation();
}
