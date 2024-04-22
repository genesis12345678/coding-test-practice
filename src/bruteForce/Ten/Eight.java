package bruteForce.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 아이디어
 * map에 치킨 집(2)이 M개 있는 모든 경우의 수에 대해 치킨 거리를 구하면서 최솟값을 업데이트 한다.
 * 처음에는 집의 모든 위치에 BFS를 통해 최소거리를 구했는데, 역시 범위가 적음에도 시간 초과가 발생했다.
 * 그래서 더 효율적으로 재귀호출과 거리를 구하기 위해 방문 체크 배열과 리스트를 사용했다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/15686">백준 15686번 - 브루트포스 : 치킨 배달</a>
 * @since 2024-04-22
 */
public class Eight {

    static int[][] map;
    static int n, m;
    static ArrayList<House> houses = new ArrayList<>(); //집(1) 위치
    static ArrayList<Chicken> chickens = new ArrayList<>(); //치킨집(2) 위치
    static boolean[] visit = new boolean[13]; //최대 치킨집 개수 = 13
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) { //집 위치 저장
                    houses.add(new House(i, j));
                } else if (map[i][j] == 2) { //치킨집 위치 저장
                    chickens.add(new Chicken(i, j));
                }
            }
        }

        select(0, 0);

        System.out.print(min);
    }

    static void select(int index, int count) {
        if (count == m) { //m개 고르면 최솟값 업데이트
            min = Math.min(min, calculate());
            return;
        }

        if (index == chickens.size()) { //마지막까지 온 거면 리턴
            return;
        }

        visit[index] = true;
        select(index + 1, count + 1);

        visit[index] = false;
        select(index + 1, count);
    }

    static int calculate() {
        int sum = 0;

        for (House house : houses) {
            int temp = Integer.MAX_VALUE;
            int hx = house.x;
            int hy = house.y;

            for (int i = 0; i < chickens.size(); i++) {
                if (visit[i]) {
                    Chicken chicken = chickens.get(i);

                    int cx = chicken.x;
                    int cy = chicken.y;

                    int dist = Math.abs(hx - cx) + Math.abs(hy - cy);
                    temp = Math.min(temp, dist);
                }
            }

            sum += temp;
        }

        return sum;
    }

    static class House{
        int x, y;

        public House(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Chicken{
        int x, y;

        public Chicken(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
