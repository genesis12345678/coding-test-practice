import java.util.Stack;

class Solution{
    public int solution(String s){
        
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < s.length(); i++){
            if(stack.isEmpty()){
                stack.push(s.charAt(i));
            } else {
                char now = s.charAt(i);
                
                if(now == stack.peek()){
                    stack.pop();
                } else {
                    stack.push(now);
                }
            }
        }
        
        return stack.isEmpty() ? 1 : 0;
    }
}