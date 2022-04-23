package racingcar.models;

import java.util.regex.Pattern;

public class CarName {

    private static final int MAX_LENGTH = 5;
    private static final String NOT_CONTAIN_CHAR = ",";
    private final String name;

    public CarName(String name) {
        validate(name);
        this.name = name;
    }

    public String get() {
        return this.name;
    }

    private void validate(String name) {
        if (name.isEmpty())
            throw new IllegalArgumentException("자동차의 이름이 필요해요.");
        if (name.length() > MAX_LENGTH)
            throw new IllegalArgumentException("자동차 이름은 5자 이내 이어야 해요.");
        if (Pattern.matches("(.*)[" + NOT_CONTAIN_CHAR + "](.*)", name))
            throw new IllegalArgumentException("자동차 이름에 " + NOT_CONTAIN_CHAR + " 는 사용할 수 없어요.");
    }
}
