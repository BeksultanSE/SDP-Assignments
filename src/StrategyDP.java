public class StrategyDP {
    public static void main(String[] args) {
        Song s1 = new Song("backinblack.mp3");
        Song s2 = new Song("legendsneverdie.mp3");
        //User gets app with standard level
        MusicPlayer mp3player = new MusicPlayer(new MediumQualityStrategy());
        mp3player.play(s1);
        //User has low quality internet
        mp3player.setStrategy(new LowQualityStrategy());
        mp3player.play(s1);
        //User gets high quality internet and premium subscription
        mp3player.setStrategy(new HighQualityStrategy());
        //switches music
        mp3player.play(s2);
    }
}

class Song{
    private String fileName;
    public Song(){
        fileName = "unknown";
    }
    public Song(String fileName){
        this.fileName = fileName;
    }
    public String getFileName(){
        return fileName;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
}

interface PlaybackStrategy {
    void stream(Song song);
}

class LowQualityStrategy implements PlaybackStrategy {
    @Override
    public void stream(Song song) {
        System.out.println("Streaming in low quality: " + song.getFileName());
    }
}

class MediumQualityStrategy implements PlaybackStrategy {
    @Override
    public void stream(Song song) {
        System.out.println("Streaming in medium quality: " + song.getFileName());
    }
}

class HighQualityStrategy implements PlaybackStrategy {
    @Override
    public void stream(Song song) {
        System.out.println("Streaming in high quality: " + song.getFileName());
    }
}

class MusicPlayer{
    private PlaybackStrategy strategy;
    public MusicPlayer(PlaybackStrategy strategy){
        this.strategy = strategy;
    }
    public void play(Song song){
        this.strategy.stream(song);
    }
    public void setStrategy(PlaybackStrategy strategy){
        this.strategy = strategy;
    }
    public PlaybackStrategy getStrategy() {
        return strategy;
    }
}

