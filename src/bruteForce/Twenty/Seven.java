package bruteForce.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 이런 문제의 포인트는 재귀마다 이전 depth의 map 같은 정보를 복사해두는 것 인것 같다.
 * 먼저 CCTV 위치 정보를 저장해두고, 재귀의 depth로 CCTV 정보를 꺼내 그 위치를 기준으로 CCTV 번호에 따라 CCTV를 배치한다.
 * 1번부터 5번까지 모두 정해진 방향이 있으므로 경우의 수가 각각 다르다.
 * 1번은 총 4개, 2번 2개, 3번 4개, 4번 4개, 5번은 1개의 경우의 수로 배치될 수 있다.
 * 재귀호출 마다 각기 다른 경우의 수를 확인하여 모든 경우의 수를 탐색할 수 있다.
 * 중요한 것은 재귀호출 각 depth의 map 정보를 복사해두는 것이다. 그래야 이전 depth의 map 정보를 다시 사용할 수 있다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/15683">백준 15683번 - 브루트포스 : 감시</a>
 * @since 2024-04-26
 */
public class Seven {

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

                if (0 < map[i][j] && map[i][j] < 6) { //1~5 CCTV 정보 저장
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

        //현재 depth의 map 정보 복사, 재귀를 빠져나오고 다른 경우의 수를 확인할 때 직전 map 정보를 그대로 사용하기 위함.
        int[][] temp = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, temp[i], 0, m);
        }

        switch (num) {
            case 1:
                //1번 CCTV, 한쪽 방향으로만 갈 수 있다.
                //총 4개의 경우의 수로 갈 수 있으며, 한 쪽으로 CCTV를 배치한 후 바로 다음 depth로 간다.
                for (int i = 0; i < 4; i++) {
                    settingCCTV(x, y, i);

                    backTrack(depth + 1);

                    resetMap(temp); //다른 경우의 수를 보기 위해 직접 map 정보 복구
                }
                break;
            case 2:
                //2번 CCTV, 서로 반대 방향으로만 갈 수 있다.
                //총 2개의 경우의 수로 갈 수 있으며, 한 번에 두 방향으로 CCTV를 배치한 후 다음 depth로 간다.
                for (int i = 0; i < 2; i++) {
                    settingCCTV(x, y, i);
                    settingCCTV(x, y, i + 2);

                    backTrack(depth + 1);

                    resetMap(temp);
                }
                break;
            case 3:
                //3번 CCTV, 직각 방향으로 갈 수 있다.
                //총 4개의 경우의 수로 갈 수 있으며, 한 번에 두 방향으로 CCTV를 배치한 후 다음 depth로 간다.
                for (int i = 0; i < 4; i++) {
                    settingCCTV(x, y, i);
                    settingCCTV(x, y, (i + 1) % 4);

                    backTrack(depth + 1);

                    resetMap(temp);
                }
                break;
            case 4:
                //4번 CCTV, 세 방향으로 갈 수 있다.
                //총 4개의 경우의 수로 갈 수 있으며, 한 번에 세 방향으로 CCTV를 배치한 후 다음 depth로 간다.
                for (int i = 0; i < 4; i++) {
                    settingCCTV(x, y, i);
                    settingCCTV(x, y, (i + 1) % 4);
                    settingCCTV(x, y, (i + 2) % 4);

                    backTrack(depth + 1);

                    resetMap(temp);
                }
                break;
            case 5:
                //5번 CCTV, 상하좌우 모두 갈 수 있다.
                //경우의 수가 딱 한 가지 이기 때문에 map을 다시 돌려놓을 필요 없다.
                //4 방향으로 CCTV를 배치한 후 다음 depth로 간다.
                for (int i = 0; i < 4; i++) {
                    settingCCTV(x, y, i);
                }
                backTrack(depth + 1);
                break;
        }
    }

    static void resetMap(int[][] temp) {
        for (int j = 0; j < n; j++) {
            System.arraycopy(temp[j], 0, map[j], 0, m);
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
