package shared;

import java.io.Serializable;

public abstract class ActionAbstract implements Serializable {

    // Template method
    public final void performAction() {
        calculateSavedCo2();
    }

    public abstract void calculateSavedCo2();
    public abstract int getSavedCo2();
    public abstract String getUsername();

    public abstract String getLocation();
}