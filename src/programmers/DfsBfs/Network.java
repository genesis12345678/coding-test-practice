package programmers.DfsBfs;

import java.util.ArrayList;

public class Network {

    static ArrayList<Integer>[] A;
    static boolean[] visit;

    public static void main(String[] args) {
        int[][] computers = {
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };

        int n = 3;

        int result = solution(n, computers);

        System.out.println("result = " + result);
    }

    private static int solution(int n, int[][] computers) {
        A = new ArrayList[n + 1];
        visit = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1) {
                    A[i].add(j);
                    A[j].add(i);
                }
            }
        }

        int temp = 0;
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                temp++;
                dfs(i);
            }
        }

        return temp;
    }

    private static void dfs(int start) {
        if (visit[start]) {
            return;
        }

        visit[start] = true;

        for (int next : A[start]) {
            dfs(next);
        }
    }

}
