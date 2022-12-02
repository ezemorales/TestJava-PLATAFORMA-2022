package com.em.plataformachallenge.advice;

import com.em.plataformachallenge.utils.Messages;
import com.em.plataformachallenge.utils.errors.ApiError;
import com.em.plataformachallenge.utils.errors.ApiFieldError;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Advice controller que captura las excepciones de validaciones
 */
@RestControllerAdvice
public class ValidAdvice {

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ApiError validationError(MissingServletRequestParameterException ex) {
    return new ApiError(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(),
        Collections.singletonList(new ApiFieldError(null,
            String.format(Messages.PARAMETER_MISSING, ex.getParameterName()))));
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ApiError validationError(MethodArgumentTypeMismatchException ex) {
    return new ApiError(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(),
        Collections.singletonList(new ApiFieldError(null,
            String.format(Messages.PARAMETER_INVALID, ex.getParameter().getParameterName()))));
  }

  @ExceptionHandler(DateTimeParseException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ApiError validationError() {
    return new ApiError(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(),
        Collections.singletonList(new ApiFieldError(null, Messages.DATETIME_PARSE_ERROR)));
  }
}
