package programmers.practice;

import java.util.Arrays;

public class N_Array {
    public static void main(String[] args) {
        int n1 = 3;
        int n2 = 4;

        int left1 = 2;
        int left2 = 7;

        int right1 = 5;
        int right2 = 14;

        System.out.println(Arrays.toString(solution(n1, left1, right1)));
        System.out.println(Arrays.toString(solution(n2, left2, right2)));
    }

    private static int[] solution(int n, long left, long right) {

        int[] result = new int[(int) (right - left + 1)];
        int idx = 0;
        for (long i = left; i <= right; i++) {
            result[idx++] = (int) (Math.max(i / n, i % n) + 1);
        }

        return result;
    }
}
