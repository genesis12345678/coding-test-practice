package Greedy.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 입력을 문자로 받아서 최솟값은 5로 변경해서 합하는 것이고
 * 최댓값은 6으로 변경해서 합하는 것이다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2864">백준 2864번 : 그리디 알고리즘 - 5와 6의 차이</a>
 * @since 2024-01-02
 */
public class Six{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String A = st.nextToken();
        String B = st.nextToken();

        int min = Integer.parseInt(A.replace('6','5')) + Integer.parseInt(B.replace('6','5'));
        int max = Integer.parseInt(A.replace('5','6')) + Integer.parseInt(B.replace('5','6'));

        System.out.println(min + " " + max);
    }
}
