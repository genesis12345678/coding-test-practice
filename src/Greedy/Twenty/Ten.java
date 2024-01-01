package Greedy.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 아이디어
 * 음수와 양수를 구분한 뒤 각각 값을 구해준다.
 * 음수는 곱하면 양수가 되니 음수끼리 곱해야 유리하다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1744">백준 1744번 : 그리디 알고리즘 - 수 묶기</a>
 * @since 2024-01-01
 */
public class Ten {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> positive = new ArrayList<>(); // 양수 배열
        List<Integer> negative = new ArrayList<>(); // 음수 배열

        for (int i = 0; i < N; i++) {
            int s = Integer.parseInt(br.readLine());
            if (s > 0) {
                positive.add(s);
            } else {
                negative.add(s);
            }
        }

        /**
         * 양수는 큰값 부터 곱해야 하기에 내림차순 정렬
         * 음수는 작은값 부터 곱해야 하기에 오름차순 정렬
         */
        Collections.sort(positive, Collections.reverseOrder());
        Collections.sort(negative);

        int sum = 0;

        /**
         * 양수끼리의 곱에서 주의할 숫자는 1이다.
         * N x 1 보다 N + 1이 더 크기 때문에 1이 곱해지는 것을 주의해준다.
         * 이 때, (i + 1 < positive.size()) <- 이 조건이 가장 앞에 위치해야 '런타임 에러 (IndexOutOfBounds)'를 예방할 수 있다.
         */
        for (int i = 0; i < positive.size();) {
            if ( i + 1 < positive.size() && positive.get(i) != 1 && positive.get(i + 1) != 1) {
                sum += positive.get(i++) * positive.get(i++);
            } else {
                sum += positive.get(i++);
            }
        }

        /**
         * 음수끼리의 곱에서는 딱히 주의할 것은 없다.
         * -1이나 0이랑 곱해지든 어찌됐든 자기 자신보다는 커진다.
         */
        for (int i = 0; i < negative.size();) {
            if (i + 1 < negative.size()) {
                sum += negative.get(i++) * negative.get(i++);
            } else {
                sum += negative.get(i++);
            }
        }
        System.out.println(sum);
    }
}
