package programmers.practice;

import java.util.HashSet;

public class 연속_부분_수열_합 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{7, 9, 1, 1, 4}));

    }

    static int solution(int[] elements) {
        int n = elements.length;
        int[] arr = new int[n * 2];

        for (int i = 0; i < n * 2; i++) {
            arr[i] = elements[i % n];
        }

        HashSet<Integer> set = new HashSet<>();
        for (int start = 0; start < n; start++) {
            for (int len = 1; len <= n; len++) {
                int sum = 0;
                for (int i = start; i < start + len; i++) {
                    sum += arr[i];
                }
                set.add(sum);
            }
        }

        return set.size();
    }
}
