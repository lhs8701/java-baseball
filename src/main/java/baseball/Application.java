package baseball;

import java.io.FilterOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {

    public static boolean validation(String input) {

        if (input.length() != 3) {
            throw new IllegalArgumentException();
        }
        if (input.contains("0")) {
            throw new IllegalArgumentException();
        }
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException();
        }
        if (!input.equals(input.chars().distinct().map(s -> s - 48).mapToObj(String::valueOf).collect(Collectors.joining()))) {
            throw new IllegalArgumentException();
        }

        return true;
    }

    public static String generateRandomNumber() {
        boolean[] used = {false, false, false, false, false, false, false, false, false, false};
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < 3) {
            int num = (int) (Math.random() * (9 - 1 + 1) + 1);
            if (!used[num]) {
                sb.append(num);
                used[num] = true;
                i++;
            }
        }
        return sb.toString();
    }

    public static String judgeResult(String key, String target) {
        int[] count = {0, 0};
        for (int i = 0; i < 3; i++) {
            compare(key, target, count, i);
        }
        return Arrays.stream(count).mapToObj(String::valueOf).collect(Collectors.joining());
    }

    private static void compare(String key, String target, int[] count, int i) {
        for (int j = 0; j < 3; j++) {
            char tc = target.charAt(i);
            char kc = key.charAt(j);
            if (tc == kc && i == j) {
                count[1] += 1;
            } else if (tc == kc && i != j) {
                count[0] += 1;
            }
        }
    }

    public static String printResult(String count) {
        char ballCount = count.charAt(0);
        char strikeCount = count.charAt(1);
        List<String> list = new ArrayList<>();
        if (strikeCount == '0' && ballCount == '0') {
            list.add("낫싱");
        } else {
            if (ballCount != '0') {
                list.add(String.format("%c볼", ballCount));
            }
            if (strikeCount != '0') {
                list.add(String.format("%c스트라이크", strikeCount));
            }
        }
        return list.stream().collect(Collectors.joining(" "));
    }

    public static String readInput(){
        System.out.print("숫자를 입력해주세요 : ");
        return Console.readLine();
    }

    public static void main(String[] args) {
        System.out.println("숫자 야구 게임을 시작합니다.");

    }

}
