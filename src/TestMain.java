import java.io.File;

public class TestMain {
    public static void main(String[] args) {
        // Check if a file name is provided as a command-line argument
        if (args.length < 1) {
            System.out.println("Usage: java TestMain <file_name>");
            return;
        }

        String fileName = args[0]; // Get the file name from the command line
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Error: File not found - " + fileName);
            return;
        }

        FileRead fileRead = new FileRead();
        CountryMap countryMap = fileRead.readCountryMap(fileName);

        if (countryMap != null) {
            WayFinder wf = new WayFinder(countryMap);
            wf.findFastestRoute(countryMap.getStartCity(), countryMap.getEndCity());

            FileWrite fileWrite = new FileWrite();
            fileWrite.writeToFile("output.txt", wf.writePath(countryMap.getStartCity(), countryMap.getEndCity()));
        } else {
            System.out.println("Error: Unable to read the country map from the file.");
        }
    }
}
