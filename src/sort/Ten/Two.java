package sort.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 선 정보를 오름차순으로 정렬하여 저장한다.
 * 선이 완전히 겹쳐지는 경우는 총 길이에 영향이 없다.
 * 선이 걸쳐있는 경우는 걸쳐진 정도는 총 길이에서 제외해야 한다.
 * 완전히 분리되어 있는 경우는 따로 계산한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2170">백준 2170번 - 정렬 : 선 긋기</a>
 * @since 2024-04-24
 */
public class Two {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Line[] lines = new Line[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            lines[i] = new Line(x, y);
        }

        Arrays.sort(lines); //x 오름차순 정렬, x가 같으면 y 오름차순 정렬

        int s = lines[0].x;
        int e = lines[0].y;

        int length = e - s;

        for (int i = 1; i < n; i++) {
            if (s <= lines[i].x && lines[i].y <= e) { //선이 겹치는 경우, 넘어가도 된다.
                continue;
            }

            if (lines[i].x < e && lines[i].y > e) { //이전 선의 끝과 현재 선의 시작 부분이 걸쳐지는 경우, 걸쳐지는 만큼은 제외해야 한다.
                length += lines[i].y - e;
            } else { //선이 완전히 분리된 경우, 다음 선의 길이만큼 총 길이에 더해준다.
                length += lines[i].y - lines[i].x;
            }

            //마지막 선으로 이동
            s = lines[i].x;
            e = lines[i].y;
        }

        System.out.print(length);
    }

    static class Line implements Comparable<Line> {
        int x, y;

        public Line(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Line o) {
            if (this.x == o.x) {
                return this.y - o.y;
            }

            return this.x - o.x;
        }
    }
}
