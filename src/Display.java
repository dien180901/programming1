import java.util.ArrayList;
import java.util.List;

public class Display {
    // Create attributes
    Summary summ;

    // Create constructor for DataDisplay class
    public Display(Summary summ) {
        this.summ = summ;
    }

    // Method to display data by table
    public void tabularDisplay() {

        System.out.println(" ________________________________________________");
        System.out.println("| GROUPS                        | STATISTICS     |");
        System.out.println("|_______________________________|________________|");

        // Loop through each group
        for (int i=0; i<summ.getdisplayData().get(0).size(); i++) {
            System.out.printf("| %-30s| %-15s|\n", summ.getdisplayData().get(0).get(i),summ.getdisplayData().get(1).get(i));
        }
        System.out.println("|_______________________________|________________|");
    }

    // Method to display data by chart
    public void chartDisplay() {
        // Get largest value from data
        float maxValue = MaximumValue(summ.getdisplayData());
        // Iterate the program through y = 24 times to get y-axis
        for (int y = 0; y < 24; y++) {
            System.out.print("\n|");
            String blank = " ";
            // Iterate through data
            for (String value : summ.getdisplayData().get(1)) {
                // formula to calculated the possition of data
                int patern = 23 - Math.round(23 / maxValue * Integer.parseInt(value));
                int a = 78 / summ.getdisplayData().get(1).size();
                // Amount of spaces between two data points
                int space = Math.round(a) - 1;
                // prints '*' if line is equals to formula
                if (patern==y) {
                    System.out.print("*");
                }
                // change blank to '_' to get the x-axis
                if (y == 23) {
                    blank = "_";
                }
                // prints blank if line is not equals to formula
                if (patern!=y) {
                    System.out.print(blank);
                }
                // create distance between two points of each line
                while (space > 0) {
                    System.out.print(blank);
                    space--;
                }
            }
        }
    }

    //Method to get largest value from data
    public int MaximumValue (ArrayList<List<String>> displayData) {
        int max = 0;
        List<String>values = displayData.get(1);
        for (String value : values) {
            if (max < Integer.parseInt(value)) {
                max = Integer.parseInt(value);
            }
        }
        return max;
    }
}