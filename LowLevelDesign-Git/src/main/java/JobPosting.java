public class JobPosting {

    private int id;

    private String title;

    private String description;

    private User postedBy;


    public JobPosting(
            int id,
            String title,
            String description,
            User postedBy) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.postedBy = postedBy;
    }


    public String getTitle() {

        return title;
    }


    public String getDescription() {

        return description;
    }


    public User getPostedBy() {

        return postedBy;
    }
}
