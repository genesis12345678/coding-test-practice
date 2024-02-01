package bfs.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 시작 위치(집)부터 맥주 20개로 갈 수 있는 거리(1000미터)일때만 각 좌표들을 연결한다.
 * bfs탐색을 하다가 마지막 정점을 찍으면 happy, 그렇지 못하면 sad를 출력한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/9205">백준 9205번 : 너비우선탐색 - 맥주 마시면서 걸어가기</a>
 * @since 2024-02-01
 */

public class Eight {

    static int n;
    static List<List<Integer>> graph;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());

            //편의점 개수(n)외에 집이랑 페스티벌 좌표가 항상 고정으로 늘어나므로 n + 2로 초기화한다.
            int[][] nodes = new int[n + 2][2];
            for (int i = 0; i < n + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                nodes[i] = new int[]{x, y};//각 좌표들을 입력받는다.
            }

            graph = new ArrayList<>();
            for (int i = 0; i < n + 2; i++) {
                graph.add(new ArrayList<>());
            }

            //처음 위치부터 마지막 전까지
            for (int i = 0; i < n + 1; i++) {
                //처음 위치 다음(i+1)부터 마지막까지
                for (int j = 1; j < n + 2; j++) {
                    //x 좌표의 차이 + y 좌표의 차이
                    int distance = Math.abs(nodes[i][0] - nodes[j][0]) + Math.abs(nodes[i][1] - nodes[j][1]);
                    //좌표 사이의 거리가 1000 이하라면 두 정점은 이동 가능하다. 연결해준다.
                    if (distance <= 1000) {
                        graph.get(i).add(j);
                        graph.get(j).add(i);
                    }
                }
            }
            bfs();
        }
        System.out.println(sb);
    }

    static void bfs() {
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(0);

        boolean[] visit = new boolean[n + 2];
        visit[0] = true;

        while (!qu.isEmpty()) {
            Integer now = qu.poll();

            if (now == n + 1) {
                sb.append("happy").append("\n");
                return;
            }
            for (int num : graph.get(now)) {
                if (!visit[num]) {
                    visit[num] = true;
                    qu.offer(num);
                }
            }
        }
        sb.append("sad").append("\n");
    }
}
