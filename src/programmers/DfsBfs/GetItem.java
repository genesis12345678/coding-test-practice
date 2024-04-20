package programmers.DfsBfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GetItem {

    static boolean[][] possible = new boolean[51][51];

    public static void main(String[] args) {
        int[][] rectangle = {
//                {1, 1, 7, 4},
//                {3, 2, 5, 5},
//                {4, 3, 6, 9},
//                {2, 6, 8, 8},
                {1, 1, 5, 7},
        };
        int characterX = 1;
        int characterY = 3;

        int itemX = 7;
        int itemY = 8;

        int result = solution(rectangle, characterX, characterY, itemX, itemY);
        System.out.println(result);
    }

    static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        for (int[] p : rectangle) {

            int x1 = p[0];
            int y1 = p[1];
            int x2 = p[2];
            int y2 = p[3];

            for (int i = x1; i <= x2; i++) {
                possible[i][y1] = possible[i][y2] = true;
            }

            for (int i = y1; i <= y2; i++) {
                possible[x1][i] = possible[x2][i] = true;
            }


        }

        Queue<Node> qu = new LinkedList<>();
        qu.offer(new Node(characterX, characterY, 0));

        boolean[][] visit = new boolean[51][51];
        visit[characterX][characterY] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int min = Integer.MAX_VALUE;

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            int x = now.x;
            int y = now.y;
            int count = now.count;

            if (x == itemX && y == itemY) {
                min = Math.min(min, count);
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx <= 50 && ny >= 0 && ny <= 50 && !visit[nx][ny] && possible[nx][ny]) {
                    qu.offer(new Node(nx, ny, count + 1));
                    visit[nx][ny] = true;
                }
            }
        }


        return min;
    }

    static class Node{
        int x, y, count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
