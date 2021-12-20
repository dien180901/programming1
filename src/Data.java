import java.util.ArrayList;
import java.util.Date;

public class Data {
    private String iso_code;
    private String geographic_area;
    private ArrayList<Date> time_range;
    private ArrayList<Long> new_cases;
    private ArrayList<Long> new_deaths ;
    private ArrayList<Long> people_vaccinated;
    private ArrayList<Long> population ;

    public Data() {

        this.geographic_area = "";
        this.time_range = new ArrayList<Date>();
        this.iso_code = "";
        this.new_cases = new ArrayList<Long>();
        this.new_deaths = new ArrayList<Long>();
        this.people_vaccinated = new ArrayList<Long>();
        this.population = new ArrayList<Long>();
    }

    public Data(String iso_code, String geographic_area, ArrayList<Date> time_range, ArrayList<Long> new_cases, ArrayList<Long> new_deaths, ArrayList<Long> people_vaccinated, ArrayList<Long> population) {
        this.iso_code = iso_code;
        this.geographic_area = geographic_area;
        this.time_range = time_range;
        this.new_cases = new_cases;
        this.new_deaths = new_deaths;
        this.people_vaccinated = people_vaccinated;
        this.population = population;
    }

    public String getGeographic_area() {
        return geographic_area;
    }

    public void setGeographic_area(String geographic_area) {
        this.geographic_area = geographic_area;
    }

    public ArrayList<Date> getTime_range() {
        return time_range;
    }

    public void setTime_range(ArrayList<Date> time_range) {
        this.time_range = time_range;
    }

    public String getIso_code() {
        return iso_code;
    }

    public void setIso_code(String iso_code) {
        this.iso_code = iso_code;
    }

    public ArrayList<Long> getNew_cases() {
        return new_cases;
    }

    public void setNew_cases(ArrayList<Long> new_cases) {
        this.new_cases = new_cases;
    }

    public ArrayList<Long> getNew_deaths() {
        return new_deaths;
    }

    public void setNew_deaths(ArrayList<Long> new_deaths) {
        this.new_deaths = new_deaths;
    }

    public ArrayList<Long> getPeople_vaccinated() {
        return people_vaccinated;
    }

    public void setPeople_vaccinated(ArrayList<Long> people_vaccinated) {
        this.people_vaccinated = people_vaccinated;
    }

    public ArrayList<Long> getPopulation() {
        return population;
    }

    public void setPopulation(ArrayList<Long> population) {
        this.population = population;
    }
}
