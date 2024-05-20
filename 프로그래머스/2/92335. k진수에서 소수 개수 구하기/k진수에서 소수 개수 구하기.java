import java.util.Arrays;

class Solution {
    public int solution(int n, int k) {
        
        String num = convert(n, k);
        
        String[] split = num.split("0");
        
        int count = 0;
        
        for(String s : split){
            if(s.isEmpty()){
                continue;
            }
            
            long temp = Long.parseLong(s);
            if (isPrime(temp)) {
                count++;
            }
        }
        
        return count;
    }
    
    public String convert(int n, int k){
        StringBuilder sb = new StringBuilder();
        
        while(n > 0){
            sb.append(n % k);
            n /= k;
        }
        
        return sb.reverse().toString();
    }
    
    public boolean isPrime(long n){
        if (n == 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
        
    }
}