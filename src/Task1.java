public class Task1 {
    public static void main(String[] args) {
        AdvancedAudioPlayer advancedAudioPlayer = new AdvancedAudioPlayer();
        AudioPlayer mp3Player = new MP3Player();
        AudioPlayer wavPlayer = new AudioAdapter(advancedAudioPlayer);
        AudioPlayer aacPlayer = new AudioAdapter(advancedAudioPlayer);

        mp3Player.play("mp3", "song.mp3");
        wavPlayer.play("wav", "song.wav");
        aacPlayer.play("aac", "song.aac");
    }
}

interface AudioPlayer {
    void play(String audioType, String fileName);
}

class MP3Player implements AudioPlayer {
    private String fileName;
    public void play(String audioType, String fileName){
        if(audioType.equals("mp3")) {
            System.out.println("Playing mp3 audio: " + fileName);
        }
        else{
            System.out.println("This audio type does not supported!");
        }
    }
}

interface WAVPlayer{
    void playWAV(String fileName);
}

interface AACPlayer{
    void playAAC(String fileName);
}

class AdvancedAudioPlayer implements WAVPlayer, AACPlayer{

    public AdvancedAudioPlayer() {

    }

    @Override
    public void playWAV(String fileName) {
        System.out.println("Playing wav audio: " + fileName);
    }

    @Override
    public void playAAC(String fileName) {
        System.out.println("Playing aac audio: " + fileName);
    }
}

class AudioAdapter implements AudioPlayer{
    private AdvancedAudioPlayer advancedAudioPlayer;
    public AudioAdapter(AdvancedAudioPlayer advancedAudioPlayer) {
        this.advancedAudioPlayer = advancedAudioPlayer;
    }
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equals("wav")) {
            advancedAudioPlayer.playWAV(fileName);
        }
        else if(audioType.equals("aac")) {
            advancedAudioPlayer.playAAC(fileName);
        }
        else{
            System.out.println("This audio type does not supported!");
        }
    }
}


