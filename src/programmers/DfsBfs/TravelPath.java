package programmers.DfsBfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TravelPath {

    static String[][] tickets;
    static List<String> result = new ArrayList<>();
    static boolean[] visit;
    public static void main(String[] args) {
        tickets = new String[][]{
                {"ICN", "ATL"},
                {"ICN", "SFO"},
                {"SFO", "ATL"},
                {"ATL", "ICN"},
                {"ATL", "SFO"}
        };
        visit = new boolean[tickets.length];

        dfs("ICN", "ICN", 0);

        Collections.sort(result);

        String[] split = result.get(0).split(", ");

        System.out.println("split = " + Arrays.toString(split));
    }

    private static void dfs(String start, String path, int depth) {
        if (depth == tickets.length) {
            result.add(path);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visit[i] && start.equals(tickets[i][0])) {
                visit[i] = true;
                dfs(tickets[i][1], path + ", " + tickets[i][1], depth + 1);
                visit[i] = false;
            }
        }
    }

}
