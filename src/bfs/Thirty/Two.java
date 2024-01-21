package bfs.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Two {
    static int[][] map;
    static boolean[][] visit;
    static int[] mx = new int[]{-1, 1, 0, 0};
    static int[] my = new int[]{0, 0, -1, 1};
    static int N, L, R;
    static List<int[]> list; // 연합을 이룬 나라의 인구수가 담길 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        // 입력 부분
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;

        while (true) {
            boolean flag = false;
            visit = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j]) {
                        // bfs를 통해 연합을 이룬 나라들의 총 인구수가 반환된다.
                        int sum = bfs(i, j);
                        // 연합을 이룬 나라가 1개보단 많아야 한다.
                        if (list.size() > 1) {
                            population(sum); // 인구 이동
                            flag = true; // 인구 이동이 일어났으므로 한번 더 진행할 수 있다.
                        }
                    }
                }
            }
            if(!flag) break;
            count++;
        }
        System.out.println(count);
    }

    static int bfs(int a, int b) {
        Queue<int[]> qu = new LinkedList<>();
        list = new ArrayList<>();

        qu.offer(new int[]{a, b});

        int sum = 0;
        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + mx[i];
                int ny = y + my[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    int result = Math.abs(map[x][y] - map[nx][ny]);
                    if (!visit[nx][ny] && result >= L && result <= R) {
                        qu.offer(new int[]{nx, ny});
                        list.add(new int[]{nx, ny});
                        visit[nx][ny] = true;
                        sum += map[nx][ny];
                    }
                }
            }
        }
        return sum;
    }

    static void population(int sum) {
        int avg = sum / list.size();
        for (int[] n : list) {
            map[n[0]][n[1]] = avg;
        }
    }
}

