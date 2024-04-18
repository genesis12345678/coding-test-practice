package bellmanFord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 얼핏 보면 BFS 문제 같지만, 계속해서 과거로 이동하는 경우가 존재할 수도 있다는 조건이 있어 벨만포드 알고리즘을 사용해야 한다는 것을 알 수 있다.
 * 일단 표 형식의 map을 그래프 형태로 저장해야 한다.
 * 각 잔디는 자유롭게 이동할 수 있기 때문에 양방향에 가중치 1로 저장할 수 있다.
 * 귀신 구멍은 단방향으로 가중치 t만큼 저장할 수 있다. 묘비는 무시해도 된다.
 *
 * 에지 리스트를 추가할 때 주의점
 *  - 잔디는 동서남북으로 양방향 연결할 수 있다.
 *  - 귀신 구멍인 칸은 동서남북이 안 되고, 반드시 귀신 구멍의 도착지로만 연결되기 때문에 에지 리스트에 추가하면 안 된다.
 *  - 출구에 도착하면 바로 탈출이므로 출구 위치에서는 인접한 칸을 에지 리스트에 추가할 필요 없다.
 *
 * 벨만포드 알고리즘의 시간 복잡도는 O(VE)이다. 이 문제에서 V는 W * H - G 이다. (전체 노드에서 묘비 개수를 뺀 값)
 * W와 H의 최댓값이 작으므로 부담없이 벨만포드 알고리즘으로 해결할 수 있다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/3860">백준 3860번 - 벨만포드 : 할로윈 묘지</a>
 * @since 2024-04-15
 */
public class Three {


    static List<Edge> edges; //에지 리스트
    static int[][] map;
    static long[][] dist;
    static int w, h, g, e;
    static StringBuilder sb = new StringBuilder();

    static final int MYO = 1; //묘지
    static final int HOLE = 2; //귀신 구멍
    static final int EXIT = 3; //출구
    static final String NEVER = "Never";
    static final String IMPOSSIBLE = "Impossible";


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            edges = new ArrayList<>();
            map = new int[h][w];
            dist = new long[h][w];

            g = Integer.parseInt(br.readLine());

            //묘비 위치 저장
            for (int i = 0; i < g; i++) {
                st = new StringTokenizer(br.readLine());

                int col = Integer.parseInt(st.nextToken());
                int row = Integer.parseInt(st.nextToken());

                map[row][col] = MYO;
            }

            e = Integer.parseInt(br.readLine());

            //귀신 구멍 위치 저장
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());

                //start
                int col1 = Integer.parseInt(st.nextToken());
                int row1 = Integer.parseInt(st.nextToken());

                //end
                int col2 = Integer.parseInt(st.nextToken());
                int row2 = Integer.parseInt(st.nextToken());

                //시간
                int T = Integer.parseInt(st.nextToken());

                map[row1][col1] = HOLE;

                Point start = new Point(row1, col1);
                Point end = new Point(row2, col2);

                edges.add(new Edge(start, end, T));
            }

            map[h - 1][w - 1] = EXIT;

            addEdges(); //모든 잔디들에 대해 에지 리스트 추가

            //벨만포드 수행 후 결과 받아서 출력
            sb.append(bellmanFord()).append("\n");
        }

        System.out.println(sb);
    }

    private static String bellmanFord() {
        long max = Long.MAX_VALUE;

        for (int i = 0; i < h; i++) {
            Arrays.fill(dist[i], max);
        }

        dist[0][0] = 0; //시작

        int n = h * w - g; //벨만포드는 노드 개수 - 1번에 한번 더 반복을 해야 한다. 그래서 일단 노드 개수를 구해준다.

        //n-1번 벨만포드 수행하면서 최단거리 업데이트
        for (int i = 0; i < n - 1; i++) {
            for (Edge edge : edges) {
                Point start = edge.start;
                Point end = edge.end;

                if (dist[start.row][start.col] != max) {
                    if (dist[end.row][end.col] > dist[start.row][start.col] + edge.time) {
                        dist[end.row][end.col] = dist[start.row][start.col] + edge.time;
                    }
                }
            }
        }

        //음의 사이클을 확인하기 위해 한번 더 수행
        for (Edge edge : edges) {
            Point start = edge.start;
            Point end = edge.end;

            if (dist[start.row][start.col] != max) {
                if (dist[end.row][end.col] > dist[start.row][start.col] + edge.time) {
                    return NEVER; //최단거리가 바뀌면 음의 사이클 발생이므로 Never
                }
            }
        }

        if (dist[h - 1][w - 1] == max) { //음의 사이클은 아니지만, 출구를 못 찾은 경우
            return IMPOSSIBLE;
        } else {
            return String.valueOf(dist[h - 1][w - 1]); //출구까지 최단 거리로 탈출한 경우
        }
    }

    static void addEdges() {

        //인접한 상하좌우
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int row = 0; row < h; row++) { //row
            for (int col = 0; col < w; col++) { //col

                //귀신 구멍은 입력 받을 때 추가했고,
                //출구랑 묘비 자리는 추가하면 안 되므로
                //현재 위치가 일반 잔디일 때만 인접한 칸을 탐색한다.
                if (map[row][col] == 0) {

                    for (int k = 0; k < 4; k++) {
                        int next_row = row + dr[k];
                        int next_col = col + dc[k];

                        //인접한 칸이 범위 내면서, 묘비가 아닐 때만 연결한다.
                        if (next_row >= 0 && next_row < h && next_col >= 0 && next_col < w && map[next_row][next_col] != MYO) {
                            Point start = new Point(row, col);
                            Point end = new Point(next_row, next_col);

                            edges.add(new Edge(start, end, 1));
                        }
                    }
                }
            }
        }
    }

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.col = col;
            this.row = row;
        }
    }

    static class Edge {
        Point start, end;
        int time;

        public Edge(Point start, Point end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }
}

