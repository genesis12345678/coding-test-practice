package Level18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <a href = "https://www.acmicpc.net/problem/26069">백준 26069번 : 심화2 - 붙임성 좋은 총총이</a>
 * @since 2023-12-25
 */
public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 무조건 총총이를 만난 이후부터 춤을 추는 것이므로 총총이 저장
        Set<String> set = new HashSet<>(List.of("ChongChong"));

        // N번만큼 반복
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String p1 = st.nextToken();
            String p2 = st.nextToken();

            /**
             * 총총이가 먼저 저장되어 있으므로 총총이 이후 사람들만 저장된다.
             * 중복이 허용되지 않는 Set이므로 그냥 add해도 문제없다.
             */
            if (set.contains(p1) || set.contains(p2)) {
                set.add(p1);
                set.add(p2);
            }
        }
        System.out.println(set.size());
    }
}
