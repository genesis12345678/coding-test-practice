package dfs.fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 자식노드부터 탐색하는 후위 탐색으로 밑에서부터 올라오면서 계산하도록 한다.
 * 이때 최대 (1억 * 약 12만) 값이 나올 수 있으므로 long형을 사용한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/16437">백준 16437번 : 깊이우선탐색 - 양 구출 작전</a>
 * @since 2024-02-28
 */
public class Three {

    /**
     * 노드 정보 클래스
     */
    static class Node {
        char ch;//양인지 늑대인지
        int capacity;//마리 수

        public Node(char ch, int capacity) {
            this.ch = ch;
            this.capacity = capacity;
        }
    }
    static List<List<Integer>> tree = new ArrayList<>();
    static Node[] nodes;//노드 정보를 갖는 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        nodes = new Node[N + 1];
        nodes[1] = new Node('S', 0);//루트 노드 생성(마리수를 0으로 하여 영향이 없게끔)

        for (int i = 2 ;i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char t = st.nextToken().charAt(0);//양인지 늑대인지
            int a = Integer.parseInt(st.nextToken());//마리 수
            int p = Integer.parseInt(st.nextToken());//다음 섬 번호

            tree.get(p).add(i);//단방향으로 부모 노드만 바라보게
            nodes[i] = new Node(t, a);
        }

        System.out.println(dfs(1));
    }

    private static long dfs(int now) {
        long sum = 0; //long 주의
        for (int next : tree.get(now)) {
            sum += dfs(next);//밑에서부터 위로 올라오면서 계산
        }

        if (nodes[now].ch == 'S') {//현재 위치가 양이면
            return sum + nodes[now].capacity;//양 마리수를 그대로 더한다.
        } else {//현재 위치가 늑대면
            long result = sum - nodes[now].capacity;//밑에서부터 올라온 양의 마리수와 늑대 마리수를 계산해서
            if (result < 0) {//늑대 마리수가 더 많다면
                return 0;//남아있는 양이 없으므로 0 반환
            } else {//양이 더 많다면
                return result;//남아있는 양 마리수 반환
            }
        }
    }
}
