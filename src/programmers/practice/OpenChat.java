package programmers.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class OpenChat {
    public static void main(String[] args) {
        String[] record = {
                "Enter uid1234 Muzi",
                "Enter uid4567 Prodo",
                "Leave uid1234",
                "Enter uid1234 Prodo",
                "Change uid4567 Ryan"};

        String[] result = solution(record);

        System.out.println("result = " + Arrays.toString(result));
    }

    private static String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();

        for (String s : record) {
            String[] split = s.split(" ");

            String action = split[0];

            if (action.equals("Enter")) {
                map.put(split[1], split[2]);
            } else if (action.equals("Change")) {
                map.put(split[1], split[2]);
            }
        }

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < record.length; i++) {
            String[] split = record[i].split(" ");
            String action = split[0];

            if (action.equals("Enter")) {
                list.add(map.get(split[1]) + "님이 들어왔습니다.");
            } else if (action.equals("Leave")) {
                list.add(map.get(split[1]) + "님이 나갔습니다.");
            }
        }

        String[] result = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;

    }

}
