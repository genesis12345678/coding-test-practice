package Level10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Five {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int maxX = -10001;
        int minX = 10001;
        int maxY = -10001;
        int minY = 10001;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(x > maxX) maxX = x;
            if(x < minX) minX = x;

            if(y > maxY) maxY = y;
            if(y < minY) minY = y;
        }
        br.close();
        System.out.println((maxX - minX) * (maxY - minY));
    }
}
