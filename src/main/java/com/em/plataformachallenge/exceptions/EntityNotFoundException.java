package com.em.plataformachallenge.exceptions;

/**
 * Excepción personalizada para entidad no encontrada
 */
public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(String mensaje) {
    super(mensaje);
  }
}
