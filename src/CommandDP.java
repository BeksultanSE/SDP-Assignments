public class CommandDP {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Thermostat homeThermostat = new Thermostat();
        DoorLock doorLock = new DoorLock();

        Command turnOnLights = new TurnOnLightsCommand(livingRoomLight);
        Command turnOffLights = new TurnOffLightsCommand(livingRoomLight);
        Command setThermostatTo25 = new SetThermostatCommand(homeThermostat, 25);
        Command lockDoors = new LockDoorsCommand(doorLock);

        Invoker hub = new Invoker();

        hub.setCommand(turnOnLights);
        hub.voiceCommand();
        hub.setCommand(setThermostatTo25);
        hub.voiceCommand();
        hub.setCommand(lockDoors);
        hub.voiceCommand();
        hub.setCommand(turnOffLights);
        hub.voiceCommand();
    }
}

interface Command{
    void execute();
}

class TurnOnLightsCommand implements Command {
    private Light light;

    public TurnOnLightsCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class TurnOffLightsCommand implements Command {
    private Light light;

    public TurnOffLightsCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

class SetThermostatCommand implements Command {
    private Thermostat thermostat;
    private int temperature;

    public SetThermostatCommand(Thermostat thermostat, int temperature) {
        this.thermostat = thermostat;
        this.temperature = temperature;
    }

    @Override
    public void execute() {
        thermostat.setTemperature(temperature);
    }
}

class LockDoorsCommand implements Command {
    private DoorLock doorLock;

    public LockDoorsCommand(DoorLock doorLock) {
        this.doorLock = doorLock;
    }

    @Override
    public void execute() {
        doorLock.lock();
    }
}

class Light {
    public void turnOn() {
        System.out.println("The lights are ON.");
    }

    public void turnOff() {
        System.out.println("The lights are OFF.");
    }
}

class Thermostat {
    public void setTemperature(int temperature) {
        System.out.println("Thermostat set to " + temperature + " degrees.");
    }
}

class DoorLock {
    public void lock() {
        System.out.println("The doors are LOCKED.");
    }

    public void unlock() {
        System.out.println("The doors are UNLOCKED.");
    }
}

class Invoker {
    private Command command;



    public void setCommand(Command command) {
        this.command = command;
    }

    public void voiceCommand() {
        command.execute();
    }
}

