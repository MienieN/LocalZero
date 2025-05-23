package shared;

import java.io.Serializable;

public interface Action extends Serializable {
    int savedCo2 = 0;
    User user = null;

    void calculateSavedCo2();

    int getSavedCo2();

    User getUser();
}
