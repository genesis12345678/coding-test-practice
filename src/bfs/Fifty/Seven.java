package bfs.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 구역을 나누고 나뉜 구역이 끊기지 않는 한 구역인지 체크한다.
 * 체크 후 통과된다면 인구 차이를 구해서 최솟값을 갱신해 나간다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/17471">백준 17471번 : 너비우선탐색 - 게리맨더링</a>
 * @since 2024-02-05
 */
public class Seven {
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] population;
    static boolean[] visit;
    static boolean[] temp;
    static int N;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        population = new int[N];
        temp = new boolean[N];

        //인접 리스트
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        //구역별 인구수 입력
        for (int i = 0; i < N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        //인접한 구역의 정보
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());//인접한 구역의 수

            for (int j = 0; j < count; j++) {
                int num = Integer.parseInt(st.nextToken());//인접한 구역의 번호
                graph.get(i).add(num - 1);
            }
        }
        divide(0);//처음부터 시작

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    //백트래킹으로 구역을 나눈다.
    static void divide(int idx) {
        if (idx == N) {//구역 할당이 정해졌다면
            List<Integer> a = new ArrayList<>();//구역 1
            List<Integer> b = new ArrayList<>();//구역 2

            for (int i = 0; i < N; i++) {
                //true는 구역 1
                if (temp[i]) {
                    a.add(i);
                //false는 구역 2
                } else {
                    b.add(i);
                }
            }
            //한 구역이라도 비어 있으면 안 된다.
            if (a.isEmpty() || b.isEmpty()) {
                //다음 경우의 수 탐색
                return;
            }
            //나뉜 두 구역이 완벽한 구역인지 체크한다.
            if (check(a) && check(b)) {
                //완벽한 두 구역으로 나뉜 거라면 인구 차이를 구한다.
                getPeople();
            }
            //다음 경우의 수 탐색
            return;
        }

        temp[idx] = true;
        divide(idx + 1);

        temp[idx] = false;
        divide(idx + 1);
    }

    static boolean check(List<Integer> list) {
        Queue<Integer> qu = new LinkedList<>();
        visit = new boolean[N];

        visit[list.get(0)] = true;
        qu.offer(list.get(0));

        int count = 1;
        while (!qu.isEmpty()) {
            int now = qu.poll();

            for (int num : graph.get(now)) {
                if (list.contains(num) && !visit[num]) {
                    qu.offer(num);
                    visit[num] = true;
                    count++;
                }
            }
        }
        return count == list.size();
    }

    static void getPeople() {
        int a = 0;
        int b = 0;
        for (int i = 0; i < N; i++) {
            if (temp[i]) {
                a += population[i];
            } else {
                b += population[i];
            }
        }
        int result = Math.abs(a - b);
        min = Math.min(min, result);
    }
}
