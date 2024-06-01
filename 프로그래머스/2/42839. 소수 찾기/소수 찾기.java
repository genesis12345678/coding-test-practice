import java.util.HashSet;

class Solution {
    
    HashSet<Integer> set = new HashSet<>();
    boolean[] visit = new boolean[7];
    
    public int solution(String numbers) {
        
        dfs("", numbers, 0);
        
        int count = 0;
        
        for(int n : set){
            if(isPrime(n)){
                count++;
            }
        }
        
        return count;
    }
    
    public void dfs(String now, String numbers, int depth){
        if(depth > numbers.length()){
            return;
        }
        
        for(int i = 0; i < numbers.length(); i++){
            if(!visit[i]){
                visit[i] = true;
                int target = Integer.parseInt(now + numbers.charAt(i));
                set.add(target);
                dfs(now + numbers.charAt(i), numbers, depth + 1);
                visit[i] = false;
            }
        }
    }
    
    public boolean isPrime(int n){
        if(n < 2){
            return false;
        }
        for(int i = 2; i <= Math.sqrt(n); i++){
            if(n % i == 0){
                return false;
            }
        }
        
        return true;
    }
}