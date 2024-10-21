import java.util.ArrayList;
import java.util.List;

public class ObserverDP {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        CurrentConditionsDisplay curD = new CurrentConditionsDisplay();
        StatisticsDisplay sD = new StatisticsDisplay();
        ForecastDisplay fD = new ForecastDisplay();

        weatherStation.addDisplay(curD);
        weatherStation.addDisplay(sD);
        weatherStation.addDisplay(fD);

        weatherStation.setWeatherData(-5, 30, 1300);
        weatherStation.setWeatherData(35, 80, 800);
        weatherStation.setWeatherData(20, 60, 1000);
    }
}

class WeatherStation {
    private List<WeatherDisplay> displays;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherStation() {
        displays = new ArrayList<>();
    }

    public void addDisplay(WeatherDisplay display) {
        displays.add(display);
    }

    public void removeDisplay(WeatherDisplay display) {
        displays.remove(display);
    }

    public void setWeatherData(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyDisplays();
    }

    private void notifyDisplays() {
        for (WeatherDisplay display : displays) {
            display.update(temperature, humidity, pressure);
        }
    }
}

interface WeatherDisplay {
    void update(float temperature, float humidity, float pressure);
}

class CurrentConditionsDisplay implements WeatherDisplay {
    private float temperature;
    private float humidity;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("Current conditions: \nTemperature is " + temperature + "°C\nHumidity is " + humidity + "% humidity.");
    }
}

class StatisticsDisplay implements WeatherDisplay {
    private float maxTemp = Float.MIN_VALUE;
    private float minTemp = Float.MAX_VALUE;
    private float sumTemp = 0;
    private int recordCount = 0;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        sumTemp += temperature;
        maxTemp = Math.max(maxTemp, temperature);
        minTemp = Math.min(minTemp, temperature);
        recordCount++;
        display();
    }

    public void display() {
        System.out.println("Average temperature: " + (sumTemp / recordCount) +
                        "\nMaximal temperature: " + maxTemp + 
                        "\nMinimal temperature: " + minTemp);
    }
}

class ForecastDisplay implements WeatherDisplay {
    private float curPressure = 1000.0f;
    private float lastPressure;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        lastPressure = curPressure;
        curPressure = pressure;
        display();
    }

    public void display() {
        System.out.print("Forecast of the day: ");
        if (curPressure == lastPressure) {
            System.out.println("Consistent state is expected.");
        }
        else if (curPressure > lastPressure) {
            System.out.println("Weather will be much more comfortable!");
        }
        else {
            System.out.println("Rapid cool with possible precipitation!");
        }
    }
}

