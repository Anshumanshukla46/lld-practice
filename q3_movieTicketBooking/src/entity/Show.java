package q3_movieTicketBooking.src.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Show {
    private long id;
    private int totalSeats;
    private List<Ticket> ticketList;
    private int bookedSeats;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ShowStatus showStatus;

    public Show() {}

    public Show(long id, int totalSeats, List<Ticket> ticketList, int bookedSeats, LocalDateTime startTime, LocalDateTime endTime, ShowStatus showStatus) {
        this.id = id;
        this.totalSeats = totalSeats;
        this.ticketList = ticketList;
        this.bookedSeats = bookedSeats;
        this.startTime = startTime;
        this.endTime = endTime;
        this.showStatus = showStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public ShowStatus getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(ShowStatus showStatus) {
        this.showStatus = showStatus;
    }
}
