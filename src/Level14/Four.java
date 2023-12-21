package Level14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Four {
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        Map<String, Integer> StringMap = new HashMap<>();
        Map<Integer, String> IntMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            String value = br.readLine();
            StringMap.put(value, i);
            IntMap.put(i, value);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            String value = br.readLine();
            if(value.charAt(0)>=48 && value.charAt(0)<=57)
                sb.append(IntMap.get(Integer.parseInt(value))).append("\n");
            else
                sb.append(StringMap.get(value)).append("\n");
        }
        System.out.println(sb);
    }

}
