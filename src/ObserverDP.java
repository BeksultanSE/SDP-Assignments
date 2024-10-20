import java.util.*;

public class ObserverDP {
    public static void main(String[] args) {
        Stock st1 = new Stock("Retail", 100);
        Stock st2 = new Stock("Institutional", 100);

        StockMarket stockMarket = new StockMarket(st1, st2);

        Investor i1 = new InstitutionalInvestor("George");
        stockMarket.stocks.subscribe(st2, i1);
        Investor i2 = new InstitutionalInvestor("Michael");
        stockMarket.stocks.subscribe(st2, i2);
        Investor i3 = new RetailInvestor("John");
        stockMarket.stocks.subscribe(st1, i3);

        stockMarket.changeInPrice(st1, 85);
        stockMarket.changeInPrice(st2, 60);


    }
}

class Stock{
    private String name;
    private double price;
    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
}

class StockManager {
    Map<Stock, List<Investor>> observers = new HashMap();

    public StockManager(Stock... stocks) {
        for (Stock stock : stocks) {
            this.observers.put(stock, new ArrayList());
        }
    }

    public void subscribe(Stock stock, Investor observer) {
        List<Investor> investors = this.observers.get(stock);
        investors.add(observer);
    }

    public void unsubscribe(Stock stock, Investor observer) {
        List<Investor> investors = this.observers.get(stock);
        investors.remove(observer);
    }

    public void notify(Stock stock) {
        List<Investor> investors = this.observers.get(stock);
        for (Investor investor : investors){
            investor.update(stock);
        }
    }
}

class StockMarket {
    public StockManager stocks;

    public StockMarket(Stock... stocks) {
        this.stocks = new StockManager(stocks);
    }

    public void changeInPrice(Stock stock, double price) {
        stock.setPrice(price);
        this.stocks.notify(stock);
    }
}



interface Investor {
    void update(Stock stock);
}

class RetailInvestor implements Investor {
    private String name;
    public RetailInvestor(String name) {
        this.name = name;
    }
    @Override
    public void update(Stock stock) {
        System.out.println("Notification to Retail Investor Mr. " + name +
                ":\nRetail stock " + stock.getName() + ": has changed its price to " + stock.getPrice());
    }
}

class InstitutionalInvestor implements Investor {
    private String name;
    public InstitutionalInvestor(String name) {
        this.name = name;
    }
    @Override
    public void update(Stock stock) {
        System.out.println("Notification to Institutional Investor Mr. " + name +
                ":\nInstitutional stock " + stock.getName() + ": has changed its price to " + stock.getPrice());
    }
}

