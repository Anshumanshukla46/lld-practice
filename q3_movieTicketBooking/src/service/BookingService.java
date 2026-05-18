package q3_movieTicketBooking.src.service;

import q3_movieTicketBooking.src.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BookingService {

    private List<Movie> listOfAvailableMovies = new ArrayList<>();
    private List<Show> showList = new ArrayList<>();


    public Ticket bookTicket(User user,Movie movie,int seatNumber,String startTime,String endTime) {
        LocalDateTime startDateTime = getLocalDateTime(startTime);
        LocalDateTime endDateTime = getLocalDateTime(endTime);

        // find matching show
        for (Show show : showList) {
            if (show.getStartTime().equals(startDateTime)
                    && show.getEndTime().equals(endDateTime)
                    && show.getShowStatus() == ShowStatus.ACTIVE) {

                // validate seat number
                if (seatNumber <= 0|| seatNumber > show.getTotalSeats()) {
                    return null;
                }

                // locking per show
                // I synchronize on the object that owns the shared mutable booking state.
                // Since seat allocation belongs to a specific show, I lock per show instead of globally locking Movie.class or Ticket.class
                synchronized (show) {
                    for (Ticket ticket : show.getTicketList()) {
                        if (ticket.getSeatNumber() == seatNumber
                                && ticket.getTicketStatus() == TicketStatus.CONFIRMED) {
                            return null;
                        }
                    }

                    // create new ticket
                    Ticket newTicket = new Ticket();
                    newTicket.setId(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE));

                    newTicket.setUser(user);
                    newTicket.setMovie(movie);
                    newTicket.setSeatNumber(seatNumber);
                    newTicket.setTicketStatus(TicketStatus.CONFIRMED);

                    // persist booking
                    show.getTicketList().add(newTicket);

                    show.setBookedSeats(show.getBookedSeats() + 1);
                    return newTicket;
                }
            }
        }

        return null;
    }


    public TicketStatus cancelTicket(Ticket ticketToCancel) {
        for (Show show : showList) {
            synchronized (show) {
                for (Ticket ticket : show.getTicketList()) {
                    if (ticket.getId() == ticketToCancel.getId() &&
                            ticket.getTicketStatus() == TicketStatus.CONFIRMED) {
                        ticket.setTicketStatus(TicketStatus.CANCELED);
                        show.setBookedSeats(show.getBookedSeats() - 1);
                        return TicketStatus.CANCELED;
                    }
                }
            }
        }

        return TicketStatus.FAILED;
    }


    public List<Movie> browseMovies() {
        return listOfAvailableMovies;
    }


    private LocalDateTime getLocalDateTime(String date) {
        return LocalDate.parse(date).atStartOfDay();
    }
}