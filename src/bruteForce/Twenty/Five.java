package bruteForce.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 상하좌우 한 칸씩 이동이 아니라 끝까지 이동해야 한다.
 * 이동하는 와중에 같은 숫자가 겹치면 더해져야 한다.
 * 5번 각기 다른 방향으로 이동하는 것은 재귀호출을 통해 구현할 수 있다.
 * 중요한 것은 재귀를 거치면서 map의 값이 바뀔텐데, 재귀를 하나씩 탈출하면서 다른 방향으로 탐색할 때 직전 바뀐 map을 그대로 사용할 수 있어야 한다.
 * 그러기 위해서 재귀마다 map을 복사하고, 깊이 5까지 가서 최댓값을 구한 다음 재귀를 빠져나올 때, 복사한 map을 다시 map으로 복구시킨다.
 * 이러면 다음 재귀호출에서 직전에서 변경된 map을 다시 사용할 수 있다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/12100">백준 12100번 - 브루트포스 : 2048(easy)</a>
 * @since 2024-04-25
 */
public class Five {

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
        if (depth == 5) { //5번 이동했으면 가장 큰 블록 탐색
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    max = Math.max(max, map[i][j]);
                }
            }
            return;
        }

        //호출 스택을 빠져나오면서 다음 호출 스택에 이전 호출 스택으로 인해 바뀐 map을 그대로 사용할 수 있게 하기 위해 현재 map 정보 복사
        int[][] copyMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, copyMap[i], 0, n);
            //원본 배열, 원본에서 복사할 시작 인덱스, 복사 데이터를 저장할 배열, 복사 시작 인덱스, 복사할 요소 개수
        }

        for (int i = 0; i < 4; i++) {
            move(i);
            backTrack(depth + 1);

            //깊이 5까지 탐색하고 최댓값을 구한 후 재귀를 빠져나오면서 이전 depth의 map 복구
            for (int j = 0; j < n; j++) {
                System.arraycopy(copyMap[j], 0, map[j], 0, n);
            }
        }
    }

    static void move(int d) {
        /**
         * idx : 값 넣을 위치
         * block : 현재 idx의 위치에 있는 값
         */
        switch (d) {
            case 0 -> { //위로
                for (int i = 0; i < n; i++) {
                    int idx = 0;
                    int block = 0;

                    for (int j = 0; j < n; j++) {
                        if (map[j][i] != 0) { //빈 칸이 아닐 때만

                            if (block == map[j][i]) {       //현재 블록 값과 같다 == 합칠 수 있다.
                                map[idx - 1][i] = block * 2;//이전 값의 2배 저장
                                block = 0;                  //합쳤으니 다시 0으로 초기화
                                map[j][i] = 0;              //옮겼으니 0으로 초기화

                            } else {                        //값이 같지 않아 옮기기만 할 수 있다.
                                block = map[j][i];          //블록 값에는 현재 값 그대로
                                map[j][i] = 0;              //옮겼으니 0으로 초기화
                                map[idx][i] = block;        //옮겨진 block 값 저장
                                idx++;                      //다음 위치할 인덱스 조정
                            }
                        }
                    }
                }
            }
            case 1 -> { //아래로
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
            }
            case 2 -> { //왼쪽으로
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
            }
            case 3 -> { //오른쪽으로
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
            }
        }
    }

}
