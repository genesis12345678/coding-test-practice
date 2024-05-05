import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String book = br.readLine();

            map.put(book, map.getOrDefault(book, 0) + 1);
        }

        int max = 0;
        for (Integer value : map.values()) {
            if (max < value) {
                max = value;
            }
        }

        List<String> bestSellers = new ArrayList<>();
        for (String s : map.keySet()) {
            if (map.get(s) == max) {
                bestSellers.add(s);
            }
        }

        Collections.sort(bestSellers);

        System.out.println(bestSellers.get(0));
    }
}
