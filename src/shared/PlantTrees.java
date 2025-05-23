package shared;

public class PlantTrees implements Action{
    private int savedCo2;
    private User user;
    private int treesPlanted;

    public PlantTrees(int treesPlanted, User user) {
        this.treesPlanted = treesPlanted;
        this.user = user;

        calculateSavedCo2();
    }

    @Override
    public void calculateSavedCo2() {
        savedCo2 = treesPlanted * 10;
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
