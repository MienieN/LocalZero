package shared;

public class ActionInitiativeTestValues {
    ActionInitiativeStorage storage;
    User testUser;

    public ActionInitiativeTestValues(ActionInitiativeStorage storage, User user) {
        this.storage = storage;
        this.testUser = user;
        addTestValues();
    }

    private void addTestValues() {
        storage.addAction(new Biking(12, "Mimmi", "Lund"));
        storage.addAction(new Composting(2, "Yrja", "Trelleborg"));
        storage.addAction(new PlantTrees(4, "Natalie", "Malmö"));
        storage.addAction(new PublicTransport(20, "Petri", "Ängelholm"));

        IInitiative testInitiative = new Initiative("Karlsson", true);
        testInitiative.setTitle("Loppis");
        testInitiative.setDescription("Garden sale with all sorts of nicknacks!");
        testInitiative.setCategory(InitiativeCategory.SELLING);
        testInitiative.setLocation("location");
        testInitiative.setDuration("Today, 9-14");
        testInitiative.setIsPublic(true);

        storage.addInitiative(testInitiative);

        IInitiative testInitiative2 = new Initiative("Svensson", true);
        testInitiative2.setTitle("Morning jog");
        testInitiative2.setDescription("Let's meet up and start the day with a light jog!");
        testInitiative2.setCategory(InitiativeCategory.FITNESS);
        testInitiative2.setLocation("location");
        testInitiative2.setDuration("Tomorrow, 6");
        testInitiative2.setIsPublic(false);

        storage.addInitiative(testInitiative2);
    }
}
