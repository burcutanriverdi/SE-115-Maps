public class CountryMap {

    City[] cities;
    Route[] routes;
    private int citySize;
    private int routeSize;
    public CountryMap(int citySize, int routeSize) {
        this.cities = new City[citySize];
        this.routes = new Route[routeSize];
        this.citySize = citySize;
        this.routeSize = routeSize;
    }

    public void readFile(String fileName) {
        Scanner sc = null;
        try {
            sc = new Scanner(Paths.get(fileName));
            int lineNumber = 0;
            while (sc.hasNextLine()) {
                lineNumber++;
                String line = sc.nextLine().trim();

                if (lineNumber == 1) {
                    if (line.isEmpty() || !line.matches("\\d+")) {
                        throw new IllegalArgumentException("Invalid city count format on line 1");
                    }
                    citySize = Integer.parseInt(line);
                    if (citySize <= 0) {
                        throw new IllegalArgumentException("City count must be a positive integer on line 1");
                    }
                    cities = new City[citySize];
                } else if (lineNumber == 2) {
                    String[] cityNames = line.split(" ", citySize);
                    if (cityNames.length != citySize) {
                        throw new IllegalArgumentException("Number of cities does not match city size on line 2");
                    }
                    for (String cityName : cityNames) {
                        if (cityName.isEmpty()) {
                            throw new IllegalArgumentException("Invalid city name in line 2");
                        }
                        addCity(cityName);
                    }
                } else if (lineNumber == 3) {
                    if (line.isEmpty() || !line.matches("\\d+")) {
                        throw new IllegalArgumentException("Invalid route count format on line 3");
                    }
                    routeSize = Integer.parseInt(line);
                    if (routeSize < 0) {
                        throw new IllegalArgumentException("Route count must be a non-negative integer on line 3");
                    }
                    routes = new Route[routeSize];
                } else {
                    String[] parts = line.split(" ", 3);
                    if (parts.length != 3) {
                        throw new IllegalArgumentException("Invalid route format on line " + lineNumber);
                    }
                    try {
                        String fromCity = parts[0];
                        String toCity = parts[1];
                        int distance = Integer.parseInt(parts[2]);
                        if (distance < 0) {
                            throw new IllegalArgumentException("Distance must be a non-negative integer on line " + lineNumber);
                        }
                        addRoute(fromCity, toCity, distance);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid distance format on line " + lineNumber);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    private int cityCount = 0;
    private int routeCount = 0;

    public void addCity(String cityName){
        if(cityCount < citySize){
            cities[cityCount++] = new City(cityName);
        }
    }

    public void addRoute(String from, String to, int dist){
        if(cityFind(from) != null && cityFind(to) != null && routeCount < routeSize){
            City fromCity = cityFind(from);
            City toCity = cityFind(to);
            routes[routeCount++] = new Route(fromCity, toCity, dist);
        }
    }

    private City cityFind(String cityName){
        for(City city : cities ){
            if(city.getName().equals(cityName)){
                return city;
            }
        }
        return null;
    }

}
