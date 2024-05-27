package programmers.practice;

public class SkillTree {
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};

        System.out.println(solution(skill, skill_trees));

    }

    private static int solution(String skill, String[] skillTrees) {

        int count = 0;

        for (String skillTree : skillTrees) {
            if (check(skill, skillTree)) {
                count++;
            }
        }
        return count;
    }

    private static boolean check(String skill, String skillTree) {

        int index = 0;

        for (char ch : skillTree.toCharArray()) {
            int temp = skill.indexOf(ch);

            if (temp == -1) {
                continue;
            }

            if (index == temp) {
                index++;
            } else {
                return false;
            }

        }

        return true;
    }


}
