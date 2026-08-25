public interface JobMatchingStrategy {

    boolean matches(
            User user,
            JobPosting job
    );
}