import java.util.HashSet;

class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        int[] arr = new int[n * 2];
        
        for(int i = 0; i < n * 2; i++){
            arr[i] = elements[i % n];
        }
        
        HashSet<Integer> set = new HashSet<>();
        
        for(int start = 0; start < n; start++){
            for(int len = 1; len <= n; len++){
                int sum = 0;
                
                for(int i = start; i < start + len; i++){
                    sum += arr[i];
                }
                
                set.add(sum);
            }
        }
        
        return set.size();
    }
}