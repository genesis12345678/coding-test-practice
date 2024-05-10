import java.util.ArrayList;

class Solution {
    
    String[] str = {"A", "E", "I", "O", "U"};
    ArrayList<String> list = new ArrayList<>();
    
    public int solution(String word) {
        dfs(0, "");
        
        return list.indexOf(word);
    }
    
    public void dfs(int depth, String now){
        list.add(now);
        
        if(depth == 5){
            return;
        }
        
        for(String s : str){
            dfs(depth + 1, now + s);
        }
    }
}