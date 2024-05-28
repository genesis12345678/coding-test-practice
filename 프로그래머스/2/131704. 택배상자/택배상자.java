import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> stack = new Stack<>();
        
        int index = 0;
        
        for(int i = 1; i <= order.length; i++){
            stack.push(i);
            
            while(!stack.isEmpty() && stack.peek() == order[index]){
                stack.pop();
                index++;
            }
        }
        
        return index;
    }
}