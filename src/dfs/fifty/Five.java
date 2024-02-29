package dfs.fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 범위가 작으니까 2자리를 놓을 수 있는 모든 경우의 수를 계산한다.
 * 이떄 중요한 건 2(상대의 돌) 주변에 하나라도 1(나의 돌)로 감싸져 있지 않으면 상대 돌을 죽일 수 없어야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/16988">백준 16988번 : 깊이우선탐색 - Baaaaaaduk2 (Easy)</a>
 * @since 2024-02-29
 */
public class Five {

    static int n, m;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int max = Integer.MIN_VALUE;
    static boolean flag;//2 주위에 0(빈 칸)이 존재하는지 여부
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        map = new int[n][m];
        //입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //모든 경우의 수로 돌을 놓는다.
        putMyStone(0, 0);
        System.out.println(max);
    }

    private static void putMyStone(int start, int depth) {
        if (depth == 2) {//2개를 놓았으면
            solution();//계산을 실행한다.
            return;
        }
        for (int i = start; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    putMyStone(i,depth + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void solution() {
        visit = new boolean[n][m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2 && !visit[i][j]) {//상대 돌을 없앨 수 있을 것 같다면
                    flag = false;//flag를 초기화하고
                    count += dfs(i, j);//몇 개의 돌을 없앨 수 있는지 계산한다.
                }
            }
        }
        max = Math.max(max, count);//최댓값 갱신
    }

    private static int dfs(int x, int y) {
        visit[x][y] = true;

        int count = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                if (map[nx][ny] == 0) {//탐색 중 주위에 빈 칸이 있으면 없앨 수 없으니 true 표시
                    flag = true;
                }
                if (map[nx][ny] == 2 && !visit[nx][ny]) {
                    count += dfs(nx, ny);
                }
            }
        }

        if (flag) {//상대 돌을 없앨 수 없다는 표시가 존재하면 0을 반환
            return 0;
        } else {//그렇지 않으면 2의 그룹 사이즈를 반환
            return count;
        }
    }
}
