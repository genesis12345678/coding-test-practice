package bruteForce.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 백트래킹을 통해 가능한 모든 경로를 돌아보면서 최단 경로를 구한다.
 * 어느 번호에서 시작하든 최단 경로는 항상 같다. 따라서 모든 번호를 시작점으로 할 필요 없이 임의의 점 하나에 대해서만 정답을 구할 수 있다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/10971">백준 10971번 - 브루트포스(외판원 순회) : 외판원 순회 2</a>
 * @since 2024-04-29
 */
public class Two {

    static int[][] map;
    static int n;
    static boolean[] visit;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0,1);

        System.out.println(min);
    }

    /**
     *
     * @param depth : 현재 위치
     * @param sum : 현재까지 거리
     * @param count : 현재까지 탐색한 도시 개수
     */
    static void dfs(int depth, int sum, int count) {
        if (count == n) { //모든 도시를 순회한 거라면
            if (map[depth][0] != 0) { //다시 처음으로 돌아갈 수 있다면
                min = Math.min(min, sum + map[depth][0]); //최단 거리 갱신
            }
            return;
        }

        for (int i = 1; i < n; i++) {
            if (!visit[i] && map[depth][i] != 0) {
                visit[i] = true;
                dfs(i, sum + map[depth][i], count + 1);
                visit[i] = false;
            }
        }
    }
}
