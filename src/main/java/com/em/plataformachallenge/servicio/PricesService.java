package com.em.plataformachallenge.servicio;

import com.em.plataformachallenge.entidad.PriceEntity;

/**
 * Interfaz del servicio validador de precios
 */
public interface PricesService {

  /**
   * Metodo que busca el precio correspondiente a los valores ingresados
   *
   * @param brandId         Identificador de la Cadena
   * @param productId       Identificador del producto
   * @param applicationDate Fecha de aplicacion
   * @return Entidad de precio con sus datos
   */
  PriceEntity getPrice(Long brandId, Long productId, String applicationDate);
}
