import java.io.*;
import java.text.DateFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

// Main class
public class Hello {
    // main driver method
    public boolean is_empty(String s){
        return s.length()==0;
    }
    public static Long ChangeLong(String s){
        if (s.isEmpty()){
            return 0L;
        }
        return Long.parseLong(s);
    }

    public static ArrayList<Data>  readFile() throws Exception {
    // Input file
        File file = new File(
                "/Users/baohang/Desktop/RMIT/Programming 1/Asm2/programming1/programming1/src/covid-data.txt");
        BufferedReader br
                = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st;
        // Consition holds true till there is character in a string
        Set<String> hash_Set = new HashSet<String>();
        Data Temp=new Data();
        ArrayList<Data> Datas=new ArrayList<Data>();
        int len=0;

        while ((st = br.readLine()) != null)
        {
            st+=",0";
            String[] parameters = st.split(",");

            if (hash_Set.contains(parameters[0])){
                Data temp_data=Datas.get(len-1);

                // Time_range ArrayList
                ArrayList<Date> time_range=temp_data.getTime_range();
                time_range.add(new SimpleDateFormat("MM/dd/yyyy").parse(parameters[3]));

                ArrayList<Long> new_case=temp_data.getNew_cases();
                new_case.add(ChangeLong(parameters[4]));

                ArrayList<Long> new_death=temp_data.getNew_deaths();
                new_death.add(ChangeLong(parameters[5]));

                ArrayList<Long> people_vaccinated=temp_data.getPeople_vaccinated();
                if(!Objects.equals(parameters[6], ""))
                    people_vaccinated.add(Long.parseLong(parameters[6]));
                else {
                    people_vaccinated.add(people_vaccinated.get(people_vaccinated.size()-1));
                }

                ArrayList<Long> population=temp_data.getPopulation();
                population.add(ChangeLong(parameters[7]));

                Datas.set(len-1,temp_data);
            }
            else{
                hash_Set.add(parameters[0]);

                len+=1;

                ArrayList<Date> time_range=new ArrayList<Date>();
                time_range.add(new SimpleDateFormat("MM/dd/yyyy").parse(parameters[3]));

                ArrayList<Long> new_cases=new ArrayList<Long>();
                new_cases.add(ChangeLong(parameters[4]));

                ArrayList<Long> new_deaths=new ArrayList<Long>();
                new_deaths.add(ChangeLong(parameters[5]));

                ArrayList<Long> people_vaccinated=new ArrayList<Long>();
                people_vaccinated.add(ChangeLong(parameters[6]));

                ArrayList<Long> population=new ArrayList<Long>();
                population.add(ChangeLong(parameters[7]));

                Temp=new Data(parameters[0],parameters[2],time_range,new_cases,new_deaths,people_vaccinated,population);
                Datas.add(Temp);
            }
        }
        return Datas;
    }

    public static void main(String[] args) throws Exception {
        /*-- DATA FORMATTING --*/
        ArrayList<Data> Datas = readFile();
        Scanner in=new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        /*-- ASK USER TO INPUT THE COUNTRY NAME --*/
        System.out.println("Enter a country: ");
        String countryName = in.nextLine();
        // Get the index of the country object
        int indexInDatas=0;
        for(int i=0; i<Datas.size(); i++){
            if(countryName.equals(Datas.get(i).getGeographic_area())) indexInDatas=i;
        }

        /*-- ASK USER TO INPUT THE BEGIN AND END DATE --*/
        System.out.println("Enter start day (MM/dd/yyyy)");
        Date beginDay = df.parse(in.nextLine());
        System.out.println("Enter end day (MM/dd/yyyy)");
        Date endDay = df.parse(in.nextLine());
        // Create Summary variable
        Summary summaryData = new Summary(Datas.get(indexInDatas));
        // Create a time range within user date input
        ArrayList<Integer> dayIndexes = summaryData.userTimeRange(beginDay, endDay);

        /*-- ASK USER TO CHOOSE THE GROUPING STYLE --*/
        ArrayList<List<Integer>> groupingAL = new ArrayList<>();
        System.out.println("Enter the GROUPING STYLE:   1->NO GROUPING    2->NUMBER OF GROUPS   3->NUMBER OF DAYS ");
        int groupingStyle = in.nextInt();
        if(groupingStyle==1) groupingAL=summaryData.NoGroup(dayIndexes);
        else if(groupingStyle==2) {
            System.out.println("How many groups you want?: ");
            int groups = in.nextInt();
            groupingAL=summaryData.NumGroup(dayIndexes, groups);
        }
        else if(groupingStyle==3) {
            System.out.println("How many days you want in a group?: ");
            int days = in.nextInt();
            groupingAL=summaryData.NumDay(dayIndexes, days);
        }

        /*-- ASK USER TO CHOOSE THE METRICS --*/
        System.out.println("Enter the METRICS:   1->POSITIVE CASES    2->DEATHS   3->VACCINATED PEOPLE ");
        int metric = in.nextInt();

        /*-- ASK USER TO CHOOSE THE RESULT TYPE --*/
        System.out.println("Enter the METRICS:   1->NEW TOTAL    2->UP TO ");
        int resType = in.nextInt();

        /*-- ASK USER TO CHOOSE THE METRIC AND RESULT TYPE --*/
        ArrayList<List<String>> displayData;
        if(metric==1) summaryData.UpToCases(groupingAL);
        else if(metric==2) summaryData.UpToDeaths(groupingAL);
        else if(metric==3){
            if(resType==1) summaryData.NewTotalVaccinated(groupingAL);
            else if(resType==2) summaryData.UpToVaccinateds(groupingAL);
        }

        /*-- ASK USER TO CHOOSE DISPLAYING TYPE --*/
        System.out.println("Enter the METRICS:   1->TABLE    2->CHART ");
        int displayType = in.nextInt();
        //System.out.println(displayData);
        Display display = new Display(summaryData);
        if(displayType==1) display.tabularDisplay();
        if(displayType==2) display.chartDisplay();

    }
}



