package Level8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Six {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int X=Integer.parseInt(br.readLine());
        int n = 0; // n번째 대각선
        int top = 1;
        int bot = 1;
        int cnt = 0; // 대각선 데이터 개수의 누적 합
        if (X == 1) {
            System.out.println("1/1");
        } else {
            while (cnt < X) {
                n++;
                cnt += n; // 몇번 째 대각선인지 찾는 코드
            }

            int num = X - (n - 1) * n / 2;  // 대각선의 몇번 째 값인지 찾는 코드

            if (n % 2 == 0) {  // 짝수 대각선일 시
                top = num; // 분자 = n번째 대각선 중에 num번째
                bot = n - num + 1; // 분모 = n(번째 대각선) - 위치 + 1
            }
            else { // 홀수 대각선일 시
                top = n - num + 1;
                bot = num;
            }
            System.out.println(top + "/" + bot);
        }
    }
}
