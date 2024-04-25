package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 입력은 전위 순회를 한 결과다.
 * 전위 순회는 (루트 -> 왼쪽 -> 오른쪽)이기 때문에 첫 값이 트리의 루트라는 것을 알 수 있다.
 * 그렇다면 이제 루트를 기준으로 앞으로 입력되는 숫자들은 크기에 따라서 왼쪽 또는 오른쪽 자식 노드로 저장하면 된다.
 * 이후 루트 노드를 시작으로 후위 순회를 출력한다. (왼쪽 -> 오른쪽 -> 루트)
 */

/**
 * <a href = "https://www.acmicpc.net/problem/5639">백준 5639번 - 트리 : 이진 검색 트리</a>
 * @since 2024-04-24
 */
public class One {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int r = Integer.parseInt(br.readLine());
        Node root = new Node(r);

        while (true) {
            String s = br.readLine();

            if (s == null) {
                break;
            }

            root.insert(Integer.parseInt(s)); //루트를 타고 크기에 따라 왼쪽 또는 오른쪽 자식 노드에 배치
        }

        postOrder(root); //후위 순회 출력
    }

    static void postOrder(Node node) {
        if (node == null) { //리프 노드 도달
            return;
        }

        //후위 순회 순서
        //왼쪽 -> 오른쪽 -> 루트
        postOrder(node.leftChild);
        postOrder(node.rightChild);
        System.out.println(node.num);
    }

    static class Node {
        int num;
        Node leftChild, rightChild;

        public Node(int num) {
            this.num = num;
        }

        public void insert(int node) {
            if (this.num > node) { //자신이 큰 경우, 왼쪽 자식 노드로 배치해야 한다.

                if (this.leftChild == null) {
                    leftChild = new Node(node);
                } else { //마지막 자식이 없을 때까지 이동한다.
                    leftChild.insert(node);
                }

            } else { //자식이 더 큰 경우, 오른쪽 자식 노드로 배치해야 한다.

                if (this.rightChild == null) {
                    rightChild = new Node(node);
                } else { //마지막 자식이 없을 때가지 이동한다.
                    rightChild.insert(node);
                }
            }
        }
    }
}
