package bridge;

import camp.nextstep.edu.missionutils.Console;

import java.util.Objects;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {
    final String readBridgeSizeMs = "다리의 길이를 입력해주세요.";
    final String readMovingMs = "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    final String readGameCommandMs = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";
    final String exceptNumber = "[ERROR] 3이상 20이하의 숫자를 입력해주세요.";
    final String exceptMoving = "[ERROR] U 혹은 D를 입력해주세요.";
    final String exceptRetry = "[ERROR] R 혹은 Q를 입력해주세요.";
    final String startGame = "다리 건너기 게임을 시작합니다.\n";
    int size;
    String input;

    public void printStart(){
        System.out.println(startGame);
    }
    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        System.out.println(readBridgeSizeMs);
        try {
            input = getInput();
            if (ThisIsNumber(input)) getSize();
            size = Integer.parseInt(input);
            if (RangeOfNumber()) getSize();
        } catch (IllegalArgumentException e) {
            size = readBridgeSize();
        }
        return size;
    }

    private boolean RangeOfNumber() {
        return size < 3 || size > 20;
    }

    void getSize() {
        System.out.println(exceptNumber);
        throw new IllegalArgumentException();
    }

    private boolean ThisIsNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        System.out.println(readMovingMs);
        try {
            input = getInput().toUpperCase();
            if (ThisIsMove()) Except();
        } catch (IllegalArgumentException e) {
            System.out.println(exceptMoving);
            input = readMoving();
        }
        return input;
    }

    private boolean ThisIsMove() {
        return !Objects.equals(input, "U") && !Objects.equals(input, "D");
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        System.out.println(readGameCommandMs);
        try {
            input = getInput();
            if (ThisIsRetry()) Except();
        } catch (IllegalArgumentException e) {
            System.out.println(exceptRetry);
            input = readGameCommand();
        }
        return input;
    }

    private boolean ThisIsRetry() {
        return !Objects.equals(input, "R") && !Objects.equals(input, "Q");
    }

    private String getInput(){
        return Console.readLine();
    }

    void Except() {
        throw new IllegalArgumentException();
    }
}
