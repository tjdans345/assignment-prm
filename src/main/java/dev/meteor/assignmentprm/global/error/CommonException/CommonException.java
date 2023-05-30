package dev.meteor.assignmentprm.global.error.CommonException;


import dev.meteor.assignmentprm.global.enum_package.error.ErrorEnum;

public class CommonException extends RuntimeException{

    private final ErrorEnum errorEnum;

    public CommonException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.errorEnum = errorEnum;
    }

    public ErrorEnum getErrorEnum() {
        return this.errorEnum;
    }

}
