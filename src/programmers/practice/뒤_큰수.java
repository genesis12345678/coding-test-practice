package programmers.practice;

import java.util.Arrays;
import java.util.Stack;

public class 뒤_큰수 {
    public static void main(String[] args) {
        int[] numbers1 = {2, 3, 3, 5};
        int[] numbers2 = {9, 1, 5, 3, 6, 2};

        System.out.println(Arrays.toString(solution(numbers1)));
        System.out.println(Arrays.toString(solution(numbers2)));
    }

    private static int[] solution(int[] numbers) {
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
