package bfs.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * map 밑에서부터 탐색하면서 연쇄가 일어날 수 있으면 연쇄 후 뿌요들을 아래로 떨어뜨린다.
 * 연속으로 일어나는 연쇄 수를 구해야 하니 중간에 연쇄가 끊기면 반복을 중단해야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/11559">11559번 : 너비우선탐색 - Puyo Puyo</a>
 * @since 2024-02-06
 */
public class Nine {

    static char[][] map;
    static boolean[][] visit;
    static List<int[]> list = new ArrayList<>();

    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[12][6];

        //입력
        for (int i = 0; i < 12; i++) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        while (true) {
            boolean flag = false;//연쇄가 가능한지 체크

            for (int i = 11; i >= 0; i--) {
                for (int j = 5; j >= 0 ; j--) {
                    if (map[i][j] != '.') {//target color
                        char c = map[i][j];

                        if (bfs(i, j, c)) {//bfs()는 연쇄가 될 수 있는지(4개 이상 연결되어 있는지) 반환한다.
                            flag = true;
                            for (int[] n : list) {
                                map[n[0]][n[1]] = '.';
                            }
                        }
                    }
                }
            }
            if (!flag) {//연쇄가 끊기면 반복 중단
                break;
            } else {
                count++;
                moveDown();//아래로 이동
            }
        }

        System.out.println(count);
    }

    static boolean bfs(int a, int b, char color) {
        //큐, 방문 배열, 연쇄될 위치 저장 리스트 생성
        Queue<int[]> qu = new LinkedList<>();
        visit = new boolean[12][6];
        list = new ArrayList<>();

        //큐, 방문 배열, 리스트 초기값
        qu.offer(new int[]{a, b});
        visit[a][b] = true;
        list.add(new int[]{a, b});

        //bfs 탐색으로 같은 색깔을 찾는다.
        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + mx[i];
                int ny = y + my[i];

                if (nx >= 0 && ny >= 0 && nx < 12 && ny < 6) {
                    if (!visit[nx][ny] && map[nx][ny] == color) {
                        visit[nx][ny] = true;
                        list.add(new int[]{nx, ny});
                        qu.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        return list.size() >= 4; //4개 이상이어야 한다.
    }

    static void moveDown() {
        Queue<Character> qu = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 11; j >= 0; j--) {
                //일단 한 행씩 탐색하면서 모든 칸을 빈공간으로 만들고
                //색깔이 있는 칸은 큐에 보관해둔다.
                if(map[j][i] != '.') {
                    qu.offer(map[j][i]);
                }
                map[j][i] = '.';
            }
            //밑에서부터 큐에서 꺼낸 값으로 대체한다.
            int h = 11;
            while (!qu.isEmpty()) {
                map[h--][i] = qu.poll();
            }
        }
    }
}
