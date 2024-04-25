import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static Map<String, List<String>> family = new HashMap<>();
    static Map<String, Double> blood = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String king = br.readLine();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String child = st.nextToken();
            family.computeIfAbsent(child, k -> new ArrayList<>());

            String father = st.nextToken();
            String mother = st.nextToken();

            family.get(child).add(father);
            family.get(child).add(mother);

            blood.put(child, -1d);
            blood.put(father, -1d);
            blood.put(mother, -1d);
        }

        blood.put(king, 1d);

        for (String s : blood.keySet()) {
            dfs(s);
        }

        double max = 0;
        String candidate = "";
        for (int i = 0; i < m; i++) {
            String temp = br.readLine();

            if (blood.getOrDefault(temp, 0d) > max) {
                max = blood.get(temp);
                candidate = temp;
            }
        }

        System.out.print(candidate);

    }

    static double dfs(String person) {

        if (blood.get(person) != -1) {
            return blood.get(person);
        }

        if (family.get(person) == null) {
            blood.put(person, 0d);
            return 0;
        }

        String father = family.get(person).get(0);
        String mother = family.get(person).get(1);

        double fa = dfs(father);
        double mo = dfs(mother);

        blood.put(person, (fa + mo) / 2);

        return blood.get(person);
    }
}
