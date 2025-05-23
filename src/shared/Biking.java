package shared;

public class Biking extends ActionAbstract {
    private int savedCo2;
    private User user;
    private int kilometers;

    public Biking(int kilometers, User user){
        this.user = user;
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
    public User getUser(){
        return user;
    }

    public int getKilometers(){
        return kilometers;
    }

    public String toString(){
        return user.getUsername() + " biked " + kilometers + " km, saving " + savedCo2 + "kg CO2!";
    }
}