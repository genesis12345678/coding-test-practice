package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 처음부터 끝까지 하나씩 탐색해서 최대 깊이를 재는 방법은 당연히 시간 초과가 날 것이다.
 * 그렇다면 메모리제이션을 사용해야 한다. map과 같은 크기의 memory 배열을 생성해서 각 위치에서 최대로 갈 수 있는 깊이를 저장한다.
 * 각 좌표를 dfs 탐색하다가 memory에 저장되어 있는(탐색했던 경로) 경로를 만나면 더 깊이 탐색하지 않고 저장되어 있던 깊이를 더해주면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1937">백준 1937번 : 깊이우선탐색 - 욕심쟁이 판다</a>
 * @since 2024-02-09
 */
public class Seven {

    static int n;
    static int[][] map;
    static int[][] memory;
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        memory = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, dfs(i, j));
            }
        }
        System.out.println(result);
        for (int[] ints : memory) {
            System.out.println("memory = " + Arrays.toString(ints));
        }
    }

    static int dfs(int x, int y) {
        if (memory[x][y] != 0) {
            return memory[x][y];
        }

        memory[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + mx[i];
            int ny = y + my[i];

            if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                if (map[x][y] < map[nx][ny]) {
                    memory[x][y] = Math.max(memory[x][y], dfs(nx, ny) + 1);
                }
            }
        }
        return memory[x][y];
    }
}
