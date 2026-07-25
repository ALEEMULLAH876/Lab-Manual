import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    private Map<Integer, CheckIn> checkIns;
    private Map<String, Trip> trips;

    public UndergroundSystem() {
        checkIns = new HashMap<>();
        trips = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkIns.put(id, new CheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckIn checkIn = checkIns.get(id);
        String key = checkIn.station + "->" + stationName;

        Trip trip = trips.getOrDefault(key, new Trip());
        trip.totalTime += (t - checkIn.time);
        trip.count++;

        trips.put(key, trip);
        checkIns.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "->" + endStation;
        Trip trip = trips.get(key);
        return (double) trip.totalTime / trip.count;
    }

    class CheckIn {
        String station;
        int time;

        CheckIn(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    class Trip {
        int totalTime = 0;
        int count = 0;
    }
}
