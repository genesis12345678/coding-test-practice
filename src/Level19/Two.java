package Level19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href = "https://www.acmicpc.net/problem/10870">백준 10870번 : 재귀 - 피보나치 수5</a>
 * @since 2023-12-25
 */
public class Two {
    static Map<Integer, Integer> map = new HashMap<>(); // 이전 fibonacci 수열 값을 기록할 Map
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(fibonacci(n));
        System.out.println(map);
    }

    static int fibonacci(int n) {
        // 0과 1은 그대로 0과 1을 출력한다.
        if(n <= 1) return n;

        if (map.containsKey(n)) {
            return map.get(n);
        }

        /**
         * Fn = Fn-1 + Fn-2 (n ≥ 2)이다.
         * N = 5면, fibonacci(1) + fibonacci(0)까지의 값을 map에 저장해 둔다.
         * N이 5니까 return fibonacci(4) + fibonacci(3)의 값이다.
         * fibonacci(4)에서 각 계산된 값이 저장되어 있으므로,
         * fibonacci(1) + fibonacci(0)에서 다시 돌아올 때는 map에서 꺼내서 쓸 수 있다.
         */
        int i = fibonacci(n - 1) + fibonacci(n - 2);
        map.put(n, i);
        return i;
    }
}
