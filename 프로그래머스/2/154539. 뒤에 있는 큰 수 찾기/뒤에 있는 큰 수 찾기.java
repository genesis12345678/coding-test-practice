import java.util.Arrays;
import java.util.Stack;

class Solution {
    public int[] solution(int[] numbers) {
        int[] result = new int[numbers.length];
        Arrays.fill(result, -1);

         Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < result.length; i++) {
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                result[stack.pop()] = numbers[i];
            }
           stack.push(i);
        }

        return result;
    }
}