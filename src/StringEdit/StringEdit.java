package StringEdit;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by oeno on 2017. 7. 17..
 */
public class StringEdit {

    public static void main(String[] args) {
        String sentence;
        int length = 0;
        Stack<String> words = new Stack();
        List<String> punctList = new ArrayList();

        // 구두점을 떼어 내기 위한 Pattern instance 생성
        Pattern p = Pattern.compile("\\p{Punct}");

        // 문자열 입력
        Scanner sc = new Scanner(System.in);
        sentence = sc.nextLine();

        // 공백으로 단어 단위로 분할
        for (String word : sentence.split(" ")) {
            // 구두점 분할 후, 단어를 stack에 저장
            Matcher m = p.matcher(word);
            if (m.find()) {
                punctList.add(m.group());
                words.push(word.replace(m.group(), ""));
            } else {
                punctList.add(" ");
                words.push(word);
            }
        }

        // 문장에 포함된 알파벳의 전체 수 계산
        for (String word : words) {
            length += word.length();
        }

        // 단어 역순으로 출력
        for (int i=0; i<punctList.size(); i++) {
            System.out.print(words.pop() + punctList.get(i));
        }
        System.out.printf("\n");

        System.out.println("전체 수 : " + length);
    }
}
