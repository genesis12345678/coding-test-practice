package bfs.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 아이디어
 * 조건을 맞춰 1,2,3번을 반복진행 하면서 S개를 만드는 순간 걸린 시간을 출력한다.
 * 방문배열은 화면과 클립보드 각각 해야 하기에 2중 배열로 선언해준다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/14226">백준 14226번 : 너비우선탐색 - 이모티콘</a>
 * @since 2024-02-06
 */
public class Ten {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());


        Queue<int[]> qu = new LinkedList<>();
        boolean[][] visit = new boolean[1001][1001];//[클립보드에 저장한 개수][이모티콘 개수]
        visit[0][1] = true;

        qu.offer(new int[]{1, 0, 0});

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int em = now[0]; //이모티콘 개수
            int clips = now[1]; //클립보드에 저장된 개수
            int time = now[2]; //걸린 시간

            //S개 만들면 종료
            if (em == S) {
                System.out.println(time);
                return;
            }

            //이모티콘을 클립보드에 저장, 덮어쓰기가 되므로 em + clips가 아닌 clips를 em으로 대체한다.
            qu.offer(new int[]{em, em, time + 1});

            //클립보드에 있는 이모티콘을 화면에 붙여넣기
            //clips > 0: 클립보드가 비어있으면 안 된다.
            //em + clips <= S : 화면에 붙어넣은 후 개수가 S개 이하여야 한다.
            if (clips > 0 && em + clips <= S && !visit[clips][em + clips]) {
                qu.offer(new int[]{em + clips, clips, time + 1});
                visit[clips][em + clips] = true;
            }

            //화면에 있는 이모티콘 중 하나를 삭제
            if (em - 1 > 0 && !visit[clips][em - 1]) {
                qu.offer(new int[]{em - 1, clips, time + 1});
            }
        }
    }
}
