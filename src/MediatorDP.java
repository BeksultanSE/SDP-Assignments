import java.util.ArrayList;
import java.util.List;

public class MediatorDP {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();

        User user1 = new PremiumUser(chatRoom, "Bradley");
        User user2 = new RegularUser(chatRoom, "Bob");
        User user3 = new RegularUser(chatRoom, "Charlie");

        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);

        user1.sendMessage("Hello everyone!");
        user2.sendMessage("Hi Bradley!");
        user3.sendMessage("Good morning!");
    }
}


interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}

class ChatRoom implements ChatMediator {
    private List<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String message, User user) {
        for (User u : users) {
            // Do not send the message to the user who sent it
            if (u != user) {
                u.receiveMessage(message);
            }
        }
    }
}

abstract class User {
    protected ChatMediator chatMediator;
    protected String name;

    public User(ChatMediator chatMediator, String name) {
        this.chatMediator = chatMediator;
        this.name = name;
    }

    public abstract void sendMessage(String message);
    public abstract void receiveMessage(String message);
}


class PremiumUser extends User {

    public PremiumUser(ChatMediator chatMediator, String name) {
        super(chatMediator, name);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(this.name + " (Premium) sends: " + message);
        chatMediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(this.name + " (Premium) receives: " + message);
    }
}


class RegularUser extends User {

    public RegularUser(ChatMediator chatMediator, String name) {
        super(chatMediator, name);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(this.name + " sends: " + message);
        chatMediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(this.name + " receives: " + message);
    }
}


