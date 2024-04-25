package programmers.practice;

import java.util.Arrays;

public class Nlca {
    public static void main(String[] args) {
        int[] arr = {2, 6, 8, 14};

        int result = solution(arr);

        System.out.println("result = " + result);
        System.out.println("arr = " + Arrays.toString(arr));
    }

    private static int solution(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int a = arr[i];
            int b = arr[i - 1];

            arr[i] = a * b / gcd(a, b);
        }

        return arr[arr.length - 1];
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}
