package dfs.thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 동서남북 방향으로 같은 지점을 방문하지 않도록 dfs 탐색을 한다.
 * 각 방향으로 갈 수 있는 확률을 곱해간다.
 */

public class Five {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static double[] percent = new double[4];//동서남북 확률
    static double answer = 0;
    static boolean[][] visit = new boolean[30][30];//N이 최대 14이다, 29 또는 30으로 map을 만들어야 한다.
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            percent[i] = Double.parseDouble(st.nextToken()) * 0.01;
        }

        visit[15][15] = true;
        dfs(15, 15, 0, 1);//중앙에서부터 시작

        System.out.println(answer);
    }

    static void dfs(int x, int y, int count, double result) {
        if (count == N) {//최대 이동횟수에 도달했을 때
            answer += result;//지금까지 이동한 방향들의 확률을 더한다.
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < 30 && ny < 30) {
                if (!visit[nx][ny]) {
                    visit[nx][ny] = true;
                    dfs(nx, ny, count + 1, result * percent[i]);
                    visit[nx][ny] = false;
                }
            }
        }
    }
}
