package dfs.fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 단방향으로 a->b, b->a 방향의 두 인접 리스트를 만든다.
 * 가장 높은 등수를 구할때는 b->a, 역방향의 인접 리스트로 구한다.
 * 그래야 타겟(x)보다 높은 등수를 가지고 있는 학생이 몇 명인지 알 수 있다.
 * 가장 낮은 등수를 구할때는 a->b, 정방향의 인접 리스트로 구한다.
 * 그래야 타겟(x)보다 낮은 등수를 가지고 있는 학생이 몇 명인지 알 수 있다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/17616">백준 17616번 : 깊이우선탐색 - 등수 찾기</a>
 * @since 2024-03-02
 */
public class Eight {
    static List<List<Integer>> forward = new ArrayList<>();//정방향 인접 리스트
    static List<List<Integer>> backward = new ArrayList<>();//역방향 인접 리스트
    static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            forward.add(new ArrayList<>());
            backward.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            forward.get(a).add(b);//정방향 연결
            backward.get(b).add(a);//역방향 연결
        }

        StringBuilder sb = new StringBuilder();

        //최대 등수와 최소 등수를 구할 때마다 새로 생성하는 것보다는 2차원으로 선언해서 [][0]은 최대 등수, [][1]은 최소 등수를 구할 때 사용한다.
        visit = new boolean[n + 1][2];

        //가장 높은 등수(getBest())는 내 앞에 몇 명 있는지를 계산하고 그대로 출력
        //가장 낮은 등수(getWorst())는 내 뒤에 몇 명 있는지를 계산하고 전체 인원에서 빼준다. 그리고 자신의 등수를 구하기 위해 1을 더해준다.
        sb.append(getBest(x)).append(" ").append(n + 1 - (getWorst(x)));
        System.out.println(sb);
    }

    static int getBest(int num) {
        visit[num][0] = true;

        int count = 1;
        for (int next : backward.get(num)) {
            if (!visit[next][0]) {
                count += getBest(next);
            }
        }
        return count;
    }

    static int getWorst(int num) {
        visit[num][1] = true;

        int count = 1;
        for (int next : forward.get(num)) {
            if (!visit[next][1]) {
                count += getWorst(next);
            }
        }
        return count;
    }
}

