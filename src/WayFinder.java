public class WayFinder {

    private CountryMap countryMap;
    private int[] distances;
    private boolean[] visited;
    private int[] previous;

    public WayFinder(CountryMap countryMap) {
        this.countryMap = countryMap;
    }

    // Method to find the fastest route implementing Dijkstra algorithm
    public void findFastestRoute(String startName, String endName) {

        int startCity = countryMap.cityIndexFind(startName);
        int endCity = countryMap.cityIndexFind(endName);
        if (startCity == -1 || endCity == -1) {
            System.out.println("City that you want to go is not in the map");
            return;
        }

        int size = countryMap.cities.length;

        distances = new int[size];
        visited = new boolean[size];
        previous = new int[size];

        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < size; i++) {
            distances[i] = Integer.MAX_VALUE;
            visited[i] = false;
            previous[i] = -1;
        }
        distances[startCity] = 0;
        for (int i = 0; i < size; i++) {
            int current = getMinDistanceUnvisitedIndex();

            if (current == -1) {
                break;
            }

            visited[current] = true;

            for (Route route : countryMap.routes) {
                if (route == null) continue;

                int fromIndex = countryMap.cityIndexFind(route.getCity1().getName());
                int toIndex = countryMap.cityIndexFind(route.getCity2().getName());


                if (fromIndex == current) {
                    int newDist = distances[current] + route.getDistance();
                    if (newDist < distances[toIndex]) {
                        distances[toIndex] = newDist;
                        previous[toIndex] = current;
                    }
                }

                if (toIndex == current) {
                    int newDist = distances[current] + route.getDistance();
                    if (newDist < distances[fromIndex]) {
                        distances[fromIndex] = newDist;
                        previous[fromIndex] = current;
                    }
                }
            }
        }

        if (distances[endCity] == Integer.MAX_VALUE) {
            System.out.println("No path found from " + startName + " to " + endName);
        } else {
            //printPath(startCity, endCity);
            return;
        }
    }


    private int getMinDistanceUnvisitedIndex() {
        int minDist = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] < minDist) {
                minDist = distances[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void printPath(int start, int end) {
        if (start == end) {
            System.out.print(countryMap.cities[start].getName());

        } else if (previous[end] == -1) {
            System.out.println("No path found");
        } else {
            printPath(start, previous[end]);
            System.out.print( " -> " + countryMap.cities[end].getName() );

            // to print the last line as total time
            int disLength = distances.length;
            if(end == disLength-1){
                System.out.println("\nTotal Time: " + distances[end] + " min");
            }
        }
    }

    private String path = "";

    public String writePath(String startName, String endName) {

        int start = countryMap.cityIndexFind(startName);
        int end = countryMap.cityIndexFind(endName);

        if (start == end) {
            path += "Fastest Way: " + countryMap.cities[start].getName();

        } else if (previous[end] == -1) {
            path = "No path found";
        }

        else {
            writePath(countryMap.cityFind(start), countryMap.cityFind(previous[end]));
            path += " -> " + countryMap.cities[end].getName();

            int disLength = distances.length;
            if(end == disLength-1){
                path += "\nTotal Time: " + distances[end] + " min";
            }
        }
        return path;
    }
}


