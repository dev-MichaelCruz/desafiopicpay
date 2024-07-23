package br.com.michaelcruz.desafiopicpay.controller;

import br.com.michaelcruz.desafiopicpay.exceptions.PicPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PicPayException.class)
    public ProblemDetail handlePicPayException(PicPayException e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<InvalidParam> fieldErros = e.getFieldErrors()
                .stream()
                .map(field -> new InvalidParam(field.getField(), field.getDefaultMessage()))
                .toList();

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problemDetail.setTitle("Your requests parameters didn't validate");
        problemDetail.setProperty("Invalid Params", fieldErros);

        return problemDetail;
    }

    private record InvalidParam(String name, String reason){}
}
