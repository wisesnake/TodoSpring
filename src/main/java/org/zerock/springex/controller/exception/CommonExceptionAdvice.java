package org.zerock.springex.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.lang.reflect.Array;
import java.util.Arrays;

@ControllerAdvice // 컨트롤러에서 발생하는 예외에 맞게 처맇 ㅏㄹ 수 있는 기능을 제공, 해당 애너테이션이 선언된 클래스 역시 스프링의 빈으로 변환.
@Log4j2 //
public class CommonExceptionAdvice {


//    @ResponseBody
//    @ExceptionHandler(NumberFormatException.class)
//    public String exceptNumber(NumberFormatException numberFormatException){
//        log.error("---------------------------");
//        log.error(numberFormatException.getMessage());
//        return "NUMBER FORMAT EXCEPTION";
//    }

    @ResponseBody
    @ExceptionHandler(NumberFormatException.class)
    public String exceptCommon(Exception exception){
        log.error("---------------------------");
        log.error(exception.getMessage());
        StringBuffer buffer = new StringBuffer("<ul>");
        buffer.append("<li>"+exception.getMessage()+"</li>");
        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>"+stackTraceElement+"</li>");
        });
        buffer.append("</ul>");
        return buffer.toString();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(){

        return "custom404";
    }


}
