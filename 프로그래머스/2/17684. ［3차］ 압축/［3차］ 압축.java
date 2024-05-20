import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < 26; i++){
            char c = (char)('A' + i);
            map.put(String.valueOf(c), i + 1);
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        
        int next = 27;
        
        for(int i = 0; i < msg.length();){
            String w = String.valueOf(msg.charAt(i));
            int j = i + 1;
            
            for(; j <= msg.length(); j++){
                String wc = msg.substring(i, j);
                if(map.containsKey(wc)){
                    w = wc;
                } else {
                    break;
                }
            }
            
            list.add(map.get(w));
            
            if(j <= msg.length()){
                map.put(msg.substring(i, j), next++);
            }
            
            i += w.length();
        }
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}