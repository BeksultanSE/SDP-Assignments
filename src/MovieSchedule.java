public interface MovieSchedule extends Cloneable{
    MovieSchedule clone();
    void setMovie(Movie movie);
}

class StandardSchedule implements MovieSchedule{
    private String time;
    private Movie movie;
    public StandardSchedule(){

    }
    public StandardSchedule(String time, Movie movie){
        this.time = time;
        this.movie = movie;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getTime() {
        return time;
    }

    public Movie getMovie() {
        return movie;
    }
    @Override
    public MovieSchedule clone(){
        return new StandardSchedule(this.time, this.movie);
    }

    @Override
    public String toString() {
        return "StandardSchedule{" +
                "time='" + time + '\'' +
                ", movie=" + movie +
                '}';
    }
}
