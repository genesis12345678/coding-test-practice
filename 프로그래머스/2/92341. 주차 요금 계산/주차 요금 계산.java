import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> inTime = new HashMap<>();
        Map<String, Integer> totalTime = new HashMap<>();

        for (String record : records) {
            String[] split = record.split(" ");
            int time = convertTime(split[0]);
            String carNum = split[1];
            String action = split[2];

            if (action.equals("IN")) {
                inTime.put(carNum, time);
            } else {
                int temp = time - inTime.remove(carNum);
                totalTime.put(carNum, totalTime.getOrDefault(carNum, 0) + temp);
            }
        }

        int end = convertTime("23:59");
        inTime.forEach((num, time) -> {
            totalTime.put(num, totalTime.getOrDefault(num, 0) + (end - time));
        });

        List<String> list = new ArrayList<>(totalTime.keySet());
        Collections.sort(list);

        int[] result = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            String carNum = list.get(i);
            int time = totalTime.get(carNum);
            result[i] = calculate(time, fees);
        }

        return result;
    }
    
    public int convertTime(String s) {
        String[] split = s.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);

        return hour * 60 + minute;
    }
    
    public int calculate(int time, int[] fees) {
        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        if (time <= basicTime) {
            return basicFee;
        } else {
            int temp = time - basicTime;
            int extra = (int) (Math.ceil((double) temp / unitTime) * unitFee);

            return basicFee + extra;
        }
    }
}