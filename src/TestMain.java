import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {

        String fileName = "map1.txt";
        FileRead fileRead = new FileRead();
        CountryMap countryMap = fileRead.readCountryMap(fileName);

        if (countryMap != null) {
            WayFinder wf = new WayFinder(countryMap);
            wf.findFastestRoute("A", "E");

            FileWrite fileWrite = new FileWrite();
            //String output = wf.findFastestRoute("A", "E").printRoute();
            fileWrite.writeToFile("output.txt", wf.writePath("A", "E"));
        }
    }


}
