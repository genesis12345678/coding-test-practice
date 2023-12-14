package Level6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(br.readLine());
        br.close();

        if ((sb.toString()).contentEquals(sb.reverse())) {
            System.out.println(1);
        }
        else System.out.println(0);


    }
}
