package algorithms.week1;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

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
public class First {

    static int xmin = 0;
    static int ymin = 0;

    public static void main(String[] args) {
        int max = 100;

        // 기준점 3개 및 군집화 추가용 리스트
        List<String> groupA = new ArrayList<>();
        List<String> groupB = new ArrayList<>();
        List<String> groupC = new ArrayList<>();
        groupA.add("5,5");
        groupB.add("5,95");
        groupC.add("95,50");

        // 입력값 리스트
        List<String> input = new ArrayList<>();
//        for(String s : args) {
//            input.add(s);
//        }
        input.add("6,5");
        input.add("5,80");
        input.add("100,80");
        input.add("5,50");

        Pair<Integer, Integer> pairGroupA = new Pair<>(Integer.parseInt(groupA.get(0).split(",")[0]), Integer.parseInt(groupA.get(0).split(",")[1]));
        Pair<Integer, Integer> pairGroupB = new Pair<>(Integer.parseInt(groupB.get(0).split(",")[0]), Integer.parseInt(groupB.get(0).split(",")[1]));
        Pair<Integer, Integer> pairGroupC = new Pair<>(Integer.parseInt(groupC.get(0).split(",")[0]), Integer.parseInt(groupC.get(0).split(",")[1]));

        int xOfBetweenAandB = betweenValue(pairGroupA.getKey(), pairGroupB.getKey());
        int yOfBetweenAandB = betweenValue(pairGroupA.getValue(),pairGroupB.getValue());
        int xOfBetweenBandC = betweenValue(pairGroupB.getKey(), pairGroupC.getKey());
        int yOfBetweenBandC = betweenValue(pairGroupB.getValue(), pairGroupC.getValue());
        int xOfBetweenCandA = betweenValue(pairGroupC.getKey(), pairGroupA.getKey());
        int yOfBetweenCandA = betweenValue(pairGroupC.getValue(), pairGroupA.getValue());

//        System.out.println("xOfBetweenAandB >>> " + xOfBetweenAandB);
//        System.out.println("yOfBetweenAandB >>> " + yOfBetweenAandB);
//        System.out.println("xOfBetweenBandC >>> " + xOfBetweenBandC);
//        System.out.println("yOfBetweenBandC >>> " + yOfBetweenBandC);
//        System.out.println("xOfBetweenCandA >>> " + xOfBetweenCandA);
//        System.out.println("yOfBetweenCandA >>> " + yOfBetweenCandA);


        int inputX = 0;
        int inputY = 0;

        // 입력값의 개수만큼 루프
        for(int inputIndex=0; inputIndex<input.size(); inputIndex++) {
            inputX = Integer.parseInt(input.get(inputIndex).split(",")[0]);
            inputY = Integer.parseInt(input.get(inputIndex).split(",")[1]);

            if(inputX < xOfBetweenCandA && inputY < yOfBetweenAandB) {
                groupA.add(input.get(inputIndex));
            } else if(inputX < xOfBetweenBandC && inputY <= max) {
                groupB.add(input.get(inputIndex));
            } else {
                groupC.add(input.get(inputIndex));
            }
        }

        System.out.println("groupA >>> " + groupA);
        System.out.println("groupB >>> " + groupB);
        System.out.println("groupC >>> " + groupC);

    }


    static int betweenValue(int param1, int param2) {
        int temp = 0;
        if(param1 < param2) {
            temp = param1;
            param1 = param2;
            param2 = temp;
        }
        return param1 - ((param1 - param2) / 2);
    }

}
