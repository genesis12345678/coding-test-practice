package Level16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Six_2 {
    public static int[] que = new int[2_000_000];
    public static int size = 0;
    public static int front = 0;
    public static int back = 0;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "push":
                    push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    pop();
                    break;
                case "size":
                    size();
                    break;
                case "empty":
                    empty();
                    break;
                case "front":
                    front();
                    break;
                case "back":
                    back();
                    break;
            }
        }
        System.out.println(sb);
    }

    public static void push(int i) {
        que[back++] = i;
        size++;
    }
    public static void pop() {
        if (size == 0) {
            sb.append(-1).append("\n");
        } else {
            sb.append(que[front++]).append("\n");
            size--;
        }
    }
    public static void size() {
        sb.append(size).append("\n");
    }
    public static void empty() {
        sb.append(size == 0 ? 1 : 0).append("\n");
    }
    public static void front() {
        sb.append(size == 0 ? -1 : que[front]).append("\n");
    }
    public static void back() {
        sb.append(size == 0 ? -1 : que[back - 1]).append("\n");
    }
}
