package programmers.practice;

import java.util.Arrays;

public class MaxMin {
    public static void main(String[] args) {
        String s = "1 2 3 4";

        System.out.println(solution(s));
    }

    private static String solution(String s) {

        int[] arr = Arrays.stream(s.split(" "))
                          .mapToInt(Integer::parseInt)
                          .toArray();

        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();

        return min + " " + max;
    }
}
