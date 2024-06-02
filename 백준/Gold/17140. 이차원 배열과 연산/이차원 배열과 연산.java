import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static class Point implements Comparable<Point> {
        int num, count;

        public Point(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Point o) {
            if (this.count == o.count) {
                return this.num - o.num;
            }
            return this.count - o.count;
        }
    }

    static int[][] A = new int[101][101];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (time <= 100) {
            if (A[r][c] == k) {
                System.out.println(time);
                return;
            }

            if (countRow() >= countCol()) {
                ROper();
            } else {
                COper();
            }

            time++;
        }

        System.out.println(-1);
    }

    private static void COper() {
        for (int i = 1; i <= 100; i++) {
            ArrayList<Point> list = new ArrayList<>();
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int j = 1; j <= 100; j++) {
                if (A[j][i] != 0) {
                    map.put(A[j][i], map.getOrDefault(A[j][i], 0) + 1);
                }
            }

            for (int num : map.keySet()) {
                list.add(new Point(num, map.get(num)));
            }

            Collections.sort(list);
            int index = 1;
            for (Point point : list) {
                if (index > 100) {
                    break;
                }
                A[index++][i] = point.num;
                if (index > 100) {
                    break;
                }
                A[index++][i] = point.count;
            }
            while (index <= 100) {
                A[index++][i] = 0;
            }
        }
    }

    private static void ROper() {
        for (int i = 1; i <= 100; i++) {
            ArrayList<Point> list = new ArrayList<>();
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int j = 1; j <= 100; j++) {
                if (A[i][j] != 0) {
                    map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
                }
            }

            for (int num : map.keySet()) {
                list.add(new Point(num, map.get(num)));
            }

            Collections.sort(list);
            int index = 1;
            for (Point point : list) {
                if (index > 100) {
                    break;
                }
                A[i][index++] = point.num;
                if (index > 100) {
                    break;
                }
                A[i][index++] = point.count;
            }
            while (index <= 100) {
                A[i][index++] = 0;
            }
        }
    }

    private static int countCol() {
        int count = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (A[j][i] != 0) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    private static int countRow() {
        int count = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (A[i][j] != 0) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }
}
