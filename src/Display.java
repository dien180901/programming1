import java.util.ArrayList;
import java.util.List;

public class Display {
    Summary summ;

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
        float maxValue = MaximumValue(summ.getdisplayData());
        for (int y = 0; y < 24; y++) {
            System.out.print("\n|");
            String blank = " ";
            for (String value : summ.getdisplayData().get(1)) {
                int patern = 23 - Math.round(23 / maxValue * Integer.parseInt(value));
                int a = 78 / summ.getdisplayData().get(1).size();
                int space = Math.round(a) - 1;
                if (patern==y) {
                    System.out.print("*");
                }
                if (y == 23) {
                    blank = "_";
                }
                if (patern!=y) {
                    System.out.print(blank);
                }
                while (space > 0) {
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