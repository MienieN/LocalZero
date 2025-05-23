package shared;

public class Composting implements Action{
    private int savedCo2;
    private User user;
    private int kg;

    public Composting(int kg, User user) {
        this.user = user;
        this.kg = kg;

        calculateSavedCo2();
    }

    @Override
    public void calculateSavedCo2() {
        savedCo2 = kg * 3;
    }

    @Override
    public int getSavedCo2() {
        return 0;
    }

    @Override
    public User getUser() {
        return null;
    }
}
