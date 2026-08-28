public class SkillBasedJobMatching
        implements JobMatchingStrategy {


    @Override
    public boolean matches(
            User user,
            JobPosting job) {

        for (Skill skill :
                user.getProfile().getSkills()) {

            String skillName =
                    skill.getName().toLowerCase();

            String jobTitle =
                    job.getTitle().toLowerCase();

            String description =
                    job.getDescription().toLowerCase();


            if (jobTitle.contains(skillName)
                    || description.contains(skillName)) {

                return true;
            }
        }

        return false;
    }
}
