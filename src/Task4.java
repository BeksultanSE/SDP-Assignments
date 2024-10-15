public class Task4 {
    public static void main(String[] args){
        Pizza pizza = new MargheritaPizza();
        pizza = new CheeseTopping(pizza);


        System.out.println("1st order: " + pizza.getDescription());
        System.out.println("Total cost: $" + pizza.getCost());

        Pizza pizzaformyfriend = new VegetarianPizza();
        pizzaformyfriend = new PepperoniTopping(pizzaformyfriend);
        pizzaformyfriend = new MushroomTopping(pizzaformyfriend);

        System.out.println("2nd order: " + pizzaformyfriend.getDescription());
        System.out.println("Total cost: $" + pizzaformyfriend.getCost());
    }

}

interface Pizza {
    String getDescription();
    double getCost();
}

class MargheritaPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Margherita Pizza";
    }

    @Override
    public double getCost() {
        return 5.99;
    }
}

class VegetarianPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Vegetarian Pizza";
    }

    @Override
    public double getCost() {
        return 8.99;
    }
}

abstract class ToppingDecorator implements Pizza {
    protected Pizza pizza;

    public ToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getCost() {
        return pizza.getCost();
    }
}

class CheeseTopping extends ToppingDecorator {
    public CheeseTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " +Cheese";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.25;
    }
}

class MushroomTopping extends ToppingDecorator {
    public MushroomTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " +Mushrooms";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.50;
    }
}

class PepperoniTopping extends ToppingDecorator {
    public PepperoniTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " +Pepperoni";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.75;
    }
}
