package algorithms.week1;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 100x100 크기의 2차원 좌표계에서 주어진 3개의 값을 기준으로 n개의 입력값을 군집화하는 코드를 작성
 * 입력값의 범위
 * -1 <= x <= 100
 * -1 <= y <= 100
 *
 * 기준값 3개(좌측하단, 좌측상단, 우측중단)
 * x = 5, y = 5
 * x = 5, y = 95
 * x = 95, y = 50
 *
 * 비고
 * - 군집화하는 기준은 자유롭게
 *
 * 예시
 *  입력값(2개) : "6,5" "5,80"
 *  결과
 *  group A = ["5,5","6,5"]
 *  group B = ["5,95", "5,80"]
 *  group C = ["95,50"]
 */
public class Second {

    public static void main(String[] args) {

        List<String> groupA = new ArrayList<>();
        List<String> groupB = new ArrayList<>();
        List<String> groupC = new ArrayList<>();

        String input1 = "6,5";
        String input2 = "5,80";

        List<String> inputList = new ArrayList<>();

        groupA.add("5,5");
        groupB.add("5,95");
        groupC.add("95,50");

        inputList.add(input1);
        inputList.add(input2);

        int xGroupA = Integer.parseInt(groupA.get(0).split(",")[0]);
        int yGroupA = Integer.parseInt(groupA.get(0).split(",")[1]);
        int xGroupB = Integer.parseInt(groupB.get(0).split(",")[0]);
        int yGroupB = Integer.parseInt(groupB.get(0).split(",")[1]);
        int xGroupC = Integer.parseInt(groupC.get(0).split(",")[0]);
        int yGroupC = Integer.parseInt(groupC.get(0).split(",")[1]);

        Pair<Integer, Integer> pairGroupA = new Pair<>(xGroupA, yGroupA);
        Pair<Integer, Integer> pairGroupB = new Pair<>(xGroupB, yGroupB);
        Pair<Integer, Integer> pairGroupC = new Pair<>(xGroupC, yGroupC);

        List<Pair> groupList = new ArrayList<>();
        groupList.add(pairGroupA);
        groupList.add(pairGroupB);
        groupList.add(pairGroupC);

        for(int j=0; j<inputList.size(); j++) {
            int xInput = Integer.parseInt(inputList.get(j).split(",")[0]);
            int yInput = Integer.parseInt(inputList.get(j).split(",")[1]);
            for(int i=0; i<groupList.size(); i++) {
                switch (getCloserGroupId(groupList, new Pair<>(xInput, yInput))){
                    case "A":
                        groupA.add(inputList.get(j));
                        break;
                    case "B":
                        groupB.add(inputList.get(j));
                        break;
                    case "C":
                        groupC.add(inputList.get(j));
                        break;
                }
                break;
            }
        }

        System.out.println(groupA);
        System.out.println(groupB);
        System.out.println(groupC);

    }

    private static String getCloserGroupId(List<Pair> groupList, Pair<Integer, Integer> dot){
        String[] groupId = {"A", "B", "C"};
        String pickId = "";
        double min = 144;
        for(int i=0; i<groupList.size(); i++) {
            if(getDirectDistance(groupList.get(i), dot) < min) {
                min = getDirectDistance(groupList.get(i), dot);
                pickId = groupId[i];
            }
        }
        return pickId;
    }

    private static double getDirectDistance(Pair<Integer, Integer> base, Pair<Integer, Integer> point){
        int xDiff = base.getKey() - point.getValue();
        int yDiff = base.getValue() - point.getValue();
        return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff,2));
    }



}
