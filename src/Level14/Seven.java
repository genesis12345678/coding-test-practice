package Level14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        Set<String> set1 = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) {
            set1.add(st.nextToken());
        }

        Set<String> set2 = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < B; i++) {
            set2.add(st.nextToken());
        }
        Set<String> set2Copy = new HashSet<>(set2);

        for (String s : set1) {
            if(set2.contains(s))
                set2.remove(s);
        }

        for (String s : set2Copy) {
            if(set1.contains(s))
                set1.remove(s);
        }
        System.out.println(set1.size() + set2.size());
    }
}
