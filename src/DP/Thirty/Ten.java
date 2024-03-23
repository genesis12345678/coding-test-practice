package DP.Thirty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * DP보단 위상 정렬과 BFS로 해결해야 할 문제다.
 * 위상 정렬은 방향 그래프에서 노드 순서를 찾을 수 있으므로
 * 노드 순서대로 따라가면서 각 노드에는 소요되는 시간의 최댓값을 저장하면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1005">백준 1005번 - DP : ACM Craft</a>
 * @since 2024-03-23
 */
public class Ten {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); //테스트 케이스 개수

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken()); //건물 개수(=노드 개수)
            int k = Integer.parseInt(st.nextToken()); //규칙 개수(=간선 개수)

            st = new StringTokenizer(br.readLine());

            int[] time = new int[n + 1]; //각 건물을 짓는데 걸리는 시간
            for (int i = 1; i <= n; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            ArrayList<Integer>[] A = new ArrayList[n + 1]; //그래프 인접 리스트
            for (int i = 0; i < n + 1; i++) {
                A[i] = new ArrayList<>();
            }

            int[] inDegree = new int[n + 1]; //위상 정렬을 위한 진입 차수 배열
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken()); //출발
                int y = Integer.parseInt(st.nextToken()); //도착

                A[x].add(y); //단방향 연결
                inDegree[y]++; //도착 노드 진입 차수 증가
            }

            int w = Integer.parseInt(br.readLine()); //최종 도착 노드

            Queue<Integer> qu = new LinkedList<>();

            for (int i = 1; i <= n; i++) {
                if (inDegree[i] == 0) { //진입 차수가 0인 부분(시작 노드) 큐 삽입
                    qu.offer(i);
                }
            }

            int[] total = new int[n + 1]; //각 건물까지 짓는데 총 걸리는 시간

            while (!qu.isEmpty()) {
                int now = qu.poll();

                if (now == w) { //최종 도착 노드에 도착했다면 반복 종료
                    break;
                }

                for (int next : A[now]) {
                    inDegree[next]--; //진입 차수 1 감소
                    if (inDegree[next] == 0) { //진입 차수가 0이면 다음 탐색 대상
                        qu.offer(next);
                    }
                    // 건물 X를 지은 다음에 Y를 지을 수 있으므로 항상 큰 값이 저장되어야 한다.
                    if (total[now] + time[now] > total[next]) {
                        total[next] = total[now] + time[now];
                    }
                }
            }

            //최종 도착 노드에서 걸리는 총 시간과 그 건물을 하나 짓는데 걸리는 시간을 더해야 한다.
            sb.append(total[w] + time[w]).append("\n");
        }

        System.out.println(sb);
    }
}
