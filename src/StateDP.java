import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StateDP {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        while(true){
            machine.change();
        }
    }
}

interface State{
    void insertCoin(VendingMachine vm);
    void selectItem(VendingMachine vm);
    void dispenseItem(VendingMachine vm);
    void handleChange(VendingMachine vm);
}

class NoCoinState implements State{
    protected int coin;
    @Override
    public void insertCoin(VendingMachine vm) {
        Scanner sc = new Scanner(System.in);
        boolean validCoin = false;

        while (!validCoin) {
            System.out.println("Insert some coins:");
            int coinInput = sc.nextInt();
            sc.nextLine();
            if (coinInput > 0) {
                coin += coinInput;
                validCoin = true;
                System.out.println("Coin(s) accepted: " + coinInput);

            } else {
                System.out.println("Invalid input. Number of coins must be greater than zero!");
            }
        }
    }
    @Override
    public void selectItem(VendingMachine vm) {
        System.out.println("Invalid function!");
    }
    @Override
    public void dispenseItem(VendingMachine vm) {
        System.out.println("Invalid function!");
    }
    @Override
    public void handleChange(VendingMachine vm) {
        vm.getItems();
        insertCoin(vm);
        vm.setState(new HasCoinState(coin));
    }
}

class HasCoinState extends NoCoinState implements State{
    public HasCoinState(int coin) {
        this.coin = coin;
    }

    @Override
    public void selectItem(VendingMachine vm) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Select an item(enter a number):");

        int itemIndex = sc.nextInt();
        itemIndex--;
        System.out.println("Selected item: " + itemIndex);
        sc.nextLine();


        if (itemIndex >= 0 && itemIndex < vm.getSize()) {
            if(vm.getItemCount(itemIndex) == 0){
                vm.setState(new SoldOutState(coin));
                return;
            }
            if(coin >= 5) {
                coin -= 5;
                vm.setCoins(vm.getCoins() + 5);
                vm.setState(new DispensingState(itemIndex, coin));
            }
            else if(coin == 0){
                vm.setState(new NoCoinState());
            }
            else{
                System.out.println("Not enough coins!");
                insertCoin(vm);
            }
        } else {
            System.out.println("Invalid item selection!");
        }
    }

    @Override
    public void handleChange(VendingMachine vm) {
        System.out.println("Coins remaining: " + coin);
        selectItem(vm);
    }

}

class SoldOutState extends NoCoinState implements State{
    public SoldOutState(int coin) {
        this.coin = coin;
    }
    @Override
    public void dispenseItem(VendingMachine vm) {
        System.out.println("Sold out!");
    }
    @Override
    public void insertCoin(VendingMachine vm) {
        System.out.println("Sold out!");
    }
    @Override
    public void selectItem(VendingMachine vm) {
        vm.setState(new HasCoinState(coin));
    }
    @Override
    public void handleChange(VendingMachine vm) {
        System.out.println("This item is sold out! Please select another one!");
        selectItem(vm);
    }
}

class DispensingState implements State{
    private int dispensingItem;
    private int coin;
    public DispensingState(int itemIndex, int coin) {
        this.dispensingItem = itemIndex;
        this.coin = coin;
    }
    @Override
    public void insertCoin(VendingMachine vm) {
        System.out.println("Dispensing!");
    }

    @Override
    public void selectItem(VendingMachine vm) {
        System.out.println("Dispensing!");
    }

    @Override
    public void dispenseItem(VendingMachine vm) {
        vm.setItemCount(dispensingItem, vm.getItemCount(dispensingItem) - 1);
        System.out.println("#" + (dispensingItem + 1) + " item is dispensed!");
        if(coin > 0){
            vm.setState(new HasCoinState(coin));
        }
        else{
            vm.setState(new NoCoinState());
        }
    }

    @Override
    public void handleChange(VendingMachine vm) {
        dispenseItem(vm);
    }
}

class VendingMachine {
    private State state;
    private List<Integer> items;
    private int coins = 0;

    public VendingMachine() {
        state = new NoCoinState();
        items = new ArrayList<Integer>(Arrays.asList(5, 5, 5, 5, 5, 5, 5, 5, 5, 5));
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getItemCount(int index){
        return items.get(index);
    }

    public void setItemCount(int index, int count){
        items.set(index, count);
    }

    public int getSize(){
        return items.size();
    }

    public void getItems() {

        for (int i = 0; i < items.size(); i++) {
            System.out.println("#" + (i+1) + " item cost: 5 coins, Available: " + items.get(i));
        }

    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getCoins(){
        return coins;
    }

    public void change(){
        state.handleChange(this);
    }
}
