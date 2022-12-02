package com.em.plataformachallenge.advice;

import com.em.plataformachallenge.exceptions.EntityNotFoundException;
import com.em.plataformachallenge.utils.errors.ApiError;
import com.em.plataformachallenge.utils.errors.ApiFieldError;
import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Advice controller que captura las excepciones de dominio
 */
@RestControllerAdvice
public class DomainAdvice {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ApiError validationError(EntityNotFoundException e) {
    return new ApiError(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(),
        Collections.singletonList(new ApiFieldError(null, e.getMessage())));
  }
}
