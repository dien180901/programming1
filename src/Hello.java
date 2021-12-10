import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
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
    public static void readFile() throws IOException {
//        input file
        File file = new File(
                "/Users/s3922087/IdeaProjects/programming13/src/covid.txt");
        BufferedReader br
                = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st;
        // Consition holds true till
        // there is character in a string
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
                timeRange temp_time_range=temp_data.getTime_range();
                temp_time_range.setEndDate(parameters[3]);
                temp_data.setTime_range(temp_time_range);
                ArrayList<Long> new_case=temp_data.getNew_cases();
                new_case.add(ChangeLong(parameters[4]));
                ArrayList<Long> new_death=temp_data.getNew_deaths();
                new_death.add(ChangeLong(parameters[5]));
                ArrayList<Long> people_vaccinated=temp_data.getPeople_vaccinated();
                people_vaccinated.add(ChangeLong(parameters[6]));
                ArrayList<Long> population=temp_data.getPopulation();
                population.add(ChangeLong(parameters[7]));
                Datas.set(len-1,temp_data);
            }else{
                hash_Set.add(parameters[0]);

                len+=1;
                ArrayList<Long> new_cases=new ArrayList<Long>();
                new_cases.add(ChangeLong(parameters[4]));
                ArrayList<Long> new_deaths=new ArrayList<Long>();
                new_deaths.add(ChangeLong(parameters[5]));
                ArrayList<Long> people_vaccinated=new ArrayList<Long>();
                people_vaccinated.add(ChangeLong(parameters[6]));
                ArrayList<Long> population=new ArrayList<Long>();
                population.add(ChangeLong(parameters[7]));

                timeRange time_range=new timeRange(parameters[3],parameters[3]);
                Temp=new Data(parameters[0],parameters[2],time_range,new_cases,new_deaths,people_vaccinated,population);
                Datas.add(Temp);

            }
        }
        Summary f1 = new Summary(Datas.get(0));
        long uptocases = f1.UpToCases();
        long uptodeaths = f1.UpToDeaths();
        long uptovaccinateds = f1.UpToVaccinateds();
        long newcases = f1.NewTotalCases();
        long newdeaths = f1.NewTotalDeath();
        long newvaccinateds = f1.NewTotalVaccinated();
        System.out.println(uptocases);
        System.out.println(uptodeaths);
        System.out.println(uptovaccinateds);
        System.out.println(newcases);
        System.out.println(newdeaths);
        System.out.println(newvaccinateds);
    }
    public static void main(String[] args) throws Exception
    {
        readFile();

    }
}



