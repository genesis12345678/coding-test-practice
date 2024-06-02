import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] player;
    static int[] order = new int[10];
    static boolean[] visit = new boolean[10];
    static int max = 0;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        player = new int[n][10];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                player[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order[4] = 1;
        visit[4] = true;

        dfs(2);
        System.out.print(max);
    }

    private static void dfs(int depth) {
        if (depth >= 10) {
            max = Math.max(max, play());
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (!visit[i]) {
                visit[i] = true;
                order[i] = depth;
                dfs(depth + 1);
                visit[i] = false;
            }
        }
    }

    private static int play() {

        int idx = 1;
        int sum = 0;

        for (int i = 0; i < n; i++) { //이닝 수
            int outs = 0;
            int score = 0;
            boolean[] base = new boolean[4];

            while (outs < 3) {
                switch (player[i][order[idx]]) {
                    case 0:
                        outs++;
                        break;
                    case 1:
                        if (base[3]) {
                            score++;
                            base[3] = false;
                        }
                        if (base[2]) {
                            base[3] = true;
                            base[2] = false;
                        }
                        if (base[1]) {
                            base[2] = true;
                            base[1] = false;
                        }
                        base[1] = true;
                        break;
                    case 2:
                        if (base[3]) {
                            score++;
                            base[3] = false;
                        }
                        if (base[2]) {
                            score++;
                            base[2] = false;
                        }
                        if (base[1]) {
                            base[3] = true;
                            base[1] = false;
                        }
                        base[2] = true;
                        break;
                    case 3:
                        if (base[3]) {
                            score++;
                            base[3] = false;
                        }
                        if (base[2]) {
                            score++;
                            base[2] = false;
                        }
                        if (base[1]) {
                            score++;
                            base[1] = false;
                        }
                        base[3] = true;
                        break;
                    case 4:
                        if (base[3]) {
                            score++;
                            base[3] = false;
                        }
                        if (base[2]) {
                            score++;
                            base[2] = false;
                        }
                        if (base[1]) {
                            score++;
                            base[1] = false;
                        }
                        score++;
                        break;
                }
                idx++;
                if (idx >= 10) {
                    idx = 1;
                }
            }
            sum += score;
        }

        return sum;
    }
}
