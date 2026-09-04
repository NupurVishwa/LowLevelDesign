import java.util.ArrayList;
import java.util.List;

public class Catalog {

    private final List<Book> books;


    public Catalog() {

        books = new ArrayList<>();
    }

    public void addBook(Book book) {

        books.add(book);
    }

    public List<Book> searchByTitle(String title) {

        List<Book> result = new ArrayList<>();

        for (Book book : books) {

            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {

                result.add(book);
            }
        }


        return result;
    }


    public List<Book> searchByAuthor(String author) {

        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase()) )
            {

                result.add(book);
            }
        }


        return result;
    }


    public Book searchByISBN(String isbn) {

        for (Book book : books) {

            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }

        return null;
    }
}