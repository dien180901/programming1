//import java.util.ArrayList;
//
//public class Summary  {
//    private String iso_code;
//    private String geographic_area;
//    private timeRange time_range;
//    private long new_cases;
//    private long new_deaths ;
//    private long people_vaccinated;
//    private long population ;
//
//    public Summary(){
//        this.iso_code = iso_code;
//        this.geographic_area = geographic_area;
//        this.time_range = time_range;
//        this.new_cases = new_cases;
//        this.new_deaths = new_deaths;
//        this.people_vaccinated = people_vaccinated;
//        this.population = population;
//    }
//
//    public Summary(String iso_code, String geographic_area, timeRange time_range, long new_cases, long new_deaths, long people_vaccinated, long population){
//        this.iso_code = iso_code;
//        this.geographic_area = geographic_area;
//        this.time_range = time_range;
//        this.new_cases = new_cases;
//        this.new_deaths = new_deaths;
//        this.people_vaccinated = people_vaccinated;
//        this.population = population;
//    }
//
//    public String getIso_code() {
//        return iso_code;
//    }
//
//    public void setIso_code(String iso_code) {
//        this.iso_code = iso_code;
//    }
//
//    public String getGeographic_area() {
//        return geographic_area;
//    }
//
//    public void setGeographic_area(String geographic_area) {
//        this.geographic_area = geographic_area;
//    }
//
//    public timeRange getTime_range() {
//        return time_range;
//    }
//
//    public void setTime_range(timeRange time_range) {
//        this.time_range = time_range;
//    }
//
//    public long getNew_cases() {
//        return new_cases;
//    }
//
//    public void setNew_cases(long new_cases) {
//        this.new_cases = new_cases;
//    }
//
//    public long getNew_deaths() {
//        return new_deaths;
//    }
//
//    public void setNew_deaths(long new_deaths) {
//        this.new_deaths = new_deaths;
//    }
//
//    public long getPeople_vaccinated() {
//        return people_vaccinated;
//    }
//
//    public void setPeople_vaccinated(long people_vaccinated) {
//        this.people_vaccinated = people_vaccinated;
//    }
//
//    public long getPopulation() {
//        return population;
//    }
//
//    public void setPopulation(long population) {
//        this.population = population;
//    }
//
//
//}
//


public class Summary extends Data{

    private Data data ;

    public Summary(Data data) {
        this.data = data;
    }

    public long UpToCases(){
        long total=0;
        for (long value:this.data.getNew_cases()){
            total+=value;
        }
        return total;
    }

    public long UpToDeaths(){
        long total=0;
        for (long value:this.data.getNew_deaths()){
            total+=value;
        }
        return total;
    }

    public long UpToVaccinateds(){
        long total=0;
        for (long value:this.data.getPeople_vaccinated()){
            total+=value;
        }
        return total;
    }

    public long NewTotalCases(){
        return this.data.getNew_cases().get(this.data.getNew_cases().size()-1);
    }

    public long NewTotalDeath(){
        return this.data.getNew_deaths().get(this.data.getNew_deaths().size()-1);
    }

    public long NewTotalVaccinated(){
        return this.data.getPeople_vaccinated().get(this.data.getPeople_vaccinated().size()-1)-this.data.getPeople_vaccinated().get(this.data.getPeople_vaccinated().size()-2);
    }
}