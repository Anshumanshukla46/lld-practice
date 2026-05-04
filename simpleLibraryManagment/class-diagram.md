Improved:

User
- id: int
- name: String

Book
- id: int
- title: String
- totalCopies: int
- availableCopies: int

LibraryService
- userToBooks: Map<Integer, Set<Book>>   // userId → borrowed books and equals/hashCode in Book (if used in Set)

+ borrowBook(user: User, book: Book): boolean
+ returnBook(user: User, book: Book): boolean
+ getBooksByUser(user: User): List<Book>


Flow:

Borrow:
User → LibraryService: borrowBook()

LibraryService:
- lock(book)
- check availableCopies
- add to user’s set
- decrement availableCopies

Return:
User → LibraryService: borrowBook()

LibraryService:
- lock(book) as
- check availableCopies
- add to user’s set
- decrement availableCopies

---------------
Code as per this:

User
- id: int
- name: String

User
- id: int
- name: String

LibraryService
- userToBookMapping: Map<User, List<Book>>

+ borrowBook(user: User, book: Book): boolean
+ returnBook(user: User, book: Book): boolean
+ getBooksByUser(user: User): List<Book>

Flow:

Borrow
- check totalCopies
- check user doesn’t already have book
- decrease totalCopies
- add book to user’s list

Return
- check user exists
- check user has book
- remove book from list
- increase totalCopies
