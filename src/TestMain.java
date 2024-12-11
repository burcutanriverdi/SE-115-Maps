import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {

        String fileName = "map2.txt";
        FileRead fileRead = new FileRead();
        CountryMap countryMap = fileRead.readCountryMap(fileName);

        if (countryMap != null) {
            WayFinder wf = new WayFinder(countryMap);
            wf.findFastestRoute(countryMap.getStartCity(), countryMap.getEndCity());

            FileWrite fileWrite = new FileWrite();
            //String output = wf.findFastestRoute("A", "E").printRoute();
            fileWrite.writeToFile("output.txt", wf.writePath(countryMap.getStartCity(), countryMap.getEndCity()));
        }
    }


}
