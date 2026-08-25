public class Education {

    private String institution;

    private String degree;

    private String fieldOfStudy;

    private int startYear;

    private int endYear;


    public Education(
            String institution,
            String degree,
            String fieldOfStudy,
            int startYear,
            int endYear) {

        this.institution = institution;

        this.degree = degree;

        this.fieldOfStudy = fieldOfStudy;

        this.startYear = startYear;

        this.endYear = endYear;
    }


    public String getInstitution() {

        return institution;
    }


    public String getDegree() {

        return degree;
    }


    public String getFieldOfStudy() {

        return fieldOfStudy;
    }


    public int getStartYear() {

        return startYear;
    }


    public int getEndYear() {

        return endYear;
    }
}