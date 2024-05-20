import java.util.HashMap;

class Solution {
    public int solution(int[] topping) {
       
        int count = 0;

        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        
        for(int i : topping){
            map1.put(i, map1.getOrDefault(i, 0) + 1);
        }
        
        for(int i : topping){
            map2.put(i, map2.getOrDefault(i, 0) + 1);
            
            int t = map1.get(i);
            
            if(t - 1 == 0){
                map1.remove(i);
            } else {
                map1.put(i, t - 1);
            }
            
            if(map1.size() == map2.size()){
                count++;
            }
        }
        

        return count;
    }
}