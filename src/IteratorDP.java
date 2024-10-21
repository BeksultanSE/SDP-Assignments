import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class IteratorDP {
    public static void main(String[] args) {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("Song 1"));
        songs.add(new Song("Song 2"));
        songs.add(new Song("Song 3"));
        songs.add(new Song("Song 4"));

        Playlist playlist = new Playlist(songs);

        System.out.println("Playing songs in sequential order:");
        Iterator sequentialIterator = playlist.createSequentialIterator();
        while (sequentialIterator.hasNext()) {
            Song song = (Song) sequentialIterator.next();
            System.out.println("Now playing: " + song.getName());
        }

        System.out.println("\nPlaying songs in random order:");
        Iterator shuffleIterator = playlist.createShuffleIterator();
        while (shuffleIterator.hasNext()) {
            Song song = (Song) shuffleIterator.next();
            System.out.println("Now playing: " + song.getName());
        }
    }
}

class Song {
    private String name;

    public Song(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Playlist {
    private List<Song> songs;

    public Playlist(List<Song> songs) {
        this.songs = songs;
    }

    public Iterator createSequentialIterator() {
        return new SequentialIterator(songs);
    }

    public Iterator createShuffleIterator() {
        return new ShuffleIterator(songs);
    }
}

class SequentialIterator implements Iterator {
    private List<Song> songs;
    private int position;

    public SequentialIterator(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean hasNext() {
        return position < songs.size();
    }

    @Override
    public Song next() {
        return songs.get(position++);
    }
}

class ShuffleIterator implements Iterator {
    private List<Song> songs;
    private List<Song> shuffledSongs;

    public ShuffleIterator(List<Song> songs) {
        this.songs = songs;
        shuffle();
    }

    private void shuffle() {
        shuffledSongs = new ArrayList<>(songs);
        Collections.shuffle(shuffledSongs);
    }

    @Override
    public boolean hasNext() {
        return !shuffledSongs.isEmpty();
    }

    @Override
    public Song next() {
        return shuffledSongs.remove(0);
    }
}

