public class Route {
    private City city1;
    private City city2;
    private int distance;

    public Route(City city1, City city2, int distance) {
        this.city1 = city1;
        this.city2 = city2;
        this.distance = distance;
    }

    public City getCity1() {
        return city1;
    }

    public void setCity1(City city1) {
        this.city1 = city1;
    }

    public City getCity2() {
        return city2;
    }

    public void setCity2(City city2) {
        this.city2 = city2;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
