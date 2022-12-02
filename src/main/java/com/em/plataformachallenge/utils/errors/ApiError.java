package com.em.plataformachallenge.utils.errors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Clase que modela los errores
 */
@Data
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class ApiError {

  private HttpStatus status;
  private String message;
  private List<ApiFieldError> errors;

  public ApiError(HttpStatus status, String message, List<ApiFieldError> errors) {
    this.status = status;
    this.message = message;
    this.errors = errors;
  }

  public ApiError(HttpStatus status, String message, ApiFieldError error) {
    this.status = status;
    this.message = message;
    this.errors = Collections.singletonList(error);
  }

  public ApiError(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  public void addError(ApiFieldError apiFieldError) {
    if (errors == null) {
      errors = new ArrayList<>();
    }
    errors.add(apiFieldError);
  }

}