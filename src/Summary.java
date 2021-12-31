import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Summary extends Data{

    // Declaration
    private Data data ;
    private ArrayList<List<String>> displayData;

    // Constructor
    public Summary(Data data) {
        this.data = data;
    }

    // Getter
    public Data getData() {
        return data;
    }
    public ArrayList<List<String>> getdisplayData() {
        return displayData;
    }

    /** Get the time range from user's date input
     * This function will get all the indexes of days which are between the start and end date of user's input.
     * @param beginDay the begin date input by user
     * @param endDay the end date input by user
     * @return an Array List of day's indexes
     */
    public ArrayList<Integer> userTimeRange(Date beginDay, Date endDay) {
        ArrayList<Integer> dayIndexes = new ArrayList<>();

        // Spread the range of date input from user because in default the begin and end date are exclusive
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDay);
        cal.add(Calendar.DATE, 1);
        endDay = cal.getTime();
        cal.setTime(beginDay);
        cal.add(Calendar.DATE,-1);
        beginDay = cal.getTime();

        // Add all indexes of all days in the range of user input
        for (int i = 0; i < data.getTime_range().size(); i++) {
            if (data.getTime_range().get(i).after(beginDay) && data.getTime_range().get(i).before(endDay))
                dayIndexes.add(i);
        }
        return dayIndexes;
    }

    /** Group divided evenly
     * This function will divide the day's indexes list evenly.
     * @param dayIndexes an ArrayList contains the indexes of all the days.
     * @param numOfGroups an Integer which is the number of groups user wants the function to divide into.
     * @return the nested arrayList which includes the day's indexes
     */
    public static ArrayList<List<Integer>> DivideEven(ArrayList<Integer> dayIndexes , int numOfGroups) {
        int[] group  = new int[numOfGroups];
        int remainder = dayIndexes.size()%numOfGroups;
        for (int i=0;i<numOfGroups;i++){
            if (i<remainder){
                group[i]= dayIndexes.size()/numOfGroups+1;
            }else{
                group[i]= dayIndexes.size()/numOfGroups;
            }
        }
        int index=0;

        // Divide into groups contained in a nested arrayList
        ArrayList<List<Integer>> groupRes = new ArrayList<List<Integer>>();
        for (int i:group){
            List<Integer> arrlist2 = dayIndexes.subList(index,index+i);
            index+=i;
            groupRes.add(arrlist2);
        }
        return groupRes;
    }

    /** Number of groups
     * This function will divide the day's indexes list evenly.
     * @param dayIndexes an ArrayList contains the indexes of all the days.
     * @param n an Integer which is the number of groups user wants the function to divide into.
     * @return the nested arrayList which includes the day's indexes
     */
    public ArrayList<List<Integer>> NumGroup(ArrayList<Integer> dayIndexes, int n){
        return DivideEven(dayIndexes,n);
    }

    /** Number of days - user decides how many days they want in a group
     * This function will divide the day's indexes list.
     * @param dayIndexes an ArrayList contains the indexes of all the days.
     * @param n an Integer which is the number of days user wants the function to divide into.
     * @return the nested arrayList which includes the day's indexes
     */
    public static ArrayList<List<Integer>> NumDay(ArrayList<Integer> dayIndexes, int n){
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if (dayIndexes.size()%n==0) res= DivideEven(dayIndexes,dayIndexes.size()/n);
        return res;
    }

    /** No grouping - each day is a group
     * This function will divide the day's indexes list.
     * @param dayIndexes an ArrayList contains the indexes of all the days.
     * @return the nested arrayList which includes the day's indexes
     */
    public static ArrayList<List<Integer>> NoGroup(ArrayList<Integer> dayIndexes){
        ArrayList<List<Integer>> res = DivideEven(dayIndexes,dayIndexes.size());
        return res;
    }

    /** Up to cases
     * This function will calculate the up to number of new cases of a group.
     * Then assign the value to displayData parameter of the Summary object.
     * @param dayIndexes a nested ArrayList contains the days's indexes groups.
     */
    public void UpToCases(ArrayList<List<Integer>> dayIndexes){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        ArrayList<List<String>> res = new ArrayList<>();
        List<String> groupName = new ArrayList<>();
        List<String> value = new ArrayList<>();
        for(List<Integer> group: dayIndexes){
            long total=0;
            for(int index: group)  total+=data.getNew_cases().get(index);
            groupName.add(df.format(data.getTime_range().get(group.get(0)))+"-"+df.format(data.getTime_range().get(group.get(group.size()-1))));
            value.add(Long.toString(total));
        }
        res.add(groupName);
        res.add(value);
        displayData=res;
    }

    /** Up to deaths
     * This function will calculate the up to number of deaths of a group.
     * Then assign the value to displayData parameter of the Summary object.
     * @param dayIndexes a nested ArrayList contains the days's indexes groups.
     */
    public void UpToDeaths(ArrayList<List<Integer>> dayIndexes){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        ArrayList<List<String>> res = new ArrayList<>();
        List<String> groupName = new ArrayList<>();
        List<String> value = new ArrayList<>();
        for(List<Integer> group: dayIndexes){
            long total=0;
            for(int index: group)  total+=data.getNew_deaths().get(index);
            groupName.add(df.format(data.getTime_range().get(group.get(0)))+"-"+df.format(data.getTime_range().get(group.get(group.size()-1))));
            value.add(Long.toString(total));
        }
        res.add(groupName);
        res.add(value);
        displayData=res;
    }

    /** Up to vaccinated people
     * This function will calculate the up to number of vaccinated people of a group.
     * Then assign the value to displayData parameter of the Summary object.
     * @param dayIndexes a nested ArrayList contains the days's indexes groups.
     */
    public void UpToVaccinateds(ArrayList<List<Integer>> dayIndexes){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        ArrayList<List<String>> res = new ArrayList<>();
        List<String> groupName = new ArrayList<>();
        List<String> value = new ArrayList<>();

        for(List<Integer> group: dayIndexes){
            long total=0;
            total=data.getPeople_vaccinated().get(group.get(group.size()-1));
            groupName.add(df.format(data.getTime_range().get(group.get(0)))+"-"+df.format(data.getTime_range().get(group.get(group.size()-1))));
            value.add(Long.toString(total));
        }
        res.add(groupName);
        res.add(value);
        displayData=res;
    }

    /** New total vaccinated people
     * This function will calculate the new total number of vaccinated people of a group.
     * Then assign the value to displayData parameter of the Summary object.
     * @param dayIndexes a nested ArrayList contains the days's indexes groups.
     */
    public void NewTotalVaccinated(ArrayList<List<Integer>> dayIndexes){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        ArrayList<List<String>> res = new ArrayList<>();
        List<String> groupName = new ArrayList<>();
        List<String> value = new ArrayList<>();

        for(List<Integer> group: dayIndexes){
            long total=0;
            if (group.get(0) != 0)
                total += data.getPeople_vaccinated().get(group.get(group.size()-1)) - data.getPeople_vaccinated().get(group.get(0) - 1);
            else total = data.getPeople_vaccinated().get(group.get(0));
            groupName.add(df.format(data.getTime_range().get(group.get(0)))+"-"+df.format(data.getTime_range().get(group.get(group.size()-1))));
            value.add(Long.toString(total));
        }
        res.add(groupName);
        res.add(value);
        displayData=res;
    }
}
