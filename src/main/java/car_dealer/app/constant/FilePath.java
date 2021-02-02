package car_dealer.app.constant;

public class FilePath {

    private FilePath() {}

    private static final String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\EX2\\";

    public static final String CARS_PATH = FILE_PATH + "cars.json";
    public static final String CUSTOMERS_PATH = FILE_PATH + "customers.json";
    public static final String PARTS_PATH = FILE_PATH + "parts.json";
    public static final String SUPPLIERS_PATH = FILE_PATH + "suppliers.json";
}