package bfs.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 콤비네이션으로 궁수가 배치될 수 있는 각 경우의 수마다 적을 처리할 수 있는 최대 개수를 구하면서 max를 갱신한다.
 * 범위가 매우 적으니 브루트포스로 할 수 있다.
 * TODO: 뭘 놓쳤는지 계속 틀린다. 아직 내 힘으로 푼건 아니니 계속 해봐야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/17135">백준 17135번 : 너비우선탐색 - 캐슬 디펜스</a>
 * @since 2024-02-04
 */
public class Three {
    static int N, M, D;
    static int[][] map;
    static int max = Integer.MIN_VALUE;
    static List<int[]> monsters = new ArrayList<>();
    static int[] archers = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    monsters.add(new int[]{i, j});
                }
            }
        }

        for (int i = 0; i < M - 2; i++) {
            for (int j = i + 1; j < M - 1; j++) {
                for (int k = j + 1; k < M; k++) {
                    archers[0] = i;
                    archers[1] = j;
                    archers[2] = k;
                    attack();
                }
            }
        }
        System.out.println(max);
    }


    static void attack() {
        int count = 0;

        List<int[]> copyMonsters = getList();

        while (!copyMonsters.isEmpty()) {

            Queue<int[]> qu = new PriorityQueue<>((o1, o2) -> {
                if (o1[2] == o2[2]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[2] - o2[2];
                }
            });

            Set<int[]> target = new HashSet<>();

            for (int i = 0; i < 3; i++) {
//                int[] archer = archers.get(i);
//                int ax = archer[0];
//                int ay = archer[1];

                for (int[] now : copyMonsters) {
                    int diff = Math.abs(now[0] - N) + Math.abs(now[1] - archers[i]);
                    if (diff <= D) {
                        qu.offer(new int[]{now[0], now[1], diff});
                    }
                }

                if (!qu.isEmpty()) {
                    int[] now = qu.poll();
                    int x = now[0];
                    int y = now[1];
                    target.add(new int[]{x, y});
                }
            }

            if (!target.isEmpty()) {

                for (int[] atk : target) {
                    for (int j = 0; j < copyMonsters.size(); j++) {

                        if (Arrays.equals(atk, copyMonsters.get(j))) {
                            copyMonsters.remove(j--);
                            count++;
                        }
                    }
                }
            }

            for (int i = 0; i < copyMonsters.size(); i++) {
                copyMonsters.get(i)[0] += 1;
                if (copyMonsters.get(i)[0] == N) {
                    copyMonsters.remove(i--);
                }
            }
        }

        max = Math.max(max, count);
    }

    private static List<int[]> getList() {
        List<int[]> copy = new ArrayList<>();
        for (int[] now : monsters) {
            copy.add(new int[]{now[0], now[1]});
        }
        return copy;
    }
}
