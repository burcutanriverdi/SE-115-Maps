public class CountryMap {

    public City[] cities;
    public Route[] routes;
    private int citySize;
    private int routeSize;

    public CountryMap(int citySize, int routeSize) {
        this.cities = new City[citySize];
        this.routes = new Route[routeSize];
        this.citySize = citySize;
        this.routeSize = routeSize;
    }

    private int cityCount= 0;
    private int routeCount= 0;

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

    public int cityIndexFind(String cityName){
        int i = 0;
        for(City city : cities ){
            if(city.getName().equals(cityName)){
                return i;
            }
            i++;
        }
        return -1;
    }

    public String cityFind(int index){
        return cities[index].getName();
    }

}
