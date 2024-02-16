package dfs.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 각 노드의 번호가 중위 순회 방식(왼쪽 -> 부모 -> 오른쪽)으로 정해진다.
 * 자신의 번호, 부모 번호, 왼쪽 자식, 오른쪽 자식 번호를 갖는 Node 클래스를 사용한다.
 * 레벨마다 가장 왼쪽에 있는 열과 가장 오른쪽에 있는 열의 번호를 구한다.
 * 가장 차이가 큰 레벨과 차이를 구한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2250">백준 2250번 : 깊이우선탐색 - 트리의 높이와 너비</a>
 * @since 2024-02-16
 */
public class Ten {
    static class Node{
        int num, parent, left, right;
        public Node(int num, int left, int right) {
            this.num = num;
            this.left = left;
            this.right = right;
            this.parent = -1;//부모가 바뀌지 않는 번호가 루트 노드가 된다.
        }
    }
    static Node[] tree;
    static int[] min, max;//min: 가장 왼쪽 열, max: 가장 오른쪽 열
    static int count = 1;//열 번호
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new Node[N + 1];
        min = new int[N + 1];
        max = new int[N + 1];

        //노드 번호를 알아야 하기 때문에 노드를 초기화한다.
        //최소와 최대를 구해야 하기 때문에 최솟값, 최댓값으로 초기화한다.
        for (int i = 1; i <= N; i++) {
            tree[i] = new Node(i, -1, -1);
            min[i] = Integer.MAX_VALUE;
            max[i] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int leftChild = Integer.parseInt(st.nextToken());
            int rightChild = Integer.parseInt(st.nextToken());

            tree[node].left = leftChild;//왼쪽 자식 연결
            tree[node].right = rightChild;//오른쪽 자식 연결

            if (leftChild != -1) {//왼쪽 자식이 있다면 왼쪽 자식의 부모를 연결한다.
                tree[leftChild].parent = node;
            }
            if (rightChild != -1) {//오른쪽 자식이 있다면 오른쪽 자식의 부모를 연결한다.
                tree[rightChild].parent = node;
            }
        }

        int root = 0;
        for (int i = 1; i <= N; i++) {
            if (tree[i].parent == -1) {//부모가 정해지지 않은 번호가 루트 노드다.
                root = i;
                break;
            }
        }

        //중위 순회 방식으로 레벨과 열 번호를 구하고 레벨마다 가장 왼쪽 열과 가장 오른쪽 열을 구한다.
        inOrder(root, 1);

        int level = 0;
        int width = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            int curWidth = max[i] - min[i];

            if (curWidth > width) {
                width = curWidth;
                level = i;
            }
        }
        System.out.println(level + " " + (width + 1));
    }

    static void inOrder(int curNode, int level) {
        Node node = tree[curNode];//현재 노드
        if (node.left != -1) {//왼쪽 자식부터 처리해야 한다.
            inOrder(node.left, level + 1);//가장 왼쪽부터 처리하도록 한다.
        }
        min[level] = Math.min(min[level], count);//레벨의 최소 열 번호(가장 왼쪽)를 구한다.
        max[level] = Math.max(max[level], count);//레벨의 최대 열 번호(가장 오른쪽)를 구한다.
        count++;//열 번호 증가

        if (node.right != -1) {//마지막으로 오른쪽 자식을 처리한다.
            inOrder(node.right, level + 1);
        }
    }
}
