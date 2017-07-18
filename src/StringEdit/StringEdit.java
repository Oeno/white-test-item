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
        int[] alNum = new int[26];
        Map<Character, Integer> alNumMap = new HashMap();


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
                punctList.add(m.group() + " ");
                words.push(word.replace(m.group(), ""));
            } else {
                punctList.add(" ");
                words.push(word);
            }
        }

        // 문장에 포함된 알파벳의 전체 수 및 개별 수 계산
        for (String word : words) {
            for (char alphabet : word.toLowerCase().toCharArray())
                alNum[alphabet - 97]++;

            length += word.length();
        }

        // 개별 수 Map에 저장
        char idx = 'a';
        for (int value : alNum) {
            alNumMap.put(idx++, value);
        }

        // 단어 역순으로 출력
        for (int i=0; i<punctList.size(); i++) {
            System.out.print(words.pop() + punctList.get(i));
        }
        System.out.printf("\n");

        System.out.println("전체 수 : " + length);

        // 알파벳 숫자를 최상위 빈도로 출력
        int value = 0;
        for (char alphabet : sortByValue(alNumMap)) {
            value = alNumMap.get(alphabet);
            if (value == 0)
                break;

            System.out.printf("%c: %d\n", alphabet, value);
        }
    }

    public static List<Character> sortByValue(final Map<Character, Integer> map) {
        List<Character> list = new ArrayList();
        list.addAll(map.keySet());

         Collections.sort(list, new Comparator<Character>() {
             @SuppressWarnings("unchecked")
             @Override
             public int compare(Character c1, Character c2) {
                Integer v1 = map.get(c1);
                Integer v2 = map.get(c2);

                return ((Comparable) v2).compareTo(v1);
             }
         });

         return list;
    }
}