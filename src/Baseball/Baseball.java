package Baseball;

import java.util.*;

/**
 * Created by oeno on 2017. 7. 18..
 */
public class Baseball {
    public static void main(String[] args) {
        Integer[] availabels = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> answer = Arrays.asList(availabels);
        List<Integer> guess;
        String input;

        // 중복이 아닌 무작위 list 생성
        Collections.shuffle(answer);

        while(true) {
            // 3자리 숫자 입력
            System.out.printf("정답: ");
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.next("^[1-9]{3}$");
            } catch (InputMismatchException e) {
                System.out.println("[Exception] 1~9사이의 3자리 숫자를 입력하시오.");
                continue;
            }

            // 입력 값을 정수로 변환
            guess = new ArrayList();
            for (char c : input.toCharArray())
                guess.add(c - '0');

            // 3개의 숫자가 일치 -> 게임 종료
            if (guess.equals(answer.subList(0,3))) {
                System.out.println("[안내] 정답입니다. 게임을 종료합니다.");
                break;
            }
        }
    }
}
