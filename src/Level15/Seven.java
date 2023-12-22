package Level15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Seven {
    public static boolean[] prime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        prime = new boolean[N + 1];
        getPrime();

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = Math.max(2, M); i <= N; i++) {
            if (!prime[i]) {
                list.add(i);
            }
        }
        for (int i : list) {
            sb.append(i).append('\n');
        }
        System.out.println(sb);
    }

    public static void getPrime() {
        for (int i = 2; i <= Math.abs(Math.sqrt(prime.length)) ; i++) {
            if(prime[i]) continue;
            for (int j = i * i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }
    }
}
