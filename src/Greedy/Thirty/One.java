package Greedy.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 총 휴가 기간 중에 연속되는 일수를 몇번 쓸 수 있냐를 구한다.
 * 그 몇번을 사용가능한 일수와 곱해준다.
 * 이 때 남은 휴가 기간을 더해주면 되는데 주의할 점이 있다.
 * 남은 휴가 기간이 있더라도 사용 가능 일수가 정해져 있기 때문이다.
 * 그래서 남은 휴가 기간과 사용 가능 일수 중 최소값을 더해주어야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/4796" >백준 4796번 : 그리디 알고리즘 - 캠핑</a>
 * @since 2024-01-01
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int caseNum = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken()); // 사용 가능 일수
            int P = Integer.parseInt(st.nextToken()); // 연속하는 일수
            int V = Integer.parseInt(st.nextToken()); // 휴가 기간

            if (L == 0 && P == 0 && V == 0) {
                break;
            }
            int days = (V / P) * L + Math.min(V % P, L);

            sb.append("Case ").append(caseNum).append(": ").append(days).append("\n");

            caseNum++;
        }
        System.out.println(sb);
    }
}
