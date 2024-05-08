package bruteForce.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 가로선을 0개, 1개, 2개, 3개 놓았을 때 조작이 가능하면 현재 개수를 출력하고 종료한다.
 * 3개까지 놓았을 때도 종료가 안 되면 -1을 출력한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/15684">백준 15684번 - 브루트포스 : 사다리 조작</a>
 * @since 2024-05-04
 */
public class Seven {
    static int[][] ladder;
    static int n, m, h;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        ladder = new int[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ladder[a][b] = 1;
        }

        for (int i = 0; i <= 3; i++) {
            addLine(1,0, i);
        }

        System.out.print(-1);
    }

    static void addLine(int r, int count, int depth) {
        if (count == depth) {
            if (check()) {
                System.out.print(count);
                System.exit(0);
            }
            return;
        }

        for (int i = r; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                //가로선을 추가할 수 있는지 확인한다.
                //현재 위치는 물론 두 가로선이 연속하면 안 되므로 이전과 다음 위치도 확인한다.
                if (ladder[i][j] == 0 && ladder[i][j - 1] == 0 && ladder[i][j + 1] == 0) {
                    ladder[i][j] = 1;
                    addLine(i,count + 1, depth); //i의 시작점을 넘겨주어야 시간을 줄일 수 있다.
                    ladder[i][j] = 0;
                }
            }
        }
    }

    static boolean check() {
        for (int i = 1; i <= n; i++) {
            int now = i;
            for (int j = 1; j <= h; j++) {
                if (ladder[j][now] == 1) {
                    now++;
                } else if (ladder[j][now - 1] == 1) {
                    now--;
                }
            }

            if (now != i) {
                return false;
            }
        }

        return true;
    }
}
