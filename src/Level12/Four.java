package Level12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Four {
    public static boolean[][] arr;
    public static int min = 64;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j) == 'W';
            }
        }

        int x = N - 7;
        int y = M - 7;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                find(i, j);
            }
        }
        System.out.println(min);
    }

    private static void find(int i, int j) {
        int x = i + 8;
        int y = j + 8;
        int count = 0;

        boolean start = arr[i][j];

        for (int k = i; k < x; k++) {
            for (int l = j; l < y; l++) {
                if (arr[k][l] != start) {
                    count++;
                }
                start = !start;
            }
            start = !start;
        }
        count = Math.min(count, 64 - count);
        min = Math.min(min, count);
    }
}

