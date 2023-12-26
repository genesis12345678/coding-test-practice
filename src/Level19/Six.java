package Level19;

import java.io.*;
import java.util.Arrays;

public class Six {
    static String[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        arr = new String[N][N];

        star(0, 0, N, false);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(arr[i][j]);
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    /**
     *
     * @param x x 좌표
     * @param y y 좌표
     * @param N 한 변의 길이
     * @param blank true라면 공백칸
     */
    static void star(int x, int y, int N, boolean blank) {
        if (blank) {
            for (int i = x; i < x + N; i++) {
                for (int j = y; j < y + N; j++) {
                    arr[i][j] = " ";
                }
            }
            return;
        }

        if (N == 1) {
            arr[x][y] = "*";
            return;
        }

        /**
         * N = 27이면 size = 9
         * N = 9면 size = 3
         * N = 3이면 size = 1
         */
        int size = N / 3;
        int count = 0;
        for (int i = x; i < x + N; i+= size) {
            for (int j = y; j < y + N; j+= size) {
                count++;
                if (count == 5) {
                    star(i, j, size, true);
                }else
                    star(i, j, size, false);
            }
        }
    }
}
