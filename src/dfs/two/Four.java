package dfs.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 입력 받은 무방향 그래프를 내림차순으로 방문하기 위해 정렬을 한다.
 * count를 1부터 늘려가면서 방문하지 않은 노드들을 방문한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/24480">백준 24480번 : 알고리즘 수업(깊이우선탐색2)</a>
 * @since 2024-02-13
 */
public class Four {

    static List<List<Integer>> graph = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static int[] visit;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //정점의 수
        int M = Integer.parseInt(st.nextToken()); //간선의 수
        int R = Integer.parseInt(st.nextToken()); //시작 정점

        visit = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        //인접 정점은 내림차순으로 방문해야 한다.
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph.get(i), Collections.reverseOrder());
        }

        //시작 정점의 방문 순서는 1이다.
        count = 1;
        //시작 정점부터 시작
        dfs(R);

        for (int i = 1; i <= N; i++) {
            sb.append(visit[i]).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int n) {
        visit[n] = count;

        for (int num : graph.get(n)) {
            if (visit[num] == 0) {
                count++;
                dfs(num);
            }
        }

    }
}
