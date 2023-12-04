package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static boolean[][] arr;
    public static int min = 64;
    public static void main(String[] args) throws IOException {

        /**
         * 숫자를 입력받아 공백 단위로 나누고
         * N과 M에 각각 형변환후 저장
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        /**
         * N x M 만큼 boolean 배열을 만든다.
         * 처음에는 모두 false로 저장된다.
         */
        arr = new boolean[N][M];

        /**
         * 예제를 복사하여 for문을 돈다.
         * 문자가 'W'면 true로 바꾸고, 아니면 false로 저장한다.
         * 시간복잡도는 O(N x M)이 된다.
         */
        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j) == 'W';
            }
        }

        /**
         * 경우의 수를 구하면서 나온
         * (가로 칸 개수 - 7) * (세로 칸 개수 - 7)
         * 나올 수 있는 8x8 경우의 수 만큼 for문을 돌며
         * 변경되어야 할 색들의 개수를 구한다.
         */
        int x = N - 7;
        int y = M - 7;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                find(i, j);
            }
        }
        System.out.println(min);
    }

    /**
     *
     * @param x x좌표의 시작점
     * @param y y좌표의 시작점
     */
    private static void find(int x, int y) {
        /**
         * x와 y좌표 시작점에 각각 8을 더해
         * for문에서 8x8 구역 내에서 찾을 수 있도록 함.
         * 만약 for (int i = x; i <= x_end; i++)로 변경한다면
         * 7을 더해도 됨.
         */
        int x_end = x + 8;
        int y_end = y + 8;
        int count = 0;

        // 구하고자 하는 8x8 내의 좌측상단의 색깔
        boolean b = arr[x][y];

        for (int i = x; i < x_end; i++) {
            for (int j = y; j < y_end; j++) {
                /**
                 * 현재 위치와 for문을 돌며 가로로 이동하는 색깔과 비교
                 * 예상되는 결과(b)와 위치 arr[i][j]가 일치하지 않으면
                 * count 1 증가
                 * 그리고 다음 칸, 다음 줄로 넘어가기 전에 색이 바뀌어야 하므로
                 * true면 false로, false면 true로 변경한다.
                 */
                if (b != arr[i][j]) {
                    count ++;
                }
                b = !b;
            }
            b= !b;
        }
        /**
         * 첫 번째 칸을 기준으로 할 때의 색칠 할 개수(count)와
         * 첫 번째 칸의 반대되는 색을 기준으로 할 때의
         * 색칠 할 개수(64 - count) 중 최솟값을 count에 저장
         */
        count = Math.min(count, 64 - count);

        /**
         * 이전까지의 경우 최솟값보다 현재 count 값이
         * 더 작을 경우 최솟값을 갱신
         */
        min = Math.min(min, count);
    }
}
