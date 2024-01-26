package bfs.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아이디어
 * D,S,L,R을 각각 계산 후 큐에 넣은 후 bfs 탐색한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/9019">백준 9019번 : 너비우선탐색 - DSLR</a>
 * @since 2024-01-26
 */
public class Nine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            sb.append(bfs(A, B)).append("\n");
        }
        System.out.println(sb);
    }

    static String bfs(int start, int target) {
        boolean[] visit = new boolean[10000];
        Queue<Object[]> qu = new LinkedList<>();
        qu.offer(new Object[]{start, ""});
        visit[start] = true;

        while (true) {
            Object[] now = qu.poll();
            int num = (int) now[0];
            String operations = (String) now[1];

            if (num == target) {
                return operations;
            }

            int nD = D(num);
            int nS = S(num);
            int nL = L(num);
            int nR = R(num);

            if (!visit[nD]) {
                visit[nD] = true;
                qu.offer(new Object[]{nD, operations + "D"});
            }
            if (!visit[nS]) {
                visit[nS] = true;
                qu.offer(new Object[]{nS, operations + "S"});
            }
            if (!visit[nL]) {
                visit[nL] = true;
                qu.offer(new Object[]{nL, operations + "L"});
            }
            if (!visit[nR]) {
                visit[nR] = true;
                qu.offer(new Object[]{nR, operations + "R"});
            }
        }
    }

    static int D(int num) {
        int result = num * 2;
        if (result > 9999) {
            return result % 10_000;
        } else {
            return result;
        }
    }

    static int S(int num) {
        if (num == 0) {
            return 9999;
        } else {
            return num - 1;
        }
    }

    static int L(int num) {
        return (num % 1000) * 10 + num / 1000;
    }

    static int R(int num) {
        return (num % 10) * 1000 + num / 10;
    }
}
