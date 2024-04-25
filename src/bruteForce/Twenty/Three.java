package bruteForce.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 1. 초기 채널은 100이므로 N이 100이면 0을 출력하고 끝내도 된다.
 * 2. 100에서 N까지 + 또는 - 만으로 한 채널씩 옮긴다. 즉, |N - 100|
 * 3. 고장나지 않은 버튼을 눌러서 최대한 근접치에 갔다가 + 또는 -로 조정한다.
 * 2, 3번 중에 최소를 구하면 된다.
 * 3번은 재귀호출을 통해 구할 수 있다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1107">백준 1107번 - 브루트포스 : 리모컨</a>
 * @since 2024-04-24
 */
public class Three {

    static boolean[] broken = new boolean[10]; //고장난 버튼
    static int n, m;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        //고장난 버튼이 없는 경우도 있다.
        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < m; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        if (n == 100) { //이미 이동하려는 채널이면 종료
            System.out.println(0);
            return;
        }

        min = Math.min(min, Math.abs(n - 100)); //현재 채널에서 버튼을 누르지 않고 + 또는 - 로만 이동할 때

        dfs(0, 0);

        System.out.print(min);
    }

    static void dfs(int depth, int num) {

        for (int i = 0; i < 10; i++) {
            if (!broken[i]) { //고장나지 않은 번호에 대해서만

                int channel = num * 10 + i; //다음 채널, 현재 채널에서 i를 눌렀을 때

                //현재 채널에서 목표 채널까지 + 또는 -로만 이동하려고 할 때
                //n - channel = + 또는 - 누르는 횟수
                //channel의 길이 = 현재까지 누른 횟수
                //값이 커질수록 점점 줄어들 것이다.
                int count = Math.abs(n - channel) + String.valueOf(channel).length();
                min = Math.min(min, count);

                if (depth < 6) { //목표 채널 n은 6자리를 넘지 않는다.
                    dfs(depth + 1, channel);
                }
            }
        }
    }
}
