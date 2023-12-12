package Level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class One {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

//        if (A > B) {
//            System.out.println(">");
//        } else if (A < B) {
//            System.out.println("<");
//        }
//        System.out.println("==");

        System.out.println((A>B) ? ">" : (A<B) ? "<" : "==");
    }
}
