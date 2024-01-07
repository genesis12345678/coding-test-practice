package Greedy.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 그림을 그려보니 규칙을 찾았다.
 * 커플석(L)이 없으면 그냥 좌석 개수만큼이고
 * 있으면 커플석 두개를 한쌍으로 해서 좌석에서 빼주고 1을 더해준다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2810">백준 2810번 : 그리디 알고리즘 - 컵홀더</a>
 * @since 2024-01-07
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();

        String seat = br.readLine();

        if (!seat.contains("L")) {
            System.out.println(seat.length());
        } else {
            int count = 0;
            for (int i = 0; i < seat.length(); i++) {
                if (seat.charAt(i) == 'L') {
                    count++;
                }
            }
            int result = seat.length() - (count / 2);
            System.out.println(result + 1);
        }
    }
}
