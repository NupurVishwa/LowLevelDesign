public class LinkedInDemo {
    public static void main(String[] args){
        LinkedInService service=new LinkedInService();
        User alice=new User(1,"Alice"),bob=new User(2,"Bob");
        service.registerUser(alice);service.registerUser(bob);
        alice.addSkill(new Skill("Java"));
        alice.addSkill(new Skill("Spring Boot"));
        alice.addEducation(new Education("MIT","B.Tech","CS",2015,2019));
        alice.addExperience(new Experience("Google","Software Engineer",2019,2022));
        service.sendConnectionRequest(alice,bob);bob.displayNotifications();service.acceptConnection(alice,bob);
        service.sendMessage(alice,bob,"Hi Bob, let's connect!");bob.displayMessages();JobPosting job=new JobPosting(1,"Java Backend Developer","Spring Boot role",alice);
        service.postJob(job);JobMatcher matcher=new JobMatcher(new SkillBasedJobMatching());
        for(JobPosting j:matcher.findMatchingJobs(alice,service.getJobPostings()))
            System.out.println("Matched: "+j.getTitle()); }
}
