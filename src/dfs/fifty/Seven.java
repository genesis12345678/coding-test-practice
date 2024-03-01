package dfs.fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 각 칸에서 갈 수 있는 거리를 구하고 방문할 때마다 count를 더해준다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/14248">백준 14248번 : 깊이우선탐색 - 점프 점프</a>
 * @since 2024-03-01
 */
public class Seven {

    static int[] stones;
    static boolean[] visit;
    static int count = 0, n;
    static int[] move = {-1, 1};//좌, 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        stones = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        //인덱스별 점프할 수 있는 거리 입력
        for (int i = 0; i < n; i++) {
            int dist = Integer.parseInt(st.nextToken());
            stones[i] = dist;
        }

        visit = new boolean[n];

        int start = Integer.parseInt(br.readLine());
        dfs(start - 1);

        System.out.println(count);
    }

    private static void dfs(int num) {
        if (visit[num]) {//방문한 곳이면 return
            return;
        }
        count++;//방문할 때마다 늘려준다.
        visit[num] = true;//방문 처리

        int dist = stones[num];//다음 이동할 거리

        for (int mv : move) {//move : 좌우 배열
            int next = mv * dist + num;//다음 점프할 거리 계산
            if (next >= 0 && next < n) {//범위 내 일때만 탐색
                dfs(next);
            }
        }
    }
}
