package Level14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Six {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt((st.nextToken())), M = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            if(set.contains(input))
                list.add(input);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        Collections.sort(list);
        for (String s : list) {
            sb.append(s).append("\n");
        }
        System.out.println(sb);
    }
}
