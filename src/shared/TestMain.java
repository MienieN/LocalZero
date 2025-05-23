package shared;

import java.util.ArrayList;

//Just for testing. Will remove later.
public class TestMain {

    public static void main(String[] args) {
        ArrayList<String> roles = new ArrayList<>();
        roles.add("community president");
        roles.add("awesome person");

        User user1 = new User("Pontus", "dekadens", "pontus@gmail.com", "Germany", roles, false);
        User user2 = new User("Laila", "barbara", "laila@gmail.com", "Guatemala", roles, false);
        User user3 = new User("Birgitta", "kahana", "birgitta@gmail.com", "Finland", roles, false);

        ArrayList<User> recipients = new ArrayList<>();
        recipients.add(user2);
        recipients.add(user3);

        ArrayList<User> recipients2 = new ArrayList<>();
        recipients2.add(user1);
        recipients2.add(user3);

        Notification notification = new Notification("Clean the beach", MessageType.NEW_INITIATIVE, user1, recipients, null);
        notification.displayMessage();
        System.out.println("Skickat från " + notification.getSender() + " till " + notification.getRecipients() + "\n\n");


        DirectMessage dm = new DirectMessage("Hej, hur e läget?", MessageType.DIRECT_MESSAGE, user2, recipients2);
        dm.displayMessage();
        dm.setRead(true);
        System.out.println("DM is read: " + dm.isRead());
        System.out.println("Skickat från " + dm.getSender() + " till " + dm.getRecipients() + "\n\n");

        ArrayList<User> singleRecepient = new ArrayList<>();
        singleRecepient.add(user2);
        Notification commentNotif = new Notification("Det här var så bra!!!", MessageType.COMMENT, user3, singleRecepient, "Clean the beach");
        commentNotif.displayMessage();
        System.out.println("Skickat från " + commentNotif.getSender() + " till " + commentNotif.getRecipients() + "\n\n");
    }
}
