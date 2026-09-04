public class BookCopy {

    private final int copyId;

    private final Book book;

    private BookCopyStatus status;


    public BookCopy(int copyId, Book book) {

        this.copyId = copyId;
        this.book = book;

        this.status = BookCopyStatus.AVAILABLE;
    }


    public synchronized void borrow() {

        if (status != BookCopyStatus.AVAILABLE) {

            throw new IllegalStateException("Book copy is not available");
        }

        status = BookCopyStatus.BORROWED;
    }


    public synchronized void returnBook() {

        status = BookCopyStatus.AVAILABLE;
    }


    public int getCopyId() {
        return copyId;
    }


    public Book getBook() {
        return book;
    }


    public BookCopyStatus getStatus() {
        return status;
    }


    public boolean isAvailable() {

        return status == BookCopyStatus.AVAILABLE;
    }


    @Override
    public String toString() {

        return "BookCopy{" + "copyId=" + copyId + ", book=" + book.getTitle() + ", status=" + status + '}';
    }
}
