package programmers.Graph;

import java.util.Arrays;

public class Rank {
    public static void main(String[] args) {
        int[][] results = {
                {4, 3},
                {4, 2},
                {3, 2},
                {1, 2},
                {2, 5},
        };

        int n = 5;
        int[][] dist = new int[n + 1][n + 1];

        for (int i = 0; i < results.length; i++) {
            int s = results[i][0];
            int e = results[i][1];

            dist[s][e] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    if (dist[s][k] == 1 && dist[k][e] == 1) {
                        dist[s][e] = 1;
                    }
                }
            }
        }

        for (int[] ints : dist) {
            System.out.println("ints = " + Arrays.toString(ints));
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            int sum = 0;

            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == 1 || dist[j][i] == 1) {
                    sum++;
                }
            }

            if (sum == n - 1) {
                result++;
            }
        }

        System.out.println(result);


    }
}
