class Solution {
    public int solution(int[] arr) {
        
        for(int i = 1; i < arr.length; i++){
            int a = arr[i];
            int b = arr[i - 1];
            
            arr[i] = a * b / gcd(a, b);
        }
        
        return arr[arr.length - 1];
    }
    
    public int gcd(int a, int b){
        if(b == 0){
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}