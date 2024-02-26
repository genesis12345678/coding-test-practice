package dfs.fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 시작 번호에서 끝 번호까지 가는 경로의 합에서
 * 가는 경로 중 가장 긴 경로를 빼주면 답이 된다.
 * 최소 경로를 구하는 것이니 bfs가 더 적합하다.
 * 다음 방 번호와 거리를 갖는 Room 클래스를 활용한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/15971">백준 15971번 : 깊이우선탐색 - 두 로봇</a>
 * @since 2024-02-26
 */
public class Eight {

    static class Room {
        int nextRoom; //다음 노드
        int distance; //거리

        public Room(int room, int distance) {
            this.nextRoom = room;
            this.distance = distance;
        }
    }

    static List<List<Room>> tree = new ArrayList<>();
    static boolean[] visit;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int robot1 = Integer.parseInt(st.nextToken());
        int robot2 = Integer.parseInt(st.nextToken());

        visit = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int room1 = Integer.parseInt(st.nextToken());
            int room2 = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            //방1과 방2를 서로 연결
            tree.get(room1).add(new Room(room2, distance));
            tree.get(room2).add(new Room(room1, distance));
        }

        bfs(robot1, robot2);
    }

    static void bfs(int start, int end) {
        Queue<int[]> qu = new LinkedList<>();

        qu.offer(new int[]{start, 0, 0});
        visit[start] = true;

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int nowNode = now[0];//현재 방
            int nowDist = now[1];//누적 거리
            int maxDist = now[2];//경로 중 최댓값

            //목표 방에 도착하면 누적 거리에서 경로 중 최댓값을 뺀 값을 출력하고 종료한다.
            if (nowNode == end) {
                System.out.println(nowDist - maxDist);
                return;
            }

            //주변 방 탐색
            for (Room next : tree.get(nowNode)) {
                int nextRoom = next.nextRoom;//다음 방
                int nextDist = next.distance;//이동할 거리
                if (!visit[nextRoom]) {//탐색 대상이면
                    visit[nextRoom] = true;//탐색 체크
                    //다음 방, 누적 거리 더한 값, 경로 중 최댓값을 갱신
                    qu.offer(new int[]{nextRoom, nowDist + nextDist, Math.max(maxDist, nextDist)});
                }
            }
        }
    }
}
