import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class Solution {
    
    String[][] tickets;
    List<String> result = new ArrayList<>();
    boolean[] visit;
    
    public String[] solution(String[][] input) {
        tickets = input;
        visit = new boolean[tickets.length];
        
        dfs("ICN", "ICN", 0);
        
        Collections.sort(result);
        
        return result.get(0).split(", ");
    }
    
    public void dfs(String start, String path, int depth){
        if(depth == tickets.length){
            result.add(path);
            return;
        }
        
        for(int i = 0; i < tickets.length; i++){
            if(!visit[i] && start.equals(tickets[i][0])){
                visit[i] = true;
                dfs(tickets[i][1], path + ", " + tickets[i][1] , depth + 1);
                visit[i] = false;
            }
        }
    }
}