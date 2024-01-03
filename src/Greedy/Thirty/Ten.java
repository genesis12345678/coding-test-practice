package Greedy.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 처음에 StringTokenizer로 '.' 기준으로 잘라서 하려다가 '.'은 그대로 다시 출력해야 해서 안된다.
 * '.'이 나오기전까지 'X'의 개수를 세서 "AAAA"나 "BB"로 치환한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1343">백준 1343번 : 그리디 알고리즘 - 폴리오미노</a>
 * @since 2024-01-03
 */
public class Ten {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringBuilder sb = new StringBuilder();

        int xCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'X') { // 'X'의 개수를 센다.
                xCount++;
            } else { // '.'이 나왔을 때
                if (xCount % 2 == 1) { // X가 홀수개라면 절대 만들 수 없다.
                    System.out.println(-1);
                    return;
                }
                /**
                 * 위 if문을 통과해서 X는 짝수개다.
                 * X의 개수에 맞게 "AAAA"와 "BB"를 출력한다.
                 * 반복이 끝나면 xCount는 다시 0으로 되어 다음 X 그룹에 X개수를 셀 수 있다.
                 */
                while (xCount != 0) {
                    if (xCount >= 4) {
                        sb.append("AAAA");
                        xCount -= 4;
                    } else {
                        sb.append("BB");
                        xCount -= 2;
                    }
                }
                sb.append(".");
            }
        }
        // 마지막 '.' 이후에 나온 xCount
        if (xCount > 0) {
            if (xCount % 2 == 1) {
                System.out.println(-1);
                return;
            } else {
                while (xCount != 0) {
                    if (xCount >= 4) {
                        sb.append("AAAA");
                        xCount -= 4;
                    } else {
                        sb.append("BB");
                        xCount -= 2;
                    }
                }
            }
        }

        System.out.println(sb);

    }
}
