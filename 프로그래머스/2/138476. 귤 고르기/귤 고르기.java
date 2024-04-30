import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : tangerine){
            map.put(n, map.getOrDefault(n ,0) + 1);
        }
        
        List<Integer> numbers = new ArrayList<>(map.values());
        numbers.sort(Collections.reverseOrder());
        
        int sum = 0;
        int count = 0;
        
        for (int num : numbers) {
            if (sum + num >= k) {
                count++;
                break;
            } else {
                sum += num;
                count++;
            }
        }
        
        return count;
    }
}