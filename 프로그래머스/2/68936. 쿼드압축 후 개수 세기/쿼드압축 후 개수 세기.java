class Solution {
    
    int[] result = new int[2];
    
    public int[] solution(int[][] arr) {
       compact(0, 0, arr.length, arr);
        
        return result;
    }
    
    public void compact(int x, int y, int length, int[][] arr){
        if(check(x, y, length, arr)){
            result[arr[x][y]]++;
            return;
        }
        
        compact(x, y, length / 2, arr);
        compact(x + length / 2, y, length / 2, arr);
        compact(x, y + length / 2, length / 2, arr);
        compact(x + length / 2, y + length / 2, length / 2, arr);
    }
    
    public boolean check(int x, int y, int length, int[][] arr){
        for(int i = x; i < x + length; i++){
            for(int j = y; j < y + length; j++){
                if(arr[x][y] != arr[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}