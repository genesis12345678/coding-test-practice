package dfs.fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 처음에는 모든 1을 놓을 수 있는 경우의 수마다 dfs를 탐색해서 수를 구했는데 당연히 시간초과가 걸렸다.
 * 해답은 처음에 먼저 1의 그룹별 사이즈를 구한 다음 0인 부분에서 상하좌우 만을 탐색하여 각 그룹의 사이즈를 더해주면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/16932">백준 16932번 : 깊이우선탐색 - 모양 만들기</a>
 * @since 2024-03-01
 */
public class Six {
    static int[][] map;
    static int n, m;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int max = Integer.MIN_VALUE;
    static Map<Integer, Integer> groupSize = new HashMap<>();//key=그룹번호, value=그룹 사이즈

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

        calculateGroupSize();//그룹 번호와 그룹 사이즈 계산

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {//0 자리에 하나씩 배치해보면서 주변 그룹의 사이즈를 계산하면서 최댓값을 갱신한다.
                    max = Math.max(max, searchSurround(i, j));
                }
            }
        }
        System.out.println(max);
    }

    private static int searchSurround(int x, int y) {
        Set<Integer> set = new HashSet<>();//같은 그룹을 만날 수 있으니 Set을 사용해야 한다.

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                if (map[nx][ny] != 0) {
                    set.add(map[nx][ny]);//그룹 번호 저장
                }
            }
        }
        int total = 1;//원래 0이 있던 자리도 사이즈에 포함해야 하므로 1부터 더해준다.
        for (int num : set) {
            total += groupSize.get(num);//Set에 저장된 그룹 번호로 Map에서 value를 꺼내 총 사이즈를 구한다.
        }
        return total;
    }


    private static void calculateGroupSize() {
        visit = new boolean[n][m];
        int groupNum = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {//그룹을 찾을 때마다 각 그룹의 번호와 사이즈를 구한다.
                    groupSize.put(groupNum, groupDfs(i, j, groupNum++));
                }
            }
        }

    }

    private static int groupDfs(int x, int y, int groupNum) {
        visit[x][y] = true;
        map[x][y] = groupNum;

        int count = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                if (!visit[nx][ny] && map[nx][ny] == 1) {
                    count += groupDfs(nx, ny, groupNum);
                }
            }
        }
        return count;
    }
}
