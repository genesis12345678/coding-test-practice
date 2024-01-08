package Greedy.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 각 자리의 문자와 최대한 많이 겹쳐야 한다.
 * 각 문자의 N번째 자리를 순회하면서 최대빈도 문자를 뽑아내 문자를 만든다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1969">백준 1969번 : 그리디 알고리즘 - DNA</a>
 * @since 2024-01-08
 */
public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        int sum = 0;
        for (int i = 0; i < M; i++) {
            Map<Character, Integer> map = new HashMap<>();
            char ch = ' ';
            int max = Integer.MIN_VALUE;

            for (int j = 0; j < N; j++) {
                String str = arr[j];
                char currCh = str.charAt(i);
                map.put(currCh, map.getOrDefault(currCh, 0) + 1);
            }
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                Integer value = entry.getValue();
                Character key = entry.getKey();

                if (value > max) {
                    max = value;
                    ch = key;
                    /**
                     * key = 반복하면서 찾은 문자
                     * ch = 현재 최대빈도 문자
                     * key와 ch를 비교할 때 음수가 나온다는 것은 key가 더 사전순으로 앞에 있다는 뜻이다.
                     * Ex) "A".compareTo("B") == -1, "A".compareTo("A") == 0
                     */
                } else if (value == max && key.compareTo(ch) < 0) {
                    ch = key;
                }
            }
            sb.append(ch);
            sum += N - max;
        }
        sb.append("\n").append(sum);

        System.out.println(sb);
    }
}
