public class StateDP {
    public static void main(String[] args) {
        Order order = new Order();

        order.payOrder();
        order.shipOrder();
        order.cancelOrder();
        order.payOrder();
        order.shipOrder();
        order.deliverOrder();
    }
}

interface State {
    void payOrder(Order order);
    void shipOrder(Order order);
    void deliverOrder(Order order);
    void cancelOrder(Order order);
}

class CancelledState implements State {

    @Override
    public void payOrder(Order order) {
        System.out.println("Order is cancelled.\nCannot pay.");
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("Order is cancelled.\nCannot ship.");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Order is cancelled.\nCannot deliver.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order is already cancelled.");
    }
}

class DeliveredState implements State {

    @Override
    public void payOrder(Order order) {
        System.out.println("Order is already delivered.\nPayment is not possible.");
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("Order is already delivered.");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Order is already delivered.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order cannot be cancelled.");
        deliverOrder(order);
    }
}

class NewState implements State {

    @Override
    public void payOrder(Order order) {
        System.out.println("Order is paid. Moving to Paid state.");
        order.setState(new PaidState());
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("Order cannot be shipped before payment.");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Order cannot be delivered before payment and shipping.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order is cancelled. Moving to Cancelled state.");
        order.setState(new CancelledState());
    }
}

class Order {
    private State state;

    public Order() {
        this.state = new NewState();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void payOrder() {
        state.payOrder(this);
    }

    public void shipOrder() {
        state.shipOrder(this);
    }

    public void deliverOrder() {
        state.deliverOrder(this);
    }

    public void cancelOrder() {
        state.cancelOrder(this);
    }
}

class PaidState implements State {

    @Override
    public void payOrder(Order order) {
        System.out.println("Order is already paid.");
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("Order is shipped.\nMoving to Shipped state.");
        order.setState(new ShippedState());
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Order cannot be delivered before shipping.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order is cancelled.\nMoving to Cancelled state.");
        order.setState(new CancelledState());
    }
}

class ShippedState implements State {

    @Override
    public void payOrder(Order order) {
        System.out.println("Order is already paid and shipped.");
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("Order is already shipped.");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Order is delivered.\nMoving to Delivered state.");
        order.setState(new DeliveredState());
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order cannot be cancelled.");
        shipOrder(order);
    }
}

