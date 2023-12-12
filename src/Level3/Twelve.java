package Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Twelve {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        String str;

            while ((str = br.readLine()) != null) {
                try {
                    st = new StringTokenizer(str, " ");
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    sb.append(a + b).append("\n");
                } catch (NoSuchElementException e) {
                    break;
                }
            }
            System.out.print(sb);
    }
}
