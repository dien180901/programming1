import java.util.ArrayList;
import java.util.List;

public class Display {
    Summary s;

    public Display(Summary s) {
        this.s = s;
    }

    //Method to display data by table
    public void tabularDisplay(ArrayList<List<String>> displayData) {

        System.out.println("+-----------------------------+----------------+");
        System.out.println("| GROUP                       | VALUE          |");
        System.out.println("+-----------------------------+----------------+");

        // Loop through each group
        for (int i=0; i<displayData.get(0).size(); i++) {
            System.out.printf("| %-28.21s| %-15.10s|\n", displayData.get(0).get(i),displayData.get(1).get(i));
        }
        System.out.println("+-----------------------------+----------------+");
    }
}
