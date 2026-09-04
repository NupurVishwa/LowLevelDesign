import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book {

    private final String title;
    private final String author;
    private final String isbn;

    private final List<BookCopy> copies;


    public Book(String title, String author, String isbn) {

        this.title = title;
        this.author = author;
        this.isbn = isbn;

        this.copies = new ArrayList<>();
    }


    public void addCopy(BookCopy bookCopy) {

        if (bookCopy == null) {
            throw new IllegalArgumentException("Book copy cannot be null");
        }

        copies.add(bookCopy);
    }


    public synchronized BookCopy getAvailableCopy() {

        for (BookCopy copy : copies) {

            if (copy.isAvailable()) {
                return copy;
            }
        }

        return null;
    }


    public int getAvailableCopiesCount() {

        int count = 0;

        for (BookCopy copy : copies) {

            if (copy.isAvailable()) {
                count++;
            }
        }

        return count;
    }


    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }


    public String getIsbn() {
        return isbn;
    }


    public List<BookCopy> getCopies() {

        return Collections.unmodifiableList(copies);
    }


    @Override
    public String toString() {

        return "Book{" + "title='" + title + '\'' + ", author='" + author + '\'' + ", isbn='" + isbn + '\'' + ", totalCopies=" + copies.size() + ", availableCopies=" + getAvailableCopiesCount() + '}';
    }
}
