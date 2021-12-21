import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Summary extends Data{

    private Data data ;

    // Constructor
    public Summary(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    // Get the index of date in the time_range array list of data object
    public ArrayList<Integer> userTimeRange(Date beginDay, Date endDay) {
        ArrayList<Integer> dayIndexes = new ArrayList<>();
        for (int i = 0; i < data.getTime_range().size(); i++) {
            if (data.getTime_range().get(i).after(beginDay) && data.getTime_range().get(i).before(endDay))
                dayIndexes.add(i);
        }
        return dayIndexes; //[1,2,3]
    }

    // Group divided evenly
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

        ArrayList<List<Integer>> groupRes = new ArrayList<List<Integer>>();
        for (int i:group){
            List<Integer> arrlist2 = dayIndexes.subList(index,index+i);
            index+=i;
            groupRes.add(arrlist2);
        }
        return groupRes;
    }

    // Number of groups
    public ArrayList<List<Integer>> NumGroup(ArrayList<Integer> dayIndexes, int n){
        return DivideEven(dayIndexes,n);
    }

    //Number of days
    public static ArrayList<List<Integer>> NumDay(ArrayList<Integer> dayIndexes, int n){
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if (dayIndexes.size()%n==0) res= DivideEven(dayIndexes,dayIndexes.size()/n);
        return res;
    }

    // No grouping
    public static ArrayList<List<Integer>> NoGroup(ArrayList<Integer> dayIndexes){
//        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
//        res.add(dayIndexes);
//        return res;
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        res= DivideEven(dayIndexes,dayIndexes.size());
        return res;
    }

    public ArrayList<List<String>> UpToCases(ArrayList<List<Integer>> dayIndexes){
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
        return res;
    }

    public ArrayList<List<String>> UpToDeaths(ArrayList<List<Integer>> dayIndexes){
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
        return res;
    }

    public ArrayList<List<String>> UpToVaccinateds(ArrayList<List<Integer>> dayIndexes){
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
        return res;
    }

    public ArrayList<List<String>> NewTotalCases(ArrayList<List<Integer>> dayIndexes){
        return UpToCases(dayIndexes);
    }

    public ArrayList<List<String>> NewTotalDeath(ArrayList<List<Integer>> dayIndexes){
        return UpToDeaths(dayIndexes);
    }

    public ArrayList<List<String>> NewTotalVaccinated(ArrayList<List<Integer>> dayIndexes){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        ArrayList<List<String>> res = new ArrayList<>();
        List<String> groupName = new ArrayList<>();
        List<String> value = new ArrayList<>();
        for(List<Integer> group: dayIndexes){
            long total=0;
            if(group.size()>1) total=data.getPeople_vaccinated().get(group.get(group.size()-1))-data.getPeople_vaccinated().get(group.get(0));
            else total=data.getPeople_vaccinated().get(group.get(0));
            groupName.add(df.format(data.getTime_range().get(group.get(0)))+"-"+df.format(data.getTime_range().get(group.get(group.size()-1))));
            value.add(Long.toString(total));
        }
        res.add(groupName);
        res.add(value);
        return res;
    }
}
