## Question: Design a Movie Ticket Booking System

Design a system where users can:

* view available movies
* see show timings
* book seats for a show
* cancel booked seats

The system should:

* prevent double booking of same seat
* show available seats for a show
* support multiple shows for same movie

You can ignore:

* payment
* food ordering
* user authentication
* multiple cities/theatres

---

## Expectations

Start with:

1. assumptions if needed
2. class design
3. relationships
4. explain booking flow

---

## Important

Think carefully about:

* seat availability
* concurrency
* what exactly belongs to Movie vs Show

That’s the main learning jump from previous questions.

---
---

Class Diagram:

User:
- id:long
- name:string
- email:string
- number:string

Movie:
- id:long
- name:string
- rating: integer
- shows: List<Show>

ShowStatus <<Enumeration>>
- ACTIVE
- INACTIVE

Ticket:
- id:long
- user:User (has-a with User)
- movie:Movie (has-a with Movie)
- seatNumber: integer
- status:TicketStatus


Show:
- id
- totalSeats: integer
- ticket:List<Ticket>
- bookedSeats
- startTime: LocalDateTime
- endTime: LocalDateTime
- status:ShowStatus

TicketStatus <<Enumeration>>
- CANCELED
- FAILED
- CONFIRMED

---

BookingService:
- listOfAvailableMovies: List<Movie>
- showsList: List<Show>

+ bookTicket(user:User, movie:Movie, seatNumber:Integer, startTime, endTime):Ticket
  - check if given seat is available in showList
  - add to showsList
  - TO ADD LOCKING I DON'T HOW TO PRESENT THAT IN LLD BUT IN CODING I AM THINKING THAT I CAN ADD LOCKING PER TICKET AS:
    - SYNCHRONISED(TICKET.CLASS) {...}

+ cancelTicket(ticket:Ticket):TicketStatus
  - check if ticket exist in showsList
  - then remove that ticket from showList

+ browserMovies():List<Movie>

---
**Correct Design:**

User
- id: long
- name: String
- email: String
- phoneNumber: String


Movie
- id: long
- name: String
- rating: int
- shows: List<Show>