package dfs.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 단순 bfs나 dfs로 주위 0의 개수를 세서 더하면 되는 줄 알았는데 시간초과도 나고 애초에 틀린 방법인 것 같다.
 * 약간 DP와 비슷하게 0이 모여있는 위치를 구분한 뒤 각 벽에서는 상하좌우만 탐색해서 그룹에 속해있는 개수를 더해주면 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/16946">백준 16946번 : 벽 부수고 이동하기 4</a>
 * @since 2024-02-14
 */
public class Six {
    static int N, M;
    static int[][] map, copyMap; //copyMap은 0의 그룹을 표시하는 용도
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static Map<Integer, Integer> group = new HashMap<>();//key: 그룹 번호, value: 0의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        copyMap = new int[N][M];

        //입력
        for (int i = 0; i < N; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = arr[j] - '0';
            }
        }

        //그룹이 정해지지 않은 0을 찾아 그룹을 정해준다.
        int count = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && copyMap[i][j] == 0) {
                    //1씩 늘어나는 그룹 번호(key)와 그 그룹의 0의 개수(value)를 Map에 저장한다.
                    group.put(count, dfsZeroGroup(i, j, count++));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                //map을 탐색하며 벽이면 이동할 수 있는 칸을 계산해서 출력한다.
                if (map[i][j] == 1) {
                    sb.append(solution(i, j));
                } else
                    sb.append(0);
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    static int dfsZeroGroup(int x, int y, int groupCount) {
        int count = 1;

        copyMap[x][y] = groupCount;

        for (int i = 0; i < 4; i++) {
            int nx = x + mx[i];
            int ny = y + my[i];

            if (isRangeValid(nx, ny)) {
                if (copyMap[nx][ny] == 0 && map[nx][ny] == 0) {
                    count += dfsZeroGroup(nx, ny, groupCount);
                }
            }
        }
        return count;//0의 개수를 반환한다.
    }

    static int solution(int x, int y) {

        //인접한 0이 같은 그룹일 수 있으니 Set을 사용한다.
        Set<Integer> result = new HashSet<>();

        //상하좌우 한 번씩만 탐색하면 된다. 그룹 번호만 알 수 있으면 0의 개수를 알 수 있다.
        for (int i = 0; i < 4; i++) {
            int nx = x + mx[i];
            int ny = y + my[i];

            if (isRangeValid(nx, ny)) {
                if (copyMap[nx][ny] != 0) {
                    result.add(copyMap[nx][ny]);//Set에 그룹 번호를 저장한다.
                }
            }
        }
        int count = 1;//자신의 칸을 포함하여 1부터 계산한다.
        for (int num : result) {
            //Set에 저장된 그룹 번호를 가지고 map에서 찾아서 더해준다.
            count += group.get(num);
        }
        return count % 10;
    }

    private static boolean isRangeValid(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < N && ny < M;
    }

}
