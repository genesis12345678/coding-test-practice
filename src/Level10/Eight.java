package Level10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Eight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        br.close();
        int max = (a>b)?(Math.max(a, c)):(Math.max(b, c));
        if (a + b + c - max > max) {
            System.out.println(a + b + c);
        }else System.out.println((a + b + c - max) + (a + b + c - max - 1));
    }
}
