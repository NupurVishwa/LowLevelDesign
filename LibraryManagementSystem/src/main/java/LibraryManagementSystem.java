import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LibraryManagementSystem {

    private final Catalog catalog;

    private final Map<Integer, Member> members;

    private final List<Loan> loans;

    private final List<Reservation> reservations;

    private final List<Transaction> transactions;

    private final NotificationService notificationService;


    private int loanIdCounter;

    private int reservationIdCounter;

    private int transactionIdCounter;


    public LibraryManagementSystem() {

        this.catalog = new Catalog();

        this.members = new HashMap<>();

        this.loans = new ArrayList<>();

        this.reservations = new ArrayList<>();

        this.transactions = new ArrayList<>();

        this.notificationService = new NotificationService();


        this.loanIdCounter = 1;

        this.reservationIdCounter = 1;

        this.transactionIdCounter = 1;
    }


    // =====================================
    // NOTIFICATION SERVICE
    // =====================================

    public NotificationService getNotificationService() {

        return notificationService;
    }


    // =====================================
    // ADD BOOK
    // =====================================

    public void addBook(Book book) {

        catalog.addBook(book);

        System.out.println("Book added: " + book.getTitle());
    }


    // =====================================
    // ADD MEMBER
    // =====================================

    public void addMember(Member member) {

        members.put(member.getId(), member);

        System.out.println("Member added: " + member.getName());
    }


    // =====================================
    // BORROW BOOK
    // =====================================

    public synchronized Loan borrowBook(int memberId, String isbn) {

        Member member = members.get(memberId);

        if (member == null) {

            throw new IllegalArgumentException("Member not found");
        }


        Book book = catalog.searchByISBN(isbn);


        if (book == null) {

            throw new IllegalArgumentException("Book not found");
        }


        BookCopy availableCopy = book.getAvailableCopy();


        // No available copy
        if (availableCopy == null) {

            Reservation reservation = reserveBook(member, book);


            createTransaction(TransactionType.RESERVATION, member, null, BigDecimal.ZERO, "Reserved book: " + book.getTitle());


            System.out.println("No copies available.");


            System.out.println("Reservation created: " + reservation);


            return null;
        }


        // Borrow the copy
        availableCopy.borrow();


        // Create loan
        Loan loan = new Loan(loanIdCounter++, member, availableCopy, 14);


        loans.add(loan);


        member.addLoan(loan);


        // Create BORROW transaction
        createTransaction(TransactionType.BORROW, member, availableCopy, BigDecimal.ZERO, "Borrowed book: " + book.getTitle());


        // Send notification
        notificationService.notifyMember(member, "You borrowed: " + book.getTitle() + ". Due date: " + loan.getDueDate());


        return loan;
    }


    // =====================================
    // RETURN BOOK
    // =====================================

    public synchronized void returnBook(int memberId, int copyId) {
        Member member = members.get(memberId);
        if (member == null) {

            throw new IllegalArgumentException("Member not found");
        }


        Loan activeLoan = findActiveLoan(member, copyId);


        if (activeLoan == null) {

            throw new IllegalStateException("Active loan not found");
        }


        BookCopy bookCopy = activeLoan.getBookCopy();


        // Complete loan
        activeLoan.completeLoan();


        // Make book available
        bookCopy.returnBook();


        // Calculate fine
        long overdueDays = activeLoan.getOverdueDays();


        double fine = member.getFineStrategy().calculateFine(overdueDays);


        BigDecimal fineAmount = BigDecimal.valueOf(fine);


        // Create RETURN transaction
        createTransaction(TransactionType.RETURN, member, bookCopy, BigDecimal.ZERO, "Returned book: " + bookCopy.getBook().getTitle());


        // Create FINE transaction
        if (fineAmount.compareTo(BigDecimal.ZERO) > 0) {

            createTransaction(TransactionType.FINE_PAYMENT, member, bookCopy, fineAmount, "Fine for " + overdueDays + " overdue days");
        }


        System.out.println("\nBook returned successfully");


        System.out.println("Overdue days: " + overdueDays);


        System.out.println("Fine: ₹" + fineAmount);


        // Send notification
        notificationService.notifyMember(member, "Book returned successfully: " + bookCopy.getBook().getTitle());


        // Notify next reservation
        notifyNextReservation(bookCopy.getBook());
    }


    // =====================================
    // CREATE TRANSACTION
    // =====================================

    private Transaction createTransaction(TransactionType type, Member member, BookCopy bookCopy, BigDecimal amount, String description) {

        Transaction transaction = new Transaction(transactionIdCounter++, type, member, bookCopy, amount, description);


        transactions.add(transaction);


        return transaction;
    }


    // =====================================
    // RESERVE BOOK
    // =====================================

    public Reservation reserveBook(Member member, Book book) {

        Reservation reservation = new Reservation(reservationIdCounter++, member,book);


        reservations.add(reservation);


        return reservation;
    }


    // =====================================
    // FIND ACTIVE LOAN
    // =====================================

    private Loan findActiveLoan(Member member, int copyId) {

        for (Loan loan : loans) {

            if (loan.getMember().getId() == member.getId() && loan.getBookCopy().getCopyId() == copyId && !loan.isReturned()) {

                return loan;
            }
        }


        return null;
    }


    // =====================================
    // NOTIFY NEXT RESERVATION
    // =====================================

    private void notifyNextReservation(Book book) {
        for(Reservation reservation : reservations) {
            if (reservation.getBook().equals(book) && !reservation.isFulfilled()) {
                notificationService.notifyMember(reservation.getMember(), "Your reserved book is " + "now available: " + book.getTitle());
                reservation.fulfill();
                break;
            }
        }
    }


    // =====================================
    // SEARCH
    // =====================================

    public List<Book> searchByTitle(String title) {
        return catalog.searchByTitle(title);
    }


    public List<Book> searchByAuthor(String author) {

        return catalog.searchByAuthor(author);
    }


    public Book searchByISBN(String isbn) {

        return catalog.searchByISBN(isbn);
    }


    // =====================================
    // GET MEMBER LOANS
    // =====================================

    public List<Loan> getMemberLoans(int memberId) {

        Member member = members.get(memberId);

        if (member == null) {

            throw new IllegalArgumentException("Member not found");
        }
        return member.getLoans();
    }


    // =====================================
    // GET ACTIVE LOANS
    // =====================================

    public List<Loan> getActiveLoans() {

        List<Loan> activeLoans = new ArrayList<>();

        for (Loan loan : loans) {

            if (!loan.isReturned()) {

                activeLoans.add(loan);
            }
        }


        return activeLoans;
    }


    // =====================================
    // GET MEMBER TRANSACTIONS
    // =====================================

    public List<Transaction> getTransactionsByMember(int memberId) {

        List<Transaction> memberTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getMember().getId() == memberId) {

                memberTransactions.add(transaction);
            }
        }


        return memberTransactions;
    }


    // =====================================
    // GET ALL TRANSACTIONS
    // =====================================

    public List<Transaction> getAllTransactions() {

        return new ArrayList<>(transactions);
    }
}