package shared;

public class Biking extends ActionAbstract {
    private int savedCo2;
    private String username;
    private String location;
    private int kilometers;

    public Biking(int kilometers, String username, String location){
        this.username = username;
        this.location = location;
        this.kilometers = kilometers;

        performAction();
    }

    @Override
    public void calculateSavedCo2() {
        savedCo2 = kilometers * 4;
    }

    @Override
    public int getSavedCo2() {
        return savedCo2;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getLocation() { return location; }

    public int getKilometers(){
        return kilometers;
    }

    public String toString(){
        return username + " biked " + kilometers + " km, saving " + savedCo2 + "kg CO2!";
    }
}