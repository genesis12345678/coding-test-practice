import java.util.LinkedList;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0){
            return cities.length * 5;
        }
        
        int time = 0;
        LinkedList<String> cache = new LinkedList<>();
        
        for(String city : cities){
            String cityName = city.toLowerCase();
            
            if(cache.contains(cityName)){
                time++;
                
                cache.remove(cityName);
                cache.addFirst(cityName);
            } else {
                time += 5;
                
                if(cache.size() >= cacheSize){
                    cache.removeLast();
                }
                
                cache.addFirst(cityName);
            }
        }
        
        return time;
    }
}