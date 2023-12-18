package Level10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 0) {
                break;
            }
            int max = Math.max(a, Math.max(b, c));
            int sum;
            if (max == a) {
                sum = b + c;
            }else if (max == b) {
                sum = a + c;
            }else{
                sum = a + b;
            }
            if(sum <= max) {
                System.out.println("Invalid");
            }
            else
            if (a == b && a == c) {
                System.out.println("Equilateral");
            } else
                if(a==b||b==c||a==c) {
                    System.out.println("Isosceles");
                }else{
                    System.out.println("Scalene");
                }
        }
    }
}
