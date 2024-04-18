package programmers.DfsBfs;

import java.util.LinkedList;
import java.util.Queue;

public class GameMap {
    public static void main(String[] args) {
        int[][] maps = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1},
        };

        System.out.println(solution(maps));
    }

    private static int solution(int[][] maps) {

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visit = new boolean[n][m];

        Queue<Point> qu = new LinkedList<>();
        qu.offer(new Point(0, 0, 1));
        visit[0][0] = true;

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            int x = now.x;
            int y = now.y;
            int v = now.v;

            if (x == n - 1 && y == m - 1) {
                return v;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visit[nx][ny] && maps[nx][ny] == 1) {
                    qu.offer(new Point(nx, ny, v + 1));
                    visit[nx][ny] = true;
                }
            }
        }

        return -1;
    }

    static class Point{
        int x, y, v;

        public Point(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }
}
