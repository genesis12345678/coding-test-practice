package programmers.practice;


public class VisitLength {

    static boolean[][][] visit = new boolean[11][11][4];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        String dirs1 = "ULURRDLLU";
        String dirs2 = "LULLLLLLU";

        System.out.println(solution(dirs1));
        System.out.println(solution(dirs2));
    }

    static int solution(String dirs) {

        int x = 5;
        int y = 5;
        int count = 0;

        for (char c : dirs.toCharArray()) {
            int d = getDir(c);
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= 11 || ny >= 11) {
                continue;
            }

            if (!visit[nx][ny][d]) {
                visit[nx][ny][d] = true;
                visit[x][y][(d + 2) % 4] = true;
                count++;
            }

            x = nx;
            y = ny;
        }

        return count;
    }

    private static int getDir(char c) {
        switch (c) {
            case 'U' :
                return 0;
            case 'L' :
                return 1;
            case 'D' :
                return 2;
            default:
                return 3;
        }
    }
}
