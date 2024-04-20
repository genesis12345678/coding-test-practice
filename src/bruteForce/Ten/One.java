package bruteForce.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * N의 범위가 적으므로 브루트포스로 모든 경우의 수를 살펴본다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/3085">백준 3085번 - 브루트포스 알고리즘 : 사탕 게임</a>
 * @since 2024-03-19
 */
public class One {

    static char[][] board;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new char[n][n];

        //입력
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < n; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        int result = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + 1 < n) { //범위 체크
                    swap(i, j, i + 1, j); //(i, j)와 (i+1, j) swap
                    result = Math.max(result, check());
                    swap(i, j, i + 1, j); //다음 탐색에 영향이 없게 다시 swap
                }
                if (j + 1 < n) { //범위 체크
                    swap(i, j, i, j + 1); //(i, j)와 (i, j+1) swap
                    result = Math.max(result, check());
                    swap(i, j, i, j + 1); //다음 탐색에 영향이 없게 다시 swap
                }
            }
        }

        System.out.println(result);
    }

    static int check() {
       int count = 1;

        for (int i = 0; i < n; i++) {
            int temp = 1;
            for (int j = 0; j < n - 1; j++) { //마지막 줄은 다음 칸과 비교하려면 범위를 벗어나므로 n-1까지
                if (board[i][j] == board[i][j + 1]) {
                    temp++; //같은 색깔이 나올 때까지 1씩 증가
                } else {
                    temp = 1; //다른 색깔이 나오면 처음으로
                }
                count = Math.max(count, temp);
            }

            temp = 1;
            for (int j = 0; j < n - 1; j++) {
                if (board[j][i] == board[j + 1][i]) { //i, j 위치만 바꿔서
                    temp++;
                } else {
                    temp = 1;
                }
                count = Math.max(count, temp);

            }
        }
        return count;
    }

    static void swap(int fx, int fy, int tx, int ty) {
        char temp = board[fx][fy];
        board[fx][fy] = board[tx][ty];
        board[tx][ty] = temp;
    }
}
