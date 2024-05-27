package programmers.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 주차_요금_계산 {
    public static void main(String[] args) {
        int[] fees1 = {180, 5000, 10, 600};
        int[] fees2 = {120, 0, 60, 591};
        int[] fees3 = {1, 461, 1, 10};

        String[] records1 = {
                "05:34 5961 IN",
                "06:00 0000 IN",
                "06:34 0000 OUT",
                "07:59 5961 OUT",
                "07:59 0148 IN",
                "18:59 0000 IN",
                "19:09 0148 OUT",
                "22:59 5961 IN",
                "23:00 5961 OUT"};

        String[] records2 = {
                "16:00 3961 IN",
                "16:00 0202 IN",
                "18:00 3961 OUT",
                "18:00 0202 OUT",
                "23:58 3961 IN"};

        String[] records3 = {"00:00 1234 IN"};

        int[] result1 = solution(fees1, records1);
        int[] result2 = solution(fees2, records2);
        int[] result3 = solution(fees3, records3);

        System.out.println("result1 = " + Arrays.toString(result1));
        System.out.println("result1 = " + Arrays.toString(result2));
        System.out.println("result1 = " + Arrays.toString(result3));
    }

    private static int[] solution(int[] fees, String[] records) {
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

    private static int calculate(int time, int[] fees) {
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

    private static int convertTime(String s) {
        String[] split = s.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);

        return hour * 60 + minute;
    }


}
