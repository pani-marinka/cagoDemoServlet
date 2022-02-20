package enums;

public enum TonnageDelivery {
    tonn5(1, String.valueOf(5)),
    tonn10(2, String.valueOf(10)),
    tonn15(3, String.valueOf(15));

    private TonnageDelivery(int value, String tonnage) {
        this.id = id;
        this.tonnage = tonnage;
    }

    int id;
    String tonnage;

    public static String getTonnageById(int tonnageId) {
        switch (tonnageId) {
            case 1:
                return "5";
            case 2:
                return "10";
            case 3:
                return "15";
        }
        return "";
    }
}
