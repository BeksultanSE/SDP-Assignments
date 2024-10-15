public class Task5 {
    public static void main(String[] args) {
        Light light = new Light();
        Thermostat thermostat = new Thermostat();
        SecuritySystem securitySystem = new SecuritySystem();
        EntertainmentSystem entertainmentSystem = new EntertainmentSystem();

        SmartHomeFacade smartHomeFacade = new SmartHomeFacade(light, thermostat, securitySystem, entertainmentSystem);

        smartHomeFacade.arriveHome();
        smartHomeFacade.leaveHome();
        smartHomeFacade.arriveHome();
        smartHomeFacade.movieMode();
        smartHomeFacade.nightMode();

    }
}

class Light {
    public void turnOn() {
        System.out.println("Lights are ON");
    }

    public void turnOff() {
        System.out.println("Lights are OFF");
    }
}

class Thermostat {
    public void setTemperature(int temperature) {
        System.out.println("Thermostat set to " + temperature + " degrees");
    }
}

class SecuritySystem {
    public void activate() {
        System.out.println("Security system is Activated");
    }

    public void deactivate() {
        System.out.println("Security system is Deactivated");
    }
}

class EntertainmentSystem {
    public void turnOn() {
        System.out.println("Entertainment system is ON");
    }

    public void turnOff() {
        System.out.println("Entertainment system is OFF");
    }

    public void setMovieMode() {
        System.out.println("Entertainment system set to MOVIE mode");
    }
}

class SmartHomeFacade {
    private Light light;
    private Thermostat thermostat;
    private SecuritySystem securitySystem;
    private EntertainmentSystem entertainmentSystem;

    public SmartHomeFacade(Light light, Thermostat thermostat, SecuritySystem securitySystem, EntertainmentSystem entertainmentSystem) {
        this.light = light;
        this.thermostat = thermostat;
        this.securitySystem = securitySystem;
        this.entertainmentSystem = entertainmentSystem;
    }

    public void leaveHome() {
        System.out.println("\nLeaving Home...");
        light.turnOff();
        thermostat.setTemperature(18);
        securitySystem.activate();
        entertainmentSystem.turnOff();
    }

    public void arriveHome() {
        System.out.println("\nArriving Home...");
        light.turnOn();
        thermostat.setTemperature(22);
        securitySystem.deactivate();
    }


    public void nightMode() {
        System.out.println("\nActivating Night Mode...");
        light.turnOff();
        thermostat.setTemperature(21);
        securitySystem.activate();
        entertainmentSystem.turnOff();
    }

    public void movieMode() {
        System.out.println("\nActivating Movie Mode...");
        light.turnOff();
        entertainmentSystem.setMovieMode();
        thermostat.setTemperature(22);
    }
}
