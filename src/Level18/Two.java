package Level18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * <a href = "https://www.acmicpc.net/problem/25192">백준 25192번 : 심화2 - 인사성 밝은 곰곰이</a>
 * @since 2023-12-25
 */
public class Two {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 입력 문자열을 저장할 Set 자료구조
        Set<String> set = new HashSet<>();
        int sum = 0;
        // N번만큼 반복한다.
        while (N-- > 0) {
            String s = br.readLine();
            if (s.equals("ENTER")) {
                /**
                 * 입력값이 ENTER면, 그 동안 set에 저장된 사람들이 몇명인지 더한다.
                 * ENTER 이후에 들어올 사람들을 또 계산하기 위해 clear를 해준다.
                 * continue로 더 이상 밑으로 가지 않고 다음 반복으로 넘어간다.
                 */
                sum += set.size();
                set.clear();
                continue;
            }
            // "ENTER"가 아닌 문자열을 저장한다.
            set.add(s);
        }
        // 바로 sum을 출력하면 안되고, 마지막 ENTER 이후에 들어온 사람들까지 계산해준다.
        sum += set.size();
        System.out.println(sum);
    }
}
