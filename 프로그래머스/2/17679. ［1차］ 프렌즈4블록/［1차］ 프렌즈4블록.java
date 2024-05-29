class Solution {
    
    char[][] map;
    boolean[][] remove;
    
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        map = new char[m][n];
        for(int i = 0; i < m; i++){
            map[i] = board[i].toCharArray();
        }
        
        while(true){
            check(m, n);
            int count = calc(m, n);
            if(count == 0){
                break;
            }
            
            answer += count;
            updateMap(m, n);
        }
        
        return answer;
    }
    
    public void check(int m, int n){
        remove = new boolean[m][n];
        
        for(int i = 0; i < m - 1; i++){
            for(int j = 0; j < n - 1; j++){
                
                char now = map[i][j];
                
                if(now == '-'){
                    continue;
                }
                
                if(map[i + 1][j] == now &&
                   map[i][j + 1] == now &&
                   map[i + 1][j + 1] == now){
                    
                   remove[i][j] = remove[i + 1][j] = remove[i][j + 1] = remove[i + 1][j + 1] = true;
                }
            }
        }
    }
    
    public int calc(int m, int n){
        int count = 0;
        
         for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(remove[i][j]){
                    count++;
                    map[i][j] = '-';
                }
            }
         }
        
        return count;
    }
    
    public void updateMap(int m, int n) {
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '-') {

                    for (int k = i - 1; k >= 0; k--) {
                        if (map[k][j] != '-') {
                            map[i][j] = map[k][j];
                            map[k][j] = '-';
                            break;
                        }
                    }
                }
            }
        }
    }
}