import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int n, m;
    static ArrayList<House> houses = new ArrayList<>();
    static ArrayList<Chicken> chickens = new ArrayList<>();
    static boolean[] visit = new boolean[13];
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
                if (map[i][j] == 1) {
                    houses.add(new House(i, j));
                } else if (map[i][j] == 2) {
                    chickens.add(new Chicken(i, j));
                }
            }
        }

        select(0, 0);

        System.out.print(min);
    }

    static void select(int index, int count) {
        if (count == m) {
            min = Math.min(min, calculate());
            return;
        }

        if (index == chickens.size()) {
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
