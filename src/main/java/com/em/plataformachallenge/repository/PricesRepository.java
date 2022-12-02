package com.em.plataformachallenge.repository;

import com.em.plataformachallenge.entidad.BrandEntity;
import com.em.plataformachallenge.entidad.PriceEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Repositorio de acceso a base de datos JPA
 */
public interface PricesRepository extends CrudRepository<PriceEntity, UUID> {

  /**
   * Consulta que busca el precio por marca, producto que esta vigente ordenados por prioridad,
   * tomando el primero.
   *
   * @param brand
   * @param productId
   * @param startDate
   * @param endDate
   * @return
   */
  Optional<PriceEntity> findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
      BrandEntity brand, Long productId, LocalDateTime startDate, LocalDateTime endDate);
}
