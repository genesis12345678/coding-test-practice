package bruteForce.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/15661">백준 15661번 - 브루트포스 : 링크와 스타트</a>
 * @since 2024-05-21
 */
public class Seven {

    static int[][] map;
    static boolean[] visit;
    static int n;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //두 팀의 인원수는 같지 않아도 되고, 한 명 이상이어야 한다고 했다.
        //한 쪽 팀원의 수가 1 ~ n-1 일 때 모든 경우의 수를 확인해야 한다.
        for (int i = 1; i < n; i++) {
            divide(0, 0, i);
        }

        System.out.print(min);
    }

    static void divide(int depth, int start, int t) {
        if (depth == t) { //2 구역으로 나눠졌으면 능력치를 구해서 최솟값을 갱신한다.
            min = Math.min(min, calculate());

            //최소가 0이면 더 볼 것도 없기에 종료한다.
            if (min == 0) {
                System.out.println(0);
                System.exit(0);
            }
            return;
        }

        for (int i = start; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                divide(depth + 1, i + 1, t);
                visit[i] = false;
            }
        }
    }

    static int calculate() {

        int startSum = 0;
        int linkSum = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (visit[i] && visit[j]) { //true를 스타트 팀으로 본다.
                    startSum += map[i][j] + map[j][i];
                }
                else if (!visit[i] && !visit[j]) { //false를 링크 팀으로 본다.
                    linkSum += map[i][j] + map[j][i];
                }
            }
        }

        return Math.abs(startSum - linkSum);
    }
}

