MOVIE TICKET BOOKING SYSTEM
========================


ASSUMPTIONS
-----------
1. One movie can have multiple shows
2. Each show has separate seat availability
3. One seat can only have one CONFIRMED ticket
4. Ignore:
    - payment
    - food ordering
    - authentication
    - multiple theatres/cities
5. In-memory implementation


--------------------------------------------------
CLASS DIAGRAM
--------------------------------------------------


Movie
------
- id: long
- name: String
- rating: double
- shows: List<Show>


Explanation:
- Movie only stores movie-related information
- Movie has multiple shows
- Seat booking DOES NOT belong to Movie because different shows of same movie
  have different seat occupancy


--------------------------------------------------


Show
-----
- id: long
- movie: Movie
- startTime: LocalDateTime
- endTime: LocalDateTime
- totalSeats: int
- bookedSeats: Map<Integer, Ticket>
- status: ShowStatus


Explanation:
- Show is the MOST IMPORTANT entity
- Show owns:
    - seat allocation
    - booking state
    - ticket mapping

Why Map<Integer, Ticket> ?
- Key   = seatNumber
- Value = booked ticket

Benefits:
- O(1) seat lookup
- easy duplicate prevention
- easy seat availability check

Example:
bookedSeats:
{
1 -> TicketA,
5 -> TicketB
}

means:
seat 1 and 5 are booked


--------------------------------------------------


Ticket
-------
- id: long
- userName: String
- seatNumber: int
- bookingTime: LocalDateTime
- status: TicketStatus
- show: Show


Explanation:
- Ticket belongs to ONE specific show
- Ticket references Show instead of Movie
  because booking always happens for
  one particular show timing


--------------------------------------------------


ShowStatus <<Enumeration>>
---------------------------
- ACTIVE
- INACTIVE


Explanation:
- ACTIVE   -> booking allowed
- INACTIVE -> booking stopped


--------------------------------------------------


TicketStatus <<Enumeration>>
-----------------------------
- CONFIRMED
- CANCELED


Explanation:
- CONFIRMED -> active booking
- CANCELED  -> canceled ticket


--------------------------------------------------


BookingService
---------------
- movies: List<Movie>
- shows: List<Show>


+ browseMovies(): List<Movie>

+ getAvailableSeats(showId: long): List<Integer>

+ bookTicket(userName: String,showId: long,seatNumber: int): Ticket

+ cancelTicket(ticketId: long): boolean

--------------------------------------------------
RELATIONSHIPS
--------------------------------------------------


Movie --------> Show
One-to-Many

Explanation:
- One movie can have multiple shows


Show --------> Ticket
One-to-Many

Explanation:
- One show can have multiple tickets


Ticket --------> Show
Many-to-One

Explanation:
- Multiple tickets belong to one show


--------------------------------------------------
BOOKING FLOW
--------------------------------------------------


1. User selects:
    - movie
    - show
    - seat number


2. BookingService finds:
    - corresponding Show object


3. Lock per show:

   synchronized(show)

Explanation:
- prevents double booking
- protects shared mutable state:
    - bookedSeats map

Why lock on show?
- seat allocation belongs to show
- different shows should book independently

Why NOT Movie.class ?
- would block ALL movie bookings globally
- unnecessary large lock scope


4. Check:
   if seatNumber already exists in:
   bookedSeats map

If yes:
reject booking


5. Create Ticket object


6. Add ticket:

   bookedSeats.put(seatNumber, ticket)


7. Return CONFIRMED ticket


--------------------------------------------------
SEAT AVAILABILITY
--------------------------------------------------


Seat is AVAILABLE if:
seatNumber NOT present in bookedSeats map


Example:

totalSeats = 5

bookedSeats:
{
2 -> TicketA,
4 -> TicketB
}

Available seats:
1, 3, 5


--------------------------------------------------
IMPORTANT DESIGN INSIGHT
--------------------------------------------------


The MOST important realization of this problem is:

Seat state belongs to SHOW
NOT MOVIE

Because:
same movie can have multiple shows
and each show has separate bookings.
