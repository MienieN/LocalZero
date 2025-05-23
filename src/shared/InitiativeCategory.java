package shared;

public enum InitiativeCategory {
    SOCIAL_GATHERING,
    FITNESS,
    SELLING,
    SHARING,
    VOLUNTEERING,
    OTHER;

    // Static method to print all enum values with numbers
    public static void printNumberedOptions() {
        InitiativeCategory[] categories = InitiativeCategory.values(); // Get all enum values
        System.out.println("-----------------------------------------------");
        System.out.println("Categories: ");
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]); // Print with 1-based index
        }

        System.out.println("Please select an option: ");
        System.out.println("-----------------------------------------------");
    }
}
