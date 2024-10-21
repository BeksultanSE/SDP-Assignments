public class StrategyDP {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.setPaymentStrategy(new CreditCardPayment("7777-1111-1111-7777", "John Wick"));
        cart.checkout(100000000);

        cart.setPaymentStrategy(new PayPalPayment("durovpavel@mail.ru"));
        cart.checkout(5000.0);

        cart.setPaymentStrategy(new CryptoPayment("q4its123me1wqd34f341ds"));
        cart.checkout(10.0);
    }
}

interface PaymentStrategy {
    void processPayment(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Credit card payment of $" + amount + " for " + cardHolderName);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("PayPal payment of $" + amount + " for account: " + email);
    }
}

class CryptoPayment implements PaymentStrategy {
    private String walletAddress;

    public CryptoPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Cryptocurrency payment of $" + amount + " for wallet: " + walletAddress);
    }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(double amount) {
        if (paymentStrategy == null) {
            System.out.println("Please select a payment method!");
        } else {
            paymentStrategy.processPayment(amount);
        }
    }
}
