package shared;

public class PlantTrees extends ActionAbstract{
    private int savedCo2;
    private String username;
    private String location;
    private int treesPlanted;
    private int likes = 0;

    public PlantTrees(int treesPlanted, String username, String location) {
        this.username = username;
        this.treesPlanted = treesPlanted;

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
    public String getUsername() {
        return username;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void likeAction() {
        likes++;
    }

    @Override
    public int getLikes() {
        return likes;
    }

    public int getTreesPlanted() {
        return treesPlanted;
    }

    public String toString() {
        return username+ " planted " + treesPlanted + " trees, saving " + savedCo2 + "kg CO2! Likes: " + likes;
    }
}