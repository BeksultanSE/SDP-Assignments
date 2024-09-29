interface Movie{
    String getTitle();
    String getType();
}

class RegularMovie implements Movie{
    private String title;
    private String type = "Regular Movie";
    public RegularMovie(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "RegularMovie{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

class IMAXMovie implements Movie{
    private String title;
    private String type = "IMAX Movie";
    public IMAXMovie(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "IMAXMovie{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

abstract class MovieFactory{
    public abstract Movie createMovie(String title);

}

class RegularMovieFactory extends MovieFactory{
    public Movie createMovie(String title) {
        return new RegularMovie(title);
    }
}

class IMAXMovieFactory extends MovieFactory{
    public Movie createMovie(String title) {
        return new IMAXMovie(title);
    }
}