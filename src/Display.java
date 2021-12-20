import java.util.ArrayList;
import java.util.List;

public class Display {
    Summary s;

    public Display(Summary s) {
        this.s = s;
    }

    //Method to display data by table
    public void tabularDisplay(ArrayList<List<String>> displayData) {

        System.out.println(" ________________________________________________");
        System.out.println("| GROUP                         | STATISTICS     |");
        System.out.println("|_______________________________|________________|");

        // Loop through each group
        for (int i=0; i<displayData.get(0).size(); i++) {
            System.out.printf("| %-30s| %-15s|\n", displayData.get(0).get(i),displayData.get(1).get(i));
        }
        System.out.println("|_______________________________|________________|");
    }

    public void chartDisplay(ArrayList<List<String>> displayData) {
        float maxValue = MaximumValue(displayData); // Get largest value from data
        for (int y = 0; y < 24; y++) { // Iterate the program through y=24 times to get y-axis
            System.out.print("\n|");
            String blank = " ";
            for (String value : displayData.get(1)) { // Iterate through data
                int a = 78 / displayData.get(1).size();
                int space = Math.round(a) - 1; // Amount of spaces between two data points
                if (y == (23 - Math.round(23 / maxValue * Integer.parseInt(value)))) { // prints '*' if line is equals to formula
                    System.out.print("*");
                }
                if (y == 23) { // change blank to '_' to get the x-axis
                    blank = "_";
                }
                if (y != (23 - Math.round(23 / maxValue * Integer.parseInt(value)))) { // prints blank if line is not equals to formula
                    System.out.print(blank);
                }
                while (space > 0) { // create spaces between two points of each line
                    System.out.print(blank);
                    space--;
                }
            }
        }
    }


    public int MaximumValue (ArrayList<List<String>> displayData) {
        int max = 0;
        List<String>values = displayData.get(1);
        for (int i = 0; i < values.size(); i++){
            if (max < Integer.parseInt(values.get(i))) {
                max = Integer.parseInt(values.get(i));
            }
        }
        return max;
    }
}