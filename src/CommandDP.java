public class CommandDP {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();
        TV tv = new TV();
        Stereo stereo = new Stereo();
        Light light = new Light();

        remote.setCommand(0, new TurnTVOn(tv));
        remote.setCommand(1, new SetVolume(stereo, 10));
        remote.setCommand(2, new DimLight(light, 50));

        remote.pressButton(0);
        remote.pressButton(1);
        remote.pressButton(2);
        remote.pressUndo();
    }
}

interface Command {
    void execute();
    void undo();
}

class TurnTVOn implements Command {
    private TV tv;

    public TurnTVOn(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.on();
    }

    @Override
    public void undo() {
        tv.off();
    }
}

class SetVolume implements Command {
    private Stereo stereo;
    private int prevVolume;
    private int volume;

    public SetVolume(Stereo stereo, int volume) {
        this.stereo = stereo;
        this.volume = volume;
    }

    @Override
    public void execute() {
        prevVolume = stereo.getVolume();
        stereo.setVolume(volume);
    }

    @Override
    public void undo() {
        stereo.setVolume(prevVolume);
    }
}

class DimLight implements Command {
    private Light light;
    private int prevBrightness;
    private int brightness;

    public DimLight(Light light, int brightness) {
        this.light = light;
        this.brightness = brightness;
    }

    @Override
    public void execute() {
        prevBrightness = light.getBrightness();
        light.dim(brightness);
    }

    @Override
    public void undo() {
        light.dim(prevBrightness);
    }
}


class TV {
    public void on() {
        System.out.println("TV is ON");
    }

    public void off() {
        System.out.println("TV is OFF");
    }
}

class Stereo {
    private int volume;

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Stereo volume set to " + volume);
    }

    public int getVolume() {
        return volume;
    }
}

class Light {
    private int brightness;

    public void dim(int brightness) {
        this.brightness = brightness;
        System.out.println("Light dimmed to " + brightness + "% brightness");
    }

    public int getBrightness() {
        return brightness;
    }
}


class RemoteControl {
    private Command[] commands;
    private Command undo;

    public RemoteControl() {
        commands = new Command[5];
    }

    public void setCommand(int slot, Command command) {
        commands[slot] = command;
    }

    public void pressButton(int slot) {
        commands[slot].execute();
        undo = commands[slot];
    }

    public void pressUndo() {
        undo.undo();
    }
}

