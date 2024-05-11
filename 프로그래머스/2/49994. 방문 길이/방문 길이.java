class Solution {
    
    boolean[][][] visit = new boolean[11][11][4];
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    
    public int solution(String dirs) {
        int x = 5;
        int y = 5;
        int count = 0;
        
        for(char ch : dirs.toCharArray()){
            int d = getDir(ch);
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            if(nx < 0 || ny < 0 || nx >= 11 || ny >= 11){
                continue;
            }
            
            if(!visit[nx][ny][d]){
                visit[nx][ny][d] = true;
                visit[x][y][(d + 2) % 4] = true;
                count++;
            }
            
            x = nx;
            y = ny;
        }
        
        return count;
    }
    
    public int getDir(char ch){
        switch(ch){
            case 'U':
                return 0;
            case 'L':
                return 1;
            case 'D':
                return 2;
            default:
                return 3;
        }
    }
}