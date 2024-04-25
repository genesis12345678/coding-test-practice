package programmers.practice;

import java.util.Stack;

public class PairRemove {
    public static void main(String[] args) {
        String s = "baabaa";

        System.out.println(solution(s));
    }

    static int solution(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else {
                char now = s.charAt(i);

                if (stack.peek() == now) {
                    stack.pop();
                } else {
                    stack.push(now);
                }
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }
}
