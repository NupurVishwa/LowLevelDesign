import java.util.ArrayList;
import java.util.List;

public class Profile {

    private List<Skill> skills;

    private List<Education> educationList;

    private List<Experience> experienceList;


    public Profile() {

        skills = new ArrayList<>();

        educationList = new ArrayList<>();

        experienceList = new ArrayList<>();
    }


    public void addSkill(Skill skill) {

        skills.add(skill);
    }


    public void addEducation(
            Education education) {

        educationList.add(education);
    }


    public void addExperience(
            Experience experience) {

        experienceList.add(experience);
    }


    public List<Skill> getSkills() {

        return skills;
    }


    public List<Education> getEducationList() {

        return educationList;
    }


    public List<Experience> getExperienceList() {

        return experienceList;
    }


    public void displayProfile() {

        System.out.println(
                "\n========== PROFILE =========="
        );

        System.out.println(
                "\nSkills:"
        );

        for (Skill skill : skills) {

            System.out.println(
                    "- " + skill.getName()
            );
        }


        System.out.println(
                "\nEducation:"
        );

        for (Education education :
                educationList) {

            System.out.println(
                    "- "
                            + education.getDegree()
                            + " at "
                            + education.getInstitution()
            );
        }


        System.out.println(
                "\nExperience:"
        );

        for (Experience experience :
                experienceList) {

            System.out.println(
                    "- "
                            + experience.getTitle()
                            + " at "
                            + experience.getCompany()
            );
        }
    }
}