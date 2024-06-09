import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
       ArrayList<Integer>[] graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        int min = Integer.MAX_VALUE;

        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];

            graph[v1].remove(Integer.valueOf(v2));
            graph[v2].remove(Integer.valueOf(v1));

            int count1 = bfs(graph, n, v1);
            int count2 = n - count1;

            min = Math.min(min, Math.abs(count1 - count2));

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        return min;
    }
    
    public int bfs(ArrayList<Integer>[] graph, int n, int start) {
        boolean[] visit = new boolean[n + 1];
        Queue<Integer> qu = new LinkedList<>();

        qu.offer(start);
        visit[start] = true;
        int count = 0;

        while (!qu.isEmpty()) {
            int now = qu.poll();
            count++;

            for (int next : graph[now]) {
                if (!visit[next]) {
                    visit[next] = true;
                    qu.offer(next);
                }
            }
        }

        return count;
    }
}