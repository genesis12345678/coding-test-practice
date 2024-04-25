import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int max = Integer.MIN_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTrack(0);
        System.out.print(max);
    }

    static void backTrack(int depth) {
        if (depth == 5) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    max = Math.max(max, map[i][j]);
                }
            }
            return;
        }

        int[][] copyMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, copyMap[i], 0, n);
        }

        for (int i = 0; i < 4; i++) {
            move(i);
            backTrack(depth + 1);

            for (int j = 0; j < n; j++) {
                System.arraycopy(copyMap[j], 0, map[j], 0, n);
            }
        }
    }

    static void move(int d) {
        switch (d) {
            case 0: //위로
                for (int i = 0; i < n; i++) {
                    int idx = 0;
                    int block = 0;
                    for (int j = 0; j < n; j++) {
                        if (map[j][i] != 0) {
                            if (block == map[j][i]) {
                                map[idx - 1][i] = block * 2;
                                block = 0;
                                map[j][i] = 0;
                            } else {
                                block = map[j][i];
                                map[j][i] = 0;
                                map[idx][i] = block;
                                idx++;
                            }
                        }
                    }
                }
                break;
            case 1: //아래로
                for (int i = 0; i < n; i++) {
                    int idx = n - 1;
                    int block = 0;
                    for (int j = n - 1; j >= 0; j--) {
                        if (map[j][i] != 0) {
                            if (block == map[j][i]) {
                                map[idx + 1][i] = block * 2;
                                block = 0;
                                map[j][i] = 0;
                            } else {
                                block = map[j][i];
                                map[j][i] = 0;
                                map[idx][i] = block;
                                idx--;
                            }
                        }
                    }
                }
                break;
            case 2: //왼쪽으로
                for (int i = 0; i < n; i++) {
                    int idx = 0;
                    int block = 0;
                    for (int j = 0; j < n; j++) {
                        if (map[i][j] != 0) {
                            if (block == map[i][j]) {
                                map[i][idx - 1] = block * 2;
                                block = 0;
                                map[i][j] = 0;
                            } else {
                                block = map[i][j];
                                map[i][j] = 0;
                                map[i][idx] = block;
                                idx++;
                            }
                        }
                    }
                }
                break;
            case 3: //오른쪽으로
                for (int i = 0; i < n; i++) {
                    int idx = n - 1;
                    int block = 0;
                    for (int j = n - 1; j >= 0; j--) {
                        if (map[i][j] != 0) {
                            if (block == map[i][j]) {
                                map[i][idx + 1] = block * 2;
                                block = 0;
                                map[i][j] = 0;
                            } else {
                                block = map[i][j];
                                map[i][j] = 0;
                                map[i][idx] = block;
                                idx--;
                            }
                        }
                    }
                }
                break;
        }
    }

}
