package com.em.plataformachallenge.repository;

import com.em.plataformachallenge.entidad.BrandEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de acceso a BRand
 */
@Repository
public interface BrandRepository extends CrudRepository<BrandEntity, Long> {

}
