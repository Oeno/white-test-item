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
        int strike, ball;

        // 중복이 아닌 무작위 list 생성
        Collections.shuffle(answer);
        answer = answer.subList(0,3);

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

            // 입력 값을 정수로 변환 및 중복 방지
            guess = new ArrayList();
            for (char c : input.toCharArray())
                guess.add(c - '0');
            if (guess.get(0) == guess.get(1) || guess.get(1) == guess.get(2) || guess.get(0) == guess.get(2)) {
                System.out.println("[안내] 서로 다른 수를 입력하시오.");
                continue;
            }

            strike = ball = 0;
            for (int i = 0; i < 3; i++) {
                if (guess.get(i) == answer.get(i))
                    strike++;
                else if (answer.contains(guess.get(i)))
                    ball++;
            }

            if (strike == 3) {
                System.out.println("[안내] 정답입니다. 게임을 종료합니다.");
                break;
            }

            System.out.print("[힌트] ");
            if (strike > 0)
                System.out.printf("%d 스트라이크 ", strike);
            if (ball > 0)
                System.out.printf("%d 볼", ball);
            if (strike == 0 && ball == 0)
                System.out.printf("낫싱");
            System.out.println("");

        }
    }
}
