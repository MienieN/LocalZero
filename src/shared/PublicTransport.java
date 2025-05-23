package shared;

public class PublicTransport implements Action{
    private int savedCo2;
    private User user;
    private int kilometers;

    public PublicTransport(int kilometers, User user) {
        this.user = user;
        this.kilometers = kilometers;
    }

    @Override
    public void calculateSavedCo2() {
        savedCo2 = kilometers * 2;
    }

    @Override
    public int getSavedCo2() {
        return savedCo2;
    }

    @Override
    public User getUser() {
        return user;
    }

    public int getKilometers() {
        return kilometers;
    }
}
