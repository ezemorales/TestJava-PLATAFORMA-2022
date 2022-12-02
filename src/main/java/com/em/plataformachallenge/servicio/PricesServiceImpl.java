package com.em.plataformachallenge.servicio;

import com.em.plataformachallenge.entidad.BrandEntity;
import com.em.plataformachallenge.entidad.PriceEntity;
import com.em.plataformachallenge.exceptions.EntityNotFoundException;
import com.em.plataformachallenge.repository.BrandRepository;
import com.em.plataformachallenge.repository.PricesRepository;
import com.em.plataformachallenge.utils.Messages;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion del servicio validador de precios
 */
@Service
public class PricesServiceImpl implements PricesService {

  Logger log = LoggerFactory.getLogger(PricesServiceImpl.class);
  @Autowired
  PricesRepository pricesRepository;

  @Autowired
  BrandRepository brandRepository;

  @Override
  public PriceEntity getPrice(Long brandId, Long productId, String applicationDate) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
    LocalDateTime applicationDateTime = LocalDateTime.parse(applicationDate, formatter);

    log.debug("[getPrice---] Buscando marca");
    Optional<BrandEntity> brandEntity = brandRepository.findById(brandId);

    if (brandEntity.isEmpty()) {
      String errorMsg = String.format(Messages.BRAND_NOT_FOUND, brandId);
      log.debug("[brandRepository.findById---] " + errorMsg);
      throw new EntityNotFoundException(errorMsg);
    }

    log.debug("[getPrice---] Buscando precio");
    Optional<PriceEntity> priceEntityOptional = pricesRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
        brandEntity.get(), productId, applicationDateTime, applicationDateTime);

    return priceEntityOptional.orElseThrow(() -> new EntityNotFoundException(
        String.format(Messages.PRICE_NOT_FOUND, brandId, productId, applicationDate)));
  }
}
