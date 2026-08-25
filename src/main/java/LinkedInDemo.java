public class LinkedInDemo {

    public static void main(String[] args) {

        // Create LinkedIn service
        LinkedInService linkedInService = new LinkedInService();

        // =====================================================
        // 1. CREATE USERS
        // =====================================================

        User alice = new User(1, "Alice");
        User bob = new User(2, "Bob");
        User charlie = new User(3, "Charlie");

        linkedInService.registerUser(alice);
        linkedInService.registerUser(bob);
        linkedInService.registerUser(charlie);


        // =====================================================
        // 2. PROFILE MANAGEMENT
        // =====================================================

        alice.addSkill(new Skill("Java"));
        alice.addSkill(new Skill("Spring Boot"));
        alice.addSkill(new Skill("SQL"));

        alice.addEducation(
                new Education(
                        "MIT",
                        "B.Tech",
                        "Computer Science",
                        2015,
                        2019
                )
        );

        alice.addExperience(
                new Experience(
                        "Google",
                        "Software Engineer",
                        2019,
                        2022
                )
        );

        alice.addExperience(
                new Experience(
                        "Microsoft",
                        "Senior Software Engineer",
                        2022,
                        2025
                )
        );


        // =====================================================
        // 3. PRINT PROFILE
        // =====================================================

        alice.getProfile().displayProfile();


        // =====================================================
        // 4. SEND CONNECTION REQUEST
        // =====================================================

        System.out.println("\n--- Sending Connection Request ---");

        linkedInService.sendConnectionRequest(alice, bob);


        // Bob should receive notification
        bob.displayNotifications();


        // =====================================================
        // 5. ACCEPT CONNECTION
        // =====================================================

        System.out.println("\n--- Accepting Connection ---");

        linkedInService.acceptConnection(alice, bob);


        System.out.println(
                "Alice connections: "
                        + alice.getConnections().size()
        );

        System.out.println(
                "Bob connections: "
                        + bob.getConnections().size()
        );


        // =====================================================
        // 6. SEND MESSAGE
        // =====================================================

        System.out.println("\n--- Sending Message ---");

        linkedInService.sendMessage(
                alice,
                bob,
                "Hi Bob! Let's discuss Java."
        );


        bob.displayMessages();

        bob.displayNotifications();


        // =====================================================
        // 7. POST JOB
        // =====================================================

        System.out.println("\n--- Posting Job ---");

        JobPosting job = new JobPosting(
                101,
                "Java Backend Developer",
                "Looking for Java Spring Boot Developer",
                alice
        );

        linkedInService.postJob(job);


        // =====================================================
        // 8. SEARCH JOB
        // =====================================================

        System.out.println("\n--- Searching Jobs ---");

        for (JobPosting j :
                linkedInService.searchJobs("Java")) {

            System.out.println(
                    j.getTitle()
                            + " - "
                            + j.getDescription()
            );
        }


        // =====================================================
        // 9. STRATEGY PATTERN - JOB MATCHING
        // =====================================================

        System.out.println(
                "\n--- Job Matching using Strategy ---"
        );

        JobMatchingStrategy strategy =
                new SkillBasedJobMatching();

        JobMatcher matcher =
                new JobMatcher(strategy);

        for (JobPosting j :
                matcher.findMatchingJobs(
                        alice,
                        linkedInService.getJobPostings()
                )) {

            System.out.println(
                    "Matched Job: "
                            + j.getTitle()
            );
        }


        // =====================================================
        // 10. SEARCH USERS
        // =====================================================

        System.out.println("\n--- Searching Users ---");

        for (User user :
                linkedInService.searchUsers("Ali")) {

            System.out.println(
                    "Found user: "
                            + user.getName()
            );
        }


        // =====================================================
        // 11. DISPLAY CONNECTIONS
        // =====================================================

        System.out.println("\n--- Alice Connections ---");

        for (User user : alice.getConnections()) {

            System.out.println(
                    user.getName()
            );
        }
    }
}
