import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileRead {

    public CountryMap readCountryMap(String fileName) {
        File file = new File(fileName);
        Scanner sc = null;
        try {
            sc = new Scanner(file);

            //Error control for city size
            if (!sc.hasNextInt()) {
                System.out.println("Error: First line does not contain number of cities.");
                return null;
            }
            int citySize = sc.nextInt();

            // Control for city names
            String[] cityNames = new String[citySize];
            for (int i = 0; i < citySize; i++) {
                if (!sc.hasNext()) {
                    System.out.println("Error: Not enough city labels provided.");
                    return null;
                }
                cityNames[i] = sc.next();
            }

            // Control for route size
            if (!sc.hasNextInt()) {
                System.out.println("Error: No route count provided.");
                return null;
            }
            int routeSize = sc.nextInt();

            // Create a new CountryMap to store the cities and routes in the file
            CountryMap countryMap = new CountryMap(citySize, routeSize);

            // Adding new cities to the countryMap
            for (int i = 0; i < citySize; i++) {
                countryMap.addCity(cityNames[i]);
            }

            // File route control
            for (int i = 0; i < routeSize; i++) {
                int line = 4 + i;
                if (!sc.hasNext()) {
                    System.out.println("Error: In line " + line + " Not enough route definitions.");
                    return null;
                }
                String from = sc.next();
                if (!sc.hasNext()) {
                    System.out.println("Error: In line " + line + " Missing 'to' city in route definition.");
                    return null;
                }
                String to = sc.next();
                if (!sc.hasNextInt()) {
                    System.out.println("Error: In line " + line + " Missing or invalid distance in route definition.");
                    return null;
                }
                int dist = sc.nextInt();

                // if everything is correct, add the route to the countryMap
                countryMap.addRoute(from, to, dist);
            }

            // for path Start and end cities error control
            if (!sc.hasNext()) {
                int line = 4 + routeSize;
                System.out.println("Error: In line" + line + " Missing start city.");
                return null;
            }
            countryMap.setStartCity(sc.next());

            if (!sc.hasNext()) {
                int line = 4 + routeSize;
                System.out.println("Error: In line"+ line  +" Missing end city.");
                return null;
            }
            countryMap.setEndCity(sc.next());

            System.out.println("File read is successful!");

            return countryMap;

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + fileName);
            return null;
        } finally {
            if (sc != null) {
                if(sc.hasNext())
                    System.out.println("Error: File is not empty there are more lines that is not written.");
                sc.close();
            }
        }
    }
}
