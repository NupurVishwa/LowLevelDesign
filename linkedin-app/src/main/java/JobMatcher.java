import java.util.*;
public class JobMatcher {
    private JobMatchingStrategy strategy;
    public JobMatcher(JobMatchingStrategy strategy){
        this.strategy=strategy;
    }
    public void setStrategy(JobMatchingStrategy strategy){
        this.strategy=strategy;
    }
    public List<JobPosting> findMatchingJobs(User user,List<JobPosting> jobs){
        List<JobPosting> result=new ArrayList<>();
        for(JobPosting j:jobs)
            if(strategy.matches(user,j))result.add(j); return result;
    }
}
