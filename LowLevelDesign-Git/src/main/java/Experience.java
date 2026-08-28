class Experience {

    private String company;
    private String title;

    private int startYear;
    private int endYear;


    public Experience(
            String company,
            String title,
            int startYear,
            int endYear) {

        this.company = company;
        this.title = title;

        this.startYear = startYear;
        this.endYear = endYear;
    }

    public String getTitle() {
        return title;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getStartYear() {
        return startYear;
    }

    public String getCompany() {
        return company;
    }
}