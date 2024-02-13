package dfs.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 크기가 5x5 고정으로 매우 작다. 브루트포스를 사용한다.
 * 각 위치에서 dfs 탐색을 통해 6자리 수가 만들어지면 리스트에 저장한다.
 * 이 때 서로 다른 수들을 구해야 하니 Set 자료구조를 사용해야 한다.
 * 또한 한 번 거쳤던 칸도 재방문이 가능하니 방문 배열은 필요 없다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2210">백준 2210번 : 깊이우선탐색 - 숫자판 점프</a>
 * @since 2024-02-13
 */
public class Two {
    static int[][] map;
    static Set<Integer> result = new HashSet<>();
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[5][5];
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dfs(i, j, 0, 0);
            }
        }

        System.out.println(result.size());
    }

    static void dfs(int x, int y, int depth, int num) {
        if (x >= 0 && y >= 0 && x < 5 && y < 5) {
            num = num * 10 + map[x][y];// 수 갱신

            //depth가 5, 즉 6자리 수가 만들어지면 결과 리스트에 저장한다.
            if (depth == 5) {
                result.add(num);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + mx[i];
                int ny = y + my[i];

                dfs(nx, ny, depth + 1, num);
            }
        }
    }
}
