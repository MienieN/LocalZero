package shared;

public class PlantTrees extends ActionAbstract{
    private int savedCo2;
    private User user;
    private int treesPlanted;

    public PlantTrees(int treesPlanted, User user) {
        this.treesPlanted = treesPlanted;
        this.user = user;

        performAction();
    }

    @Override
    public void calculateSavedCo2() {
        savedCo2 = treesPlanted * 10;
    }

    @Override
    public int getSavedCo2() {
        return savedCo2;
    }

    @Override
    public User getUser() {
        return user;
    }

    public int getTreesPlanted() {
        return treesPlanted;
    }

    public String toString() {
        return user.getUsername() + " planted " + treesPlanted + " trees, saving " + savedCo2 + "kg CO2!";
    }
}