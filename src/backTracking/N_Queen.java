package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N_Queen {

    public static int[] arr;
    public static int N;
    public static int count = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        nQueen(0);
        System.out.println(count);
    }

    private static void nQueen(int depth) {
        if (depth == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[depth] = i;  // depth번째 행, i번째 열에 queen을 놓아본다.
            // 그 행에 둔 것에 대한 유망성을 판단한다.
            if(possibility(depth)){ // 그 자리에 두어도 괜찮다면,
                nQueen(depth + 1); // 그 다음 행에 대해 queen을 놓는 것을 해 본다.
            }
        }
    }

    // 유망성 판단
    private static boolean possibility(int col) {

        for (int i = 0; i < col; i++) {
            // 열(세로, column)에 일치하는 게 있는지 판별
            if (arr[col] == arr[i]) {

                return false;

            }
            // 대각선에 위치한지 판단
            // (행 번호 차이 == 열 번호 차이) = 대각선상에 있는 것
            else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
                return false;
            }
        }

        return true;
    }
}
