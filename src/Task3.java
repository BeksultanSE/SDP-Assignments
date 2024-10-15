import java.util.ArrayList;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        Menu breakfastMenu = new Menu("Breakfast");
        breakfastMenu.add(new MenuItem("Eggs", "Scrambled eggs", 3.99));
        breakfastMenu.add(new MenuItem("Pancakes", "Fluffy pancakes", 2.99));

        Menu lunchMenu = new Menu("Lunch");
        lunchMenu.add(new MenuItem("Burger", "Juicy burger", 5.99));
        lunchMenu.add(new MenuItem("Salad", "Fresh salad", 4.99));

        Menu dinnerMenu = new Menu("Dinner");
        dinnerMenu.add(new MenuItem("Steak", "Grilled steak", 12.99));
        dinnerMenu.add(new MenuItem("Shrimp", "Garlic shrimp", 10.99));

        Menu mainMenu = new Menu("Main Menu");
        mainMenu.add(breakfastMenu);
        mainMenu.add(lunchMenu);
        mainMenu.add(dinnerMenu);

        lunchMenu.print();
        mainMenu.print();
    }
}

abstract class MenuComponent {
    abstract String getName();
    abstract String getDescription();
    abstract double getPrice();
    void print(){
        System.out.println(getName());
        System.out.println('-' + getDescription());
        System.out.println("$" + getPrice());
    }
}

class MenuItem extends MenuComponent {
    private String name;
    private String description;
    private double price;

    public MenuItem(String name, String description, double price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

class Menu extends MenuComponent {
    private String name;
    private List<MenuComponent> menuComponents;
    public Menu(String name) {
        this.name = name;
        this.menuComponents = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    String getDescription() {
        return "";
    }

    @Override
    double getPrice() {
        double total = 0;
        for (MenuComponent menuComponent : menuComponents) {
            total += menuComponent.getPrice();
        }
        return total;
    }

    @Override
    void print() {
        System.out.println(getName());
        for(MenuComponent menuComponent : menuComponents){
            menuComponent.print();
        }
    }

    public void add(MenuComponent menuComponent){
        menuComponents.add(menuComponent);
    }

    public void remove(MenuComponent menuComponent){
        menuComponents.remove(menuComponent);
    }
}

