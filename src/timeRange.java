public class timeRange {
    private String startDate;
    private String endDate;

    public timeRange() {
        this.startDate = "";
        this.endDate = "";
    }

    public timeRange(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

//    String[] presentDate(String[], String start, String end){
//
//    }
}
