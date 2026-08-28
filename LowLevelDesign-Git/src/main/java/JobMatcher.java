import java.util.ArrayList;
import java.util.List;

public class JobMatcher {

    private JobMatchingStrategy strategy;


    public JobMatcher(
            JobMatchingStrategy strategy) {

        this.strategy = strategy;
    }


    public List<JobPosting> findMatchingJobs(
            User user,
            List<JobPosting> jobs) {

        List<JobPosting> result =
                new ArrayList<>();


        for (JobPosting job : jobs) {

            if (strategy.matches(user, job)) {

                result.add(job);
            }
        }


        return result;
    }


    public void setStrategy(
            JobMatchingStrategy strategy) {

        this.strategy = strategy;
    }
}