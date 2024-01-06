package Greedy.Fourty.nine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 유니온 파인드(합집합 찾기)
 * 여러 노드 중 두 개의 노드가 서로 같은 집합에 속하는지 판별한다.
 * union(a, b) : a와 b노드가 각각 속한 집합을 합친다.
 * find(a) : a가 어떤 집합에 대표 노드를 찾는다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/10775">백준 10775번 : 분리 집합 - 집합 표현하기</a>
 * @since 2024-01-06
 */
public class Union {

    static int[] parent; // 자기의 부모 노드가 무엇인지 나타내는 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];

        /**
         * 부모 노드 초기화
         * 처음에는 자신의 인덱스가 부모가 된다.(1부터)
         */
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (x == 0) {
                union(a, b);
            } else {
                sb.append(checkSame(a, b) ? "YES" : "NO").append("\n");
            }
        }

        System.out.println(sb);
    }

    /**
     * find 연산 - 대표 노드를 찾는다.
     * 핵심은 재귀를 통해 거치는 노드들을 재귀를 빠져나오면서 대표 노드로 변경해준다.
     * @param a 대표 노드가 무엇인지 알고싶은 수
     * @return a가 속한 집합의 대표 노드
     */
    static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        parent[a] = find(parent[a]);
        return parent[a];
    }

    /**
     * union 연산 - 집합을 합친다.
     * 일반적으로 더 작은 값 쪽으로 합친다.
     */
    static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }

    /**
     * 집합의 대표 노드가 같은지 판단하는 연산
     */
    static boolean checkSame(int a, int b) {
        return find(a) == find(b);
    }
}
