package bruteForce.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1911">백준 1911번 - 정렬 : 흙길 보수하기</a>
 * @since 2024-05-22
 */
public class Eight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        Water[] waters = new Water[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            waters[i] = new Water(s, e);
        }

        Arrays.sort(waters);

        int now = 0;
        int count = 0;

        for (Water water : waters) {
            if (now < water.s) {
                now = water.s;
            }

            while (now < water.e) {
                now += l;
                count++;
            }
        }

        System.out.println(count);
    }

    static class Water implements Comparable<Water>{
        int s, e;

        public Water(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Water o) {
            return this.s - o.s;
        }
    }
}
