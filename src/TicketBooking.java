public class TicketBooking {
    private String MovieTitle;
    private String SeatNumber;
    private String SnackCombo;

    private TicketBooking(TicketBookingBuilder builder) {
        this.MovieTitle = builder.MovieTitle;
        this.SeatNumber = builder.SeatNumber;
        this.SnackCombo = builder.SnackCombo;
    }

    public void setMovieTitle(String movieTitle) {
        this.MovieTitle = movieTitle;
    }
    public void setSeatNumber(String seatNumber) {
        this.SeatNumber = seatNumber;
    }
    public void setSnackCombo(String snackCombo) {
        this.SnackCombo = snackCombo;
    }
    public String getMovieTitle() {
        return this.MovieTitle;
    }
    public String getSeatNumber() {
        return this.SeatNumber;
    }
    public String getSnackCombo() {
        return this.SnackCombo;
    }
    public static class TicketBookingBuilder{
        private String MovieTitle;
        private String SeatNumber;
        private String SnackCombo;

        public TicketBookingBuilder(){

        }

        public TicketBookingBuilder setMovieTitle(String movieTitle){
            this.MovieTitle = movieTitle;
            return this;
        }
        public TicketBookingBuilder setSeatNumber(String seatNumber){
            this.SeatNumber = seatNumber;
            return this;
        }
        public TicketBookingBuilder setSnackCombo(String snackCombo){
            this.SnackCombo = snackCombo;
            return this;
        }
        public TicketBooking build(){
            return new TicketBooking(this);
        }
    }

    @Override
    public String toString() {
        return "TicketBooking{" +
                "MovieTitle='" + MovieTitle + '\'' +
                ", SeatNumber='" + SeatNumber + '\'' +
                ", SnackCombo='" + SnackCombo + '\'' +
                '}';
    }
}

