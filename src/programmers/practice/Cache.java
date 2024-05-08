package programmers.practice;

import java.util.LinkedList;

public class Cache {
    public static void main(String[] args) {

        int cacheSize1 = 3;
        int cacheSize2 = 3;
        int cacheSize3 = 2;
        int cacheSize4 = 5;
        int cacheSize5 = 2;
        int cacheSize6 = 0;

        String[] cities1 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        String[] cities2 = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
        String[] cities3 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        String[] cities4 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        String[] cities5 = {"Jeju", "Pangyo", "NewYork", "newyork"};
        String[] cities6 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};

        System.out.println(solution(cacheSize1, cities1));
        System.out.println(solution(cacheSize2, cities2));
        System.out.println(solution(cacheSize3, cities3));
        System.out.println(solution(cacheSize4, cities4));
        System.out.println(solution(cacheSize5, cities5));
        System.out.println(solution(cacheSize6, cities6));
    }

    private static int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5;
        }

        int time = 0;
        LinkedList<String> cache = new LinkedList<>();

        for (String city : cities) {
            String cityName = city.toLowerCase();

            if (cache.contains(cityName)) {
                time++;

                cache.remove(cityName);
                cache.addFirst(cityName);
            } else {
                time += 5;

                if (cache.size() >= cacheSize) {
                    cache.removeLast();
                }

                cache.addFirst(cityName);
            }
        }

        return time;
    }
}
