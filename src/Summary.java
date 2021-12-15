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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Summary extends Data{

    private Data data ;

    public Summary(Data data) {
        this.data = data;
    }

    public static ArrayList<List<Integer>> DivideEven(ArrayList<Integer> dateArrayList , int numOfGroups){ //10, 4
        int[] group  = new int[numOfGroups];
        int remainder = dateArrayList.size()%numOfGroups; //2

        for (int i=0;i<numOfGroups;i++){
            if (i<remainder){
                group[i]= dateArrayList.size()/numOfGroups+1;
            }else{
                group[i]= dateArrayList.size()/numOfGroups;
            }
        }
        int index=0;

        ArrayList<List<Integer>> substring = new ArrayList<List<Integer>>();
        for (int i:group){
            List<Integer> arrlist2 = dateArrayList.subList(index,index+i);
            index+=i;
            substring.add(arrlist2);
        }
        return substring;
    }

    public ArrayList<List<Integer>> NumGroup(ArrayList<Integer> days, int n){
        return DivideEven(days,n);
    }

    public static ArrayList<List<Integer>> NumDay(ArrayList<Integer> days, int n){
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if (days.size()%n==0) res= DivideEven(days,n);
        return  res;

    }

    public static ArrayList<Integer> NoGroup(ArrayList<Integer> days, int n){
        return days;
    }

    public long UpToCases(ArrayList<Integer> days ){
        long total=0;
        for (int value:days){
            total+=value;
        }
        return total;
    }

    public long UpToDeaths(ArrayList<Integer> days){
        long total=0;
        for (long value:days){
            total+=value;
        }
        return total;
    }

    public long UpToVaccinateds(ArrayList<Integer> days){
        long total=0;
        for (long value:this.data.getPeople_vaccinated()){
            total+=value;
        }
        return total;
    }

    public long NewTotalCases(ArrayList<Integer> days){
        return UpToCases(days);
    }

    public long NewTotalDeath(ArrayList<Integer> days){
        return UpToDeaths(days);
    }

    public long NewTotalVaccinated(){
        return this.data.getPeople_vaccinated().get(this.data.getPeople_vaccinated().size()-1)-this.data.getPeople_vaccinated().get(this.data.getPeople_vaccinated().size()-2);
    }

}
