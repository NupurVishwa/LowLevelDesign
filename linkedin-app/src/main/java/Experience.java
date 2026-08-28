public class Experience {
    private final String company,title;
    private final int startYear,endYear;
    public Experience(String company,String title,int startYear,int endYear){
        this.company=company;
        this.title=title;
        this.startYear=startYear;
        this.endYear=endYear;
    }
    public String getCompany(){
        return company;
    }
    public String getTitle(){
        return title;
    }
    public int getStartYear(){
        return startYear;
    }
    public int getEndYear(){
        return endYear;
    }
}
