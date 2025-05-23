package shared;

public class Composting extends ActionAbstract {
    private int savedCo2;
    private String username;
    private String location;
    private int kg;

    public Composting(int kg, String username, String location) {
        this.username = username;
        this.location = location;
        this.kg = kg;

        performAction();
    }

    @Override
    public void calculateSavedCo2() {
        savedCo2 = kg * 3;
    }

    @Override
    public int getSavedCo2() {
        return savedCo2;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getLocation() {
        return location;
    }

    public int getKg() {
        return kg;
    }

    public String toString() {
        return username + " composted " + kg + " kg, saving " + savedCo2 + "kg CO2!";
    }
}