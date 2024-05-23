import java.util.*;

class Solution {
    public String[] solution(String[] record) {
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