import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int n, m;
    static int min = Integer.MAX_VALUE;
    static ArrayList<CCTV> cctvs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (0 < map[i][j] && map[i][j] < 6) {
                    cctvs.add(new CCTV(i, j));
                }
            }
        }

        backTrack(0);

        System.out.print(min);
    }

    static void backTrack(int depth) {
        if (depth == cctvs.size()) {
            getMin();
            return;
        }

        CCTV cctv = cctvs.get(depth);
        int x = cctv.x;
        int y = cctv.y;
        int num = map[x][y];

        int[][] temp = new int[n][m];

        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, temp[i], 0, m);
        }

        switch (num) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    settingCCTV(x, y, i);

                    backTrack(depth + 1);

                    for (int j = 0; j < n; j++) {
                        System.arraycopy(temp[j], 0, map[j], 0, m);
                    }
                }
                break;
            case 2:
                for (int i = 0; i < 2; i++) {
                    settingCCTV(x, y, i);
                    settingCCTV(x, y, i + 2);

                    backTrack(depth + 1);

                    for (int j = 0; j < n; j++) {
                        System.arraycopy(temp[j], 0, map[j], 0, m);
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 4; i++) {
                    settingCCTV(x, y, i);
                    settingCCTV(x, y, (i + 1) % 4);

                    backTrack(depth + 1);

                    for (int j = 0; j < n; j++) {
                        System.arraycopy(temp[j], 0, map[j], 0, m);
                    }
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    settingCCTV(x, y, i);
                    settingCCTV(x, y, (i + 1) % 4);
                    settingCCTV(x, y, (i + 2) % 4);

                    backTrack(depth + 1);

                    for (int j = 0; j < n; j++) {
                        System.arraycopy(temp[j], 0, map[j], 0, m);
                    }

                }
                break;
            case 5:
                for (int i = 0; i < 4; i++) {
                    settingCCTV(x, y, i);
                }
                backTrack(depth + 1);
                break;
        }
    }

    static void settingCCTV(int x, int y, int d) {
        switch (d) {
            case 0: //위로
                for (int i = x - 1; i >= 0; i--) {
                    if (map[i][y] == 6) {
                        break;
                    }
                    if (map[i][y] == 0) {
                        map[i][y] = -1;
                    }
                }
                break;

            case 2: //아래로
                for (int i = x + 1; i < n; i++) {
                    if (map[i][y] == 6) {
                        break;
                    }
                    if (map[i][y] == 0) {
                        map[i][y] = -1;
                    }
                }
                break;

            case 1: //오른쪽으로
                for (int i = y + 1; i < m; i++) {
                    if (map[x][i] == 6) {
                        break;
                    }
                    if (map[x][i] == 0) {
                        map[x][i] = -1;
                    }
                }
                break;

            case 3: //왼쪽으로
                for (int i = y - 1; i >= 0; i--) {
                    if (map[x][i] == 6) {
                        break;
                    }
                    if (map[x][i] == 0) {
                        map[x][i] = -1;
                    }
                }
                break;
        }
    }

    static void getMin() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    sum++;
                }
            }
        }

        min = Math.min(min, sum);
    }

    static class CCTV {
        int x, y;

        public CCTV(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
