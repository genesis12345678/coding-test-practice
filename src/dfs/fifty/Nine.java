package dfs.fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * dfs 인자에 x, y 좌표 번호랑 같이 현재까지 계산된 숫자와 연산자를 파라미터로 가지고 dfs 탐색한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/17265">백준 17265번 : 깊이우선탐색 - 나의 인생에는 수학과 함께</a>
 * @since 2024-03-02
 */
public class Nine {
    static char[][] map;
    static int n;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        int start = Character.getNumericValue(map[0][0]);
        dfs(0, 0, start, map[0][0]);

        System.out.println(max + " " + min);
    }

    private static void dfs(int x, int y, int num, char operation) {
        int cur = map[x][y] - '0';//아스키 코드 번호로 현재 문자 유추

        if (cur >= 0 && cur <= 5) {//0~5의 숫자면 계산을 해서, 다음 탐색에 이 계산된 숫자를 가지고 간다.
            if (operation == '+') {
                num += cur;
            } else if (operation == '-') {
                num -= cur;
            } else if (operation == '*') {
                num *= cur;
            }
        } else {//문자면 현재 위치의 연산자를 구하고, 다음 탐색에 이 연산자를 가지고 간다.
            operation = map[x][y];
        }

        //마지막 위치에 왔다면 최댓값과 최솟값을 갱신한다.
        if (x == n - 1 && y == n - 1) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        if (x < n - 1) {//아래로 이동
            dfs(x + 1, y, num, operation);
        }
        if (y < n - 1) {//오른쪽으로 이동
            dfs(x, y + 1, num, operation);
        }
    }
}
