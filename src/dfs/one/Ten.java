package dfs.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * 아이디어
 * 물을 옮기는 경우의 수는 총 6가지이다.
 * 1. a -> b
 * 2. a -> c
 * 3. b -> a
 * 4. b -> c
 * 5. c -> a
 * 6. c -> b
 * 각 경우의 수에 대해 최대 용량을 넘어설 때와 그렇지 않을 때를 dfs로 탐색한다.
 * 탐색하면서 첫 번째 물통이 비어있는 순간에는 세 번째 물통에 담겨있는 물의 양을 Set에 저장한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2251">백준 2251번 : 깊이우선탐색 - 물통</a>
 * @since 2024-02-12
 */
public class Ten {
    static int A, B, C;
    static boolean[][] visit = new boolean[201][201];
    static Set<Integer> answer = new TreeSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        //처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득 차 있다.
        dfs(0, 0, C);

        //TreeSet 이므로 정렬이 되어 있다.
        for (int integer : answer) {
            sb.append(integer).append(" ");
        }

        System.out.println(sb);
    }

    static void dfs(int a, int b, int c) {
        if (visit[a][b]) {
            return;
        }
        visit[a][b] = true;

        //첫 번째 물통이 비어있을 때 세 번째 물통에 담겨있을 수 있는 물의 양 저장
        if (a == 0) {
            answer.add(c);
        }

        // 현재 상태 출력 (디버깅용)
         System.out.println("a: " + a + ", b: " + b + ", c: " + c);

        // a -> b
        if (a + b > B) {//a에서 b로 옮겼는데 B의 최대 용량을 초과했다.
            dfs(a + b - B, B, c);//a는 b로 옮기고 남은 양, b는 꽉 찼으니까 최대 용량, c는 그대로
        } else {//a에서 b로 옮겼는데 b의 최대 용량을 넘지 않았다.
            dfs(0, a + b, c);//a는 비워지고, b는 합해진 양, c는 그대로
        }

        // a -> c
        if (a + c > C) {//a에서 c로 옮겼는데 C의 최대 용량을 초과했다.
            dfs(a + c - C, b, C);//a는 c로 옮기고 남은 양, b는 그대로, c는 꽉 찼으니까 최대 용량
        } else {//a에서 c로 옮겼는데 c의 최대 용량을 넘지 않았다.
            dfs(0, b, a + c);//a는 비워지고, b는 그대로, c는 합해진 양
        }

        // b -> a
        if (b + a > A) {//b에서 a로 옮겼는데 A의 최대 용량을 초과했다.
            dfs(A, b + a - A, c);//a는 꽉 찼으니까 최대 용량, b는 a로 옮기고 남은 양, c는 그대로
        } else {//b에서 a로 옮겼는데 a의 최대 용량을 넘지 않았다.
            dfs(b + a, 0, c);//a는 합해진 양, b는 비워지고, c는 그대로
        }

        // b -> c
        if (b + c > C) {//b에서 c로 옮기는데 C의 최대 용량을 초과했다.
            dfs(a, b + c - C, C);//a는 그대로, b는 c로 옮기고 남은 양, C는 꽉 찼으니까 최대 용량
        } else {//b에서 c로 옮기는데 c의 최대 용량을 넘지 않았다.
            dfs(a, 0, b + c);//a는 그대로, b는 비워지고, c는 합해진 양
        }

        // c -> a
        if (c + a > A) {//c에서 a로 옮기는데 A의 최대 용량을 초과했다.
            dfs(A, b, c + a - A);//A는 꽉 찼으니까 최대 용량, b는 그대로, c는 a로 옮기고 남은 양
        } else {//c에서 a로 옮기는데 a의 최대 용량을 넘지 않았다.
            dfs(a + c, b, 0);//a는 합해진 양, b는 그대로, c는 비워진다.
        }

        // c -> b
        if (c + b > B) {//c에서 b로 옮기는데 B의 최대 용량을 초과했다.
            dfs(a, B, c + b - B);//a는 그대로, B는 꽉 찼으니까 최대 용량, c는 b로 옮기고 남은 양
        } else {//c에서 b로 옮기는데 b의 최대 용량을 넘지 않았다.
            dfs(a, b + c, 0);//a는 그대로, b는 합해진 양, c는 비워진다.
        }
    }
}
