import java.util.ArrayList;
import java.util.List;

public class Task7 {
    public static void main(String[] args) {
        OnlineCourse course = new OnlineCourse();

        course.addLecture(new ProxyVideoLecture("Lecture 1: Introduction", "intro.mp4"));
        course.addLecture(new ProxyVideoLecture("Lecture 2: Design Patterns", "design_patterns.mp4"));
        course.addLecture(new ProxyVideoLecture("Lecture 3: Creational Design Patterns", "creational.mp4"));

        System.out.println("Showing lecture info:");
        course.showLectureInfo();

        System.out.println("\nPlaying Lecture 1:");
        course.playLecture(0);

        System.out.println("\nPlaying Lecture 3:");
        course.playLecture(2);

        System.out.println("\nPlaying Lecture 2:");
        course.playLecture(1);
    }
}

interface VideoLecture {
    String getInfo();
    void play();
}

class RealVideoLecture implements VideoLecture {
    private String title;
    private String fileName;

    public RealVideoLecture(String title, String fileName) {
        this.title = title;
        this.fileName = fileName;
        System.out.println("Loading video file: " + fileName);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getInfo() {
        return "Video Title: " + title;
    }

    @Override
    public void play() {
        System.out.println("Playing video: " + title);
    }
}


class ProxyVideoLecture implements VideoLecture {
    private String title;
    private String fileName;
    private RealVideoLecture realVideoLecture;

    public ProxyVideoLecture(String title, String fileName) {
        this.title = title;
        this.fileName = fileName;
    }

    @Override
    public String getInfo() {
        return "Video Title: " + title + " (Proxy)";
    }

    @Override
    public void play() {
        if (realVideoLecture == null) {
            realVideoLecture = new RealVideoLecture(title, fileName);
        }
        realVideoLecture.play();
    }
}



class OnlineCourse {
    private List<VideoLecture> lectures = new ArrayList<>();

    public void addLecture(VideoLecture lecture) {
        lectures.add(lecture);
    }

    public void showLectureInfo() {
        for (VideoLecture lecture : lectures) {
            System.out.println(lecture.getInfo());
        }
    }

    public void playLecture(int index) {
        if (index >= 0 && index < lectures.size()) {
            lectures.get(index).play();
        } else {
            System.out.println("Invalid lecture index");
        }
    }
}