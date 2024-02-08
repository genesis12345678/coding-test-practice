package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * boolean 배열을 두 개 만들어 방문 배열, 팀 완성 배열을 각각 관리한다.
 * 1번부터 팀 완성이 되지 않은 번호만 dfs 탐색을 통해 count를 계산한 다음 총 개수(n)에서 빼준다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/9466">백준 9466번 : 깊이우선탐색 - 텀 프로젝트</a>
 * @since 2024-02-09
 */
public class Five {

    static int[] parent;
    static boolean[] visit, done;
    static int n;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            count = 0;
            visit = new boolean[n + 1];
            done = new boolean[n + 1];
            parent = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parent[i] = Integer.parseInt(st.nextToken());

                //자기 자신을 고른 경우, 바로 팀 완성이다.
                if (parent[i] == i) {
                    done[i] = true;
                    count++;
                }
            }
            solution();
            sb.append(n - count).append("\n");

        }
        System.out.println(sb);
    }

    static void solution() {
        //1번부터 팀이 되지 않은 번호만 탐색
        for (int i = 1; i <= n; i++) {
            if (!done[i]) {
                dfs(i);
            }
        }
    }

    static void dfs(int num) {
        //돌고 돌아서 방문한 번호에 도착하면 팀이 될 수 있다.
        if (visit[num]) {
            done[num] = true;
            count++;
        } else {
            visit[num] = true;
        }

        //자신이 찍은 번호가 팀이 되지 않았을 때만 탐색한다.
        if (!done[parent[num]]) {
            dfs(parent[num]);
        }
        //다음 탐색에 영향을 주지 않기 위해 거쳐온 경로들을 초기화 해준다.
        visit[num] = false;
        done[num] = true;
    }
}
