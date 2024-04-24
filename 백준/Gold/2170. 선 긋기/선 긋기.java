import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Line[] lines = new Line[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            lines[i] = new Line(x, y);
        }

        Arrays.sort(lines);

        int s = lines[0].x;
        int e = lines[0].y;

        int length = e - s;

        for (int i = 1; i < n; i++) {
            if (s <= lines[i].x && e >= lines[i].y) {
                continue;
            }

            if (lines[i].x < e) {
                length += lines[i].y - e;
            } else {
                length += lines[i].y - lines[i].x;
            }

            s = lines[i].x;
            e = lines[i].y;
        }

        System.out.print(length);
    }

    static class Line implements Comparable<Line> {
        int x, y;

        public Line(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Line o) {
            if (this.x == o.x) {
                return this.y - o.y;
            }

            return this.x - o.x;
        }
    }
}
