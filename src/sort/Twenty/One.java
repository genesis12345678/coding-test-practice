package sort.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href = "https://www.acmicpc.net/problem/2822">백준 2822번 - 정렬 : 점수 계산</a>
 * @since 2024-05-14
 */
public class One {
    public static void main(String[] args) throws IOException {
        int[] arr = new int[9];
        int[] arr2 = new int[9];


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= 8; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            arr2[i] = arr[i];
        }

        Arrays.sort(arr);

        List<Integer> list = new ArrayList<>();
        int sum = 0;
        for (int i = 4; i <= 8; i++) {
            list.add(arr[i]);
            sum += arr[i];
        }
        System.out.println(sum);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 8; i++) {
            if (list.contains(arr2[i])) {
                System.out.print(i + " ");
            }
        }
    }
}
