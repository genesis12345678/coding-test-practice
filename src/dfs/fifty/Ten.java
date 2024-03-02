package dfs.fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 아이디어
 * 문자를 숫자로 치환 후 단방향으로 인접 리스트를 연결한다.
 * 예를 들어 a is b 면 1에서 2로 갈 수 있냐를 dsf를 통해 판단한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/15723">백준 15723번 : 깊이우선탐색 - n단 논법</a>
 * @since 2024-03-02
 */
public class Ten {
    static List<List<Integer>> graph = new ArrayList<>();//인접 리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        //알파벳 개수만큼 생성
        for (int i = 0; i <= 26; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            String s = br.readLine();//a is b
            int from = s.charAt(0) -'a'; //a -> 1
            int to = s.charAt(5) - 'a'; //b -> 2
            graph.get(from).add(to);
        }

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            String s = br.readLine();

            int from = s.charAt(0) -'a';
            int to = s.charAt(5) - 'a';

            sb.append(dfs(from, to) ? "T" : "F").append("\n");
        }

        System.out.println(sb);
    }

    private static boolean dfs(int from, int to) {
        if (from == to) {
            return true;
        }

        for (int next : graph.get(from)) {
            return dfs(next, to);
        }
        return false;
    }
}
