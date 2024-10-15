public class Task2 {
    public static void main(String[] args) {
        Device myTV = new TVDevice();
        Device myKOLONKA = new SoundSystemDevice();

        RemoteControl myRemote = new BasicRemote(myTV);
        myRemote.togglePower();
        myRemote.volumeUp();

        myRemote = new BasicRemote(myKOLONKA);
        myRemote.togglePower();
        myRemote.channelUp();
        myRemote.channelUp();
        myRemote.togglePower();

        AdvancedRemote myMoshnyiRemote = new AdvancedRemote(myTV);

        myMoshnyiRemote.togglePower(); // after turning off you cannot change properties
        myMoshnyiRemote.volumeUp();
        myMoshnyiRemote.toggleMute();
        myMoshnyiRemote.toggleMute();
        myMoshnyiRemote.togglePower();
        myMoshnyiRemote.volumeUp();
        myMoshnyiRemote.toggleMute();
        myMoshnyiRemote.volumeUp();
        myMoshnyiRemote.volumeUp();
    }
}

interface Device {
    void powerOn();
    void powerOff();
    void setChannel(int channel);
    void setVolume(int volume);
    int getChannel();
    int getVolume();
    boolean isEnabled();
}

class TVDevice implements Device {
    private int channel;
    private int volume;
    private boolean isEnabled = false;

    @Override
    public void powerOff() {
        isEnabled = false;
        System.out.println("TV device power off");
    }

    @Override
    public void powerOn() {
        System.out.println("TV device power on");
        isEnabled = true;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("TV device channel set to " + channel);
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("TV device volume set to " + volume);
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public String toString() {
        return "TV device";
    }
}

class SoundSystemDevice implements Device {
    private int volume;
    private int channel;
    private boolean isEnabled = false;

    @Override
    public void powerOn() {
        isEnabled = true;
        System.out.println("Sound device power on");
    }

    @Override
    public void powerOff() {
        System.out.println("Sound device power off");
        isEnabled = false;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("Sound device channel set to " + channel);
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Sound device volume set to " + volume);
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public String toString() {
        return "Sound System device";
    }
}

abstract class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
        System.out.println("Remote control connected to " + device);
    }

    public void togglePower(){
        if(this.device.isEnabled()){
            this.device.powerOff();
        }
        else{
            this.device.powerOn();
        }
    }

    abstract void volumeUp();
    abstract void volumeDown();
    abstract void channelUp();
    abstract void channelDown();
}

class BasicRemote extends RemoteControl {
    public BasicRemote(Device device) {
        super(device);
    }

    public void volumeUp(){
        if(this.device.isEnabled()){
            int currentVolume = this.device.getVolume();
            this.device.setVolume((currentVolume < 100 ? currentVolume + 1 : currentVolume));
        }
    }

    public void volumeDown(){
        if(this.device.isEnabled()){
            int currentVolume = this.device.getVolume();
            this.device.setVolume((currentVolume > 0 ? currentVolume - 1 : currentVolume));
        }
    }

    public void channelUp(){
        if(this.device.isEnabled()){
            int currentChannel = this.device.getChannel();
            this.device.setChannel((currentChannel < 100 ? currentChannel + 1 : 1));
        }
    }

    public void channelDown(){
        if(this.device.isEnabled()){
            int currentChannel = this.device.getChannel();
            this.device.setChannel((currentChannel > 1 ? currentChannel - 1 : 100));
        }
    }
}

class AdvancedRemote extends BasicRemote {
    public AdvancedRemote(Device device) {
        super(device);
    }

    public void toggleMute(){
        if(this.device.isEnabled())
            this.device.setVolume(0);
    }


}

