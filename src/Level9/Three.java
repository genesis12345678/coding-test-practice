package Level9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == -1) {
                break;
            }
            ArrayList<Integer> list = new ArrayList<>();
            int sum = 0;
            for (int i = 1; i < n ; i++) {
                if (n % i == 0) {
                    list.add(i);
                    sum += i;
                }
            }
            sb.append(n);
            if (n == sum) {
                sb.append(" = ");
                for (int i = 0; i < list.size() - 1; i++) {
                    sb.append(list.get(i)).append(" + ");
                }
                sb.append(list.get(list.size()-1));
            }else
                sb.append(" is NOT perfect.");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
