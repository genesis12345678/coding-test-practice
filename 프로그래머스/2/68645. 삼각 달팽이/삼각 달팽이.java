class Solution {
    public int[] solution(int n) {
        int total = (n * (n + 1)) / 2;
        int[] result = new int[total];

        int[][] arr = new int[n][n];

        int x = 0;
        int y = 0;
        int num = 1;

        int[] dx = {1, 0, -1};
        int[] dy = {0, 1, -1};
        int dir = 0;

        while (num <= total) {
            arr[x][y] = num++;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n || arr[nx][ny] != 0) {
                dir = (dir + 1) % 3;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            x = nx;
            y = ny;
        }

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                result[index++] = arr[i][j];
            }
        }

        return result;
    }
}