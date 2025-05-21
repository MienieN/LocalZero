package shared;

public class Biking implements Action{

    private int savedCo2;
    private User user;

    private int kilometers;

    public Biking(int kilometers, User user){
        savedCo2 = kilometers * 2;
        this.user = user;
        this.kilometers = kilometers;
    }
    @Override
    public void calculateSavedCo2() {

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
}
