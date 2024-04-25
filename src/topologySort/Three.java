package topologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 자식과 부모의 정보와 모든 사람의 혈통 정보를 Map에 저장한다.
 * 왕의 혈통 1을 시작으로 부모를 타고 올라갈수록 1/2 씩 줄어든다.
 * 위상 정렬과 직접적인 연관이 있는지는 모르겠다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/5021">백준 5021번 - 위상 정렬 : 왕위 계승</a>
 * @since 2024-04-25
 */
public class Three {

    static Map<String, List<String>> family = new HashMap<>();
    static Map<String, Double> blood = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String king = br.readLine(); //유토피아를 세운 사람

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String child = st.nextToken();
            family.computeIfAbsent(child, k -> new ArrayList<>()); //밑에랑 같은 기능
//            if (family.get(child) == null) {
//                family.put(child, new ArrayList<>());
//            }

            //아버지, 어머니 정보 저장
            String father = st.nextToken();
            String mother = st.nextToken();

            family.get(child).add(father);
            family.get(child).add(mother);

            //초기 혈통 정보 저장
            blood.put(child, -1d);
            blood.put(father, -1d);
            blood.put(mother, -1d);
        }

        blood.put(king, 1d); //왕은 1로 초기화

        for (String s : blood.keySet()) { //미리 모든 혈통 정보를 구해놓는다.
            dfs(s);
        }

        double max = 0;
        String candidate = "";
        for (int i = 0; i < m; i++) {
            candidate = br.readLine();

            //위에서 이미 구한 혈통 정보로 값을 비교한다.
            //값이 클수록 왕과 혈통이 가깝다.
            if (blood.getOrDefault(candidate, 0d) > max) {
                max = blood.get(candidate);
                candidate = candidate;
            }
        }

        System.out.print(candidate);
    }

    static double dfs(String child) {

        if (blood.get(child) != -1) { //이미 혈통 정보를 구한 적이 있다면 리턴
            return blood.get(child);
        }

        if (family.get(child) == null) { //마지막 혈통인 경우
            blood.put(child, 0d);
            return 0;
        }

        String father = family.get(child).get(0);
        String mother = family.get(child).get(1);

        double fa = dfs(father);
        double mo = dfs(mother);

        blood.put(child, (fa + mo) / 2); //아버지의 어머니 혈통의 반 씩 받아야 한다.

        return blood.get(child);
    }
}
