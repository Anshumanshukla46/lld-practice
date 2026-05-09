package q3_movieticketbooking.src.entity;

public class Ticket {
    private long id;
    private User user;
    private Movie movie;
    private int seatNumber;
    private TicketStatus ticketStatus;

    public Ticket(){}

    public Ticket(long id, User user, Movie movie, int seatNumber, TicketStatus ticketStatus) {
        this.id = id;
        this.user = user;
        this.movie = movie;
        this.seatNumber = seatNumber;
        this.ticketStatus = ticketStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
