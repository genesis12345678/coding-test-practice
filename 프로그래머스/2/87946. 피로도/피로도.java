class Solution {
    
    public boolean[] visit;
    public int max = Integer.MIN_VALUE;
    
    public int solution(int k, int[][] dungeons) {
        visit = new boolean[dungeons.length];
        dfs(0, k, dungeons);
        return max;
    }
    
    public void dfs(int depth, int fatigue, int[][] dungeons) {
      
        for(int i = 0; i < dungeons.length; i++){
            if(!visit[i] && fatigue >= dungeons[i][0]){
                visit[i] = true;
                dfs(depth + 1, fatigue - dungeons[i][1], dungeons);
                visit[i] = false;
            }
        }

        max = Math.max(max, depth);
      
    }
}