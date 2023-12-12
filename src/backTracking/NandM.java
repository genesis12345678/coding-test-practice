package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NandM {
    public static int n;
    public static int m;
    public static int[] arr;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];

        solution(0);

        System.out.println(sb);
    }

    /**
     * 백트래킹 알고리즘 메소드
     * @param idx index = 현재 배열의 위치
     *            처음에는 0으로 시작한다.
     */
    private static void solution(int idx) {
        /**
         * 배열위치와 배열크기가 같아진다면
         * sb에 append 하고
         * 이전 콜스택으로 돌아간다.
         */

        if (idx == m) {
            for (int i : arr) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i < n + 1; i++) {
            arr[idx] = i;
            solution(idx + 1);
        }
    }
}
