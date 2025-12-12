package com.soses.multilines.common;

public enum UnitModeEnum {

	CASE('C'),
    PIECE('P');

    private final char code;

    UnitModeEnum(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static UnitModeEnum fromCode(char code) {
        for (UnitModeEnum mode : values()) {
            if (mode.code == code) {
                return mode;
            }
        }
        throw new IllegalArgumentException("Unknown UnitMode code: " + code);
    }
}
