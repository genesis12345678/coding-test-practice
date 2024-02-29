package dfs.fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 일반적인 dfs 말고 동적 프로그래밍을 같이 사용해서 해결한다.
 * 탐색 중 이동 가능하거나 불가능한 경로를 만나면 더 이상 탐색하지 않고 종료한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/17090">백준 17090번 : 깊이우선탐색 - 미로 탈출하기</a>
 * @since 2024-02-29
 */
public class Four {
    static int N, M;
    static char[][] map;
    static int[][] dp;//동적 프로그래밍 배열, 0=아직 탐색하지 않은 경로, 1=탈출 불가능 경로, 2=탈출 가능 경로

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dp[i][j] == 0) {//아직 탐색하지 않은 칸만 탐색을 해본다.
                    if (dfs(i, j)) {//현재 칸에서 탈출 가능하면 결과를 늘린다.
                        count++;
                    }
                } else if (dp[i][j] == 2) {//이미 해당 칸이 탈출 가능 경로에 포함되어 있으면 탐색하지 않고 결과만 늘려준다.
                    count++;
                }//dp[i][j]==1은 볼 것도 없이 실패니까 생략
            }
        }
        System.out.println(count);

    }

    private static boolean dfs(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M) {//탈출 가능하면 true 반환
            return true;
        }

        if (dp[x][y] == 1) {//탈출 불가능한 경로이거나, 같은 자리를 반복하는 것일 수 있다.
            return false;
        }

        if (dp[x][y] == 2) {//탈출 가능한 경로를 찾으면 종료
            return true;
        }

        dp[x][y] = 1;//이동한 경로 표시

        int[] next = getNext(map[x][y]);//다음 위치정보 get
        int nx = x + next[0];
        int ny = y + next[1];

        if (dfs(nx, ny)) {//탈출 가능하면
            dp[x][y] = 2;//이동했던 경로에 탈출 가능을 표시한다.
            return true;
        } else {//탈출 불가능하면
            dp[x][y] = 1;//이동했던 경로에 탈출 불가능을 표시한다.
            return false;
        }
    }

    private static int[] getNext(char c) {
        switch (c) {
            case 'U':
                return new int[]{-1, 0};
            case 'D':
                return new int[]{1, 0};
            case 'L':
                return new int[]{0, -1};
            default:
                return new int[]{0, 1};
        }
    }
}
