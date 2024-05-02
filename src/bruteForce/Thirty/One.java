package bruteForce.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * map 정보를 입력받으면서 최소 높이와 최대 높이를 구한다.
 * 최소 높이 ~ 최대 높이 사이가 가능한 모든 경우의 수다.
 * 가능한 모든 높이에 대해 채워야 할 블록과 제거해야 할 블록 개수를 구한다.
 * 걸리는 시간을 계산하여 최소 시간과 땅 높이를 구한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/18111">백준 18111번 - 브루트포스 : 마인크래프트</a>
 * @since 2024-04-29
 */
public class One {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        int lowest = Integer.MAX_VALUE;
        int highest = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                lowest = Math.min(lowest, map[i][j]);
                highest = Math.max(highest, map[i][j]);
            }
        }

        int minTime = Integer.MAX_VALUE;
        int height = 0;

        for (int h = lowest; h <= highest; h++) { //가능한 최소 높이에서 최대 높이까지
            int toFill = 0; //현재 높이에서 채워야 할 블록 개수
            int toRemove = 0; //현재 높이에서 제거해야 할 블록 개수

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int diff = map[i][j] - h;

                    //차이가 양수면 높이를 맞추기 위해 제거해야 한다.
                    if (diff > 0) {
                        toRemove += diff;

                    //차이가 음수면 높이를 맞추기 위해 채워야 한다.
                    //음수이므로 빼기 연산을 해줘야 덧셈이 됨
                    } else {
                        toFill -= diff;
                    }
                }
            }

            //블록을 제거하면 인벤토리에 넣는다.
            //제거하면 인벤토리에서 꺼내쓴다.
            //제거해야 할 개수가 더 많으면 안 된다.
            if (b + toRemove >= toFill) {

                //제거는 2초, 채우는 건 1초
                int time = toRemove * 2 + toFill;

                //답이 여러 개라면 가장 높은 것을 골라야 하므로 크거나 같음 조건
                if (minTime >= time) {
                    minTime = time;
                    height = h;
                }
            }
        }

        System.out.print(minTime + " " + height);
    }
}
