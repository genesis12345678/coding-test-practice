package programmers.practice;

public class Fatigue {

    static boolean[] visit;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {
                {80, 20},
                {50, 40},
                {30, 10}
        };

        solution(k, dungeons);
    }

    private static void solution(int k, int[][] dungeons) {
        visit = new boolean[dungeons.length];
        dfs(0, k, dungeons);

        System.out.println(max);
    }

    static void dfs(int depth, int fatigue, int[][] dungeons) {

        for (int i = 0; i < dungeons.length; i++) {
            if (!visit[i] && fatigue >= dungeons[i][0]) {
                visit[i] = false;
                dfs(depth + 1, fatigue - dungeons[i][1], dungeons);
                visit[i] = true;
            }
        }

        max = Math.max(max, depth);
    }
}
