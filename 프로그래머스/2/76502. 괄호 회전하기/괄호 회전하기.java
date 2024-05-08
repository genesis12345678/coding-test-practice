import java.util.Stack;

class Solution {
    public int solution(String s) {
        
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if(check(s)){
                count++;
            }
            
            s = s.substring(1) + s.charAt(0);
        }
        
        return count;
    }
    
    public boolean check(String s){
        
        Stack<Character> stack = new Stack<>();
        
        for(char ch : s.toCharArray()){
            if(ch == '(' || ch == '[' || ch == '{'){
                stack.push(ch);
            } else {
                if(stack.isEmpty()){
                    return false;
                }
                
                char top  = stack.pop();
                
                if((ch == ')' && top != '(')||
                   (ch == ']' && top != '[')||
                   (ch == '}' && top != '{')){
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
}