

public class LibraryManagementSystemDemo {

    public static void main(String[] args) {

        // ==========================================
        // 1. CREATE LIBRARY MANAGEMENT SYSTEM
        // ==========================================

        LibraryManagementSystem library = new LibraryManagementSystem();


        // ==========================================
        // 2. REGISTER NOTIFICATION OBSERVER
        // ==========================================

        library.getNotificationService().addObserver(new EmailNotificationObserver());


        // ==========================================
        // 3. CREATE BOOK
        // ==========================================

        Book effectiveJava = new Book("Effective Java", "Joshua Bloch", "978-0134685991");


        // ==========================================
        // 4. CREATE PHYSICAL BOOK COPIES
        // ==========================================

        BookCopy copy1 = new BookCopy(1, effectiveJava);

        BookCopy copy2 = new BookCopy(2, effectiveJava);


        // Add copies to the book
        effectiveJava.addCopy(copy1);
        effectiveJava.addCopy(copy2);


        // ==========================================
        // 5. ADD BOOK TO LIBRARY
        // ==========================================

        library.addBook(effectiveJava);


        // ==========================================
        // 6. CREATE MEMBERS
        // ==========================================

        Member alice = new Member(1, "Alice", "alice@gmail.com", new StudentFineStrategy());

        Member bob = new Member(2, "Bob", "bob@gmail.com", new RegularFineStrategy());


        Member charlie = new Member(3, "Charlie", "charlie@gmail.com", new RegularFineStrategy());


        // ==========================================
        // 7. ADD MEMBERS TO LIBRARY
        // ==========================================

        library.addMember(alice);
        library.addMember(bob);
        library.addMember(charlie);


        // ==========================================
        // 8. SEARCH BOOK BY TITLE
        // ==========================================

        System.out.println("\n========== SEARCH BY TITLE ==========");

        System.out.println(library.searchByTitle("Effective"));


        // ==========================================
        // 9. SEARCH BOOK BY AUTHOR
        // ==========================================

        System.out.println("\n========== SEARCH BY AUTHOR ==========");

        System.out.println(library.searchByAuthor("Joshua"));


        // ==========================================
        // 10. SEARCH BOOK BY ISBN
        // ==========================================

        System.out.println("\n========== SEARCH BY ISBN ==========");

        System.out.println(library.searchByISBN("978-0134685991"));


        // ==========================================
        // 11. ALICE BORROWS BOOK
        // ==========================================

        System.out.println("\n========== ALICE BORROWS BOOK ==========");

        library.borrowBook(1, "978-0134685991");


        // ==========================================
        // 12. BOB BORROWS BOOK
        // ==========================================

        System.out.println("\n========== BOB BORROWS BOOK ==========");

        library.borrowBook(2, "978-0134685991");


        // ==========================================
        // 13. CHARLIE TRIES TO BORROW
        // BOTH COPIES ARE ALREADY BORROWED
        // SO A RESERVATION IS CREATED
        // ==========================================

        System.out.println("\n========== CHARLIE TRIES TO BORROW ==========");

        library.borrowBook(3, "978-0134685991");


        // ==========================================
        // 14. DISPLAY ACTIVE LOANS
        // ==========================================

        System.out.println("\n========== ACTIVE LOANS ==========");

        library.getActiveLoans().forEach(System.out::println);


        // ==========================================
        // 15. ALICE RETURNS BOOK COPY 1
        // ==========================================

        System.out.println("\n========== ALICE RETURNS BOOK ==========");

        library.returnBook(1, 1);


        // ==========================================
        // 16. DISPLAY ACTIVE LOANS AFTER RETURN
        // ==========================================

        System.out.println("\n========== ACTIVE LOANS AFTER RETURN ==========");

        library.getActiveLoans().forEach(System.out::println);


        // ==========================================
        // 17. DISPLAY ALICE'S LOANS
        // ==========================================

        System.out.println("\n========== ALICE LOANS ==========");

        library.getMemberLoans(1).forEach(System.out::println);


        // ==========================================
        // 18. DISPLAY ALICE'S TRANSACTIONS
        // ==========================================

        System.out.println("\n========== ALICE TRANSACTIONS ==========");

        library.getTransactionsByMember(1).forEach(System.out::println);


        // ==========================================
        // 19. DISPLAY BOB'S TRANSACTIONS
        // ==========================================

        System.out.println("\n========== BOB TRANSACTIONS ==========");

        library.getTransactionsByMember(2).forEach(System.out::println);


        // ==========================================
        // 20. DISPLAY CHARLIE'S TRANSACTIONS
        // ==========================================

        System.out.println("\n========== CHARLIE TRANSACTIONS ==========");

        library.getTransactionsByMember(3).forEach(System.out::println);


        // ==========================================
        // 21. DISPLAY ALL TRANSACTIONS
        // ==========================================

        System.out.println("\n========== ALL TRANSACTIONS ==========");

        library.getAllTransactions().forEach(System.out::println);


        // ==========================================
        // 22. DISPLAY FINAL BOOK STATUS
        // ==========================================

        System.out.println("\n========== FINAL BOOK STATUS ==========");

        System.out.println("Book: " + effectiveJava.getTitle());

        System.out.println("Available Copies: " + effectiveJava.getAvailableCopiesCount());

        System.out.println("Copy 1 Status: " + copy1.getStatus());

        System.out.println("Copy 2 Status: " + copy2.getStatus());


        // ==========================================
        // 23. PROGRAM COMPLETED
        // ==========================================

        System.out.println("\n========== LIBRARY SYSTEM DEMO COMPLETED ==========");
    }
}