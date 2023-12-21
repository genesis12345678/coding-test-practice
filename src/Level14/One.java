package Level14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        Set<String> set = new HashSet<>(Arrays.asList(br.readLine().split(" ")));
        StringBuilder sb = new StringBuilder();
        br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        while(st.hasMoreTokens())
            sb.append(set.contains(st.nextToken()) ? 1 : 0).append(" ");

        System.out.println(sb);
    }
}
