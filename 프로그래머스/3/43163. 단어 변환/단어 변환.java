class Solution {
    
    boolean[] visit;
    int min = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        
        visit = new boolean[words.length];
        
        dfs(begin, target, words, 0);
        
        if(min == Integer.MAX_VALUE){
            return 0;
        }else{
            return min;
        }
        
    }
    
    public void dfs(String begin, String target, String[] words, int depth){
        if(begin.equals(target)){
            min = Math.min(min, depth);
            return;
        }
        
        for(int i = 0; i < words.length; i++){
            if(!visit[i]){
                
                int same = 0;
                
                for(int j = 0; j < begin.length(); j++){
                    if(begin.charAt(j) == words[i].charAt(j)){
                        same++;
                    }
                }
                
                if(same == begin.length() - 1){
                    visit[i] = true;
                    
                    dfs(words[i], target, words, depth + 1);
                    
                    visit[i] = false;
                
                }
            }
        }
    }
}