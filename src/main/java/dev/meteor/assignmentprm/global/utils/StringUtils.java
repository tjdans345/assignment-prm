package dev.meteor.assignmentprm.global.utils;

import dev.meteor.assignmentprm.global.enum_package.uuid.UuidTypeEnum;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtils {

    /**
     * 공백 or Null 체크 후 boolean 값 반환
     * @param value 체크할 값
     * @return boolean ( 데이터 공백 or Null 시 true 반환 )
     */
    public static boolean checkDataNullOrEmpty(String value) {
        if(value == null) {
            return true;
        }
        // 공백 제거 후 데이터 검증
        return org.apache.commons.lang3.StringUtils.isEmpty(value.trim());
    }

    /**
     * 데이터 타입 변환 Method
     * localDateTime To String Converter Method
     * @param dateTime LocalDateTime Data
     * @return String Data ("yyyy-MM-dd HH:mm")
     */
    public static String localDateTimeToString(LocalDateTime dateTime) {
        final String FORMAT = "yyyy-MM-dd HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        return dateTime.format(formatter);
    }


    /**
     * 코드 벨류 값 생성 (uuid)
     * @return String (codeValue)
     */
    public static String createCodeValue() {
        return RandomStringUtils.randomAlphanumeric(25);
    }

    /**
     * Type 별 Code 디비 UUID 생성 Method
     * @param uuidTypeEnum UuidType (UuidTypeEnum)
     * @return Code Type 별 UUID
     */
    public static String createUUID(UuidTypeEnum uuidTypeEnum) {

        final String DATE_FORMAT = "yyyyMMddHHmmss";

        String uuid = RandomStringUtils.randomAlphanumeric(25);
        String nowDateFormatting = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT));


        return uuidTypeEnum.toString() + "_" + uuid + "_" + nowDateFormatting;
    }
}
