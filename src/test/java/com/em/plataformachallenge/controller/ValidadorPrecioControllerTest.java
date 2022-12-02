package com.em.plataformachallenge.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.em.plataformachallenge.entidad.PriceEntity;
import com.em.plataformachallenge.utils.errors.ApiError;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

/**
 * Clase que contiene los test de {@link ValidadorPrecioController}
 */
@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ValidadorPrecioControllerTest {

  protected static final String URL = "http://localhost";
  private static final String PRICES = "/v1/prices";

  @LocalServerPort
  private int port;

  @BeforeEach
  void init() {
    RestAssured.baseURI = URL;
    RestAssured.port = port;
  }

  /**
   * Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA) Deberia
   * devolver la lista 1
   */
  @Test
  void challenge_test_1() {
    PriceEntity priceEntity = RestAssured.given()
        .queryParams("productId", 35455, "brandId", 1, "applicationDate", "2020-06-14-10:00:00")
        .when()
        .get(PRICES).then().statusCode(
            HttpStatus.OK.value())
        .extract().as(PriceEntity.class);
    assertEquals(1, priceEntity.getPriceList());
  }

  /**
   * Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA) Deberia
   * devolver la lista 2
   */
  @Test
  void challenge_test_2() {
    PriceEntity priceEntity = RestAssured.given()
        .queryParams("productId", 35455, "brandId", 1, "applicationDate", "2020-06-14-16:00:00")
        .when()
        .get(PRICES).then().statusCode(
            HttpStatus.OK.value())
        .extract()
        .as(PriceEntity.class);
    assertEquals(2, priceEntity.getPriceList());

  }

  /**
   * Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 Deberia devolver
   * lista 1
   */
  @Test
  void challenge_test_3() {
    PriceEntity priceEntity = RestAssured.given()
        .queryParams("productId", 35455, "brandId", 1, "applicationDate", "2020-06-14-21:00:00")
        .when()
        .get(PRICES).then().statusCode(
            HttpStatus.OK.value())
        .extract().as(PriceEntity.class);
    assertEquals(1, priceEntity.getPriceList());

  }

  /**
   * Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 Deberia devolver
   * lista 3
   */
  @Test
  void challenge_test_4() {
    PriceEntity priceEntity = RestAssured.given()
        .queryParams("productId", 35455, "brandId", 1, "applicationDate", "2020-06-15-10:00:00")
        .when()
        .get(PRICES).then().statusCode(
            HttpStatus.OK.value())
        .extract()
        .as(PriceEntity.class);
    assertEquals(3, priceEntity.getPriceList());

  }

  /**
   * Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA) Deberia
   * devolver lista 4
   */
  @Test
  void challenge_test_5() {
    PriceEntity priceEntity = RestAssured.given()
        .queryParams("productId", 35455, "brandId", 1, "applicationDate", "2020-06-16-21:00:00")
        .when()
        .get(PRICES).then().statusCode(
            HttpStatus.OK.value())
        .extract()
        .as(PriceEntity.class);
    assertEquals(4, priceEntity.getPriceList());

  }

  /**
   * Envio Fecha formato incorrecto
   */
  @Test
  void getPrice_with_applicationDate_incorrectFormat() {
    ApiError apiError = RestAssured.given()
        .queryParams("productId", 35455, "brandId", 1, "applicationDate", "2020-06-16:21:00:00")
        .when()
        .get(PRICES).then().statusCode(
            HttpStatus.BAD_REQUEST.value())
        .extract()
        .as(ApiError.class);

    assertEquals(
        "Error convirtiendo la fecha. El formato debe ser 'yyyy-MM-dd-HH:mm:ss' Ejemplo: 2020-06-15-11:00:00",
        apiError.getErrors().get(0).getMessage());
  }

  /**
   * Envio prouductId formato incorrecto
   */
  @Test
  void getPrice_with_productId_incorrectFormat() {
    ApiError apiError = RestAssured.given()
        .queryParams("productId", "asd", "brandId", 1, "applicationDate", "2020-06-16-21:00:00")
        .when()
        .get(PRICES).then().statusCode(
            HttpStatus.BAD_REQUEST.value())
        .extract()
        .as(ApiError.class);

    assertEquals(
        "productId invalido",
        apiError.getErrors().get(0).getMessage());
  }

  /**
   * Envio brandId formato incorrecto
   */
  @Test
  void getPrice_with_brandId_incorrectFormat() {
    ApiError apiError = RestAssured.given()
        .queryParams("productId", 1, "brandId", "s", "applicationDate", "2020-06-16-21:00:00")
        .when()
        .get(PRICES).then().statusCode(
            HttpStatus.BAD_REQUEST.value())
        .extract()
        .as(ApiError.class);

    assertEquals(
        "brandId invalido",
        apiError.getErrors().get(0).getMessage());
  }

  /**
   * No Envio applicationDate
   */
  @Test
  void getPrice_without_applicationDate() {
    ApiError apiError = RestAssured.given()
        .queryParams("productId", 1, "brandId", 1)
        .when()
        .get(PRICES).then().statusCode(
            HttpStatus.BAD_REQUEST.value())
        .extract()
        .as(ApiError.class);

    assertEquals(
        "applicationDate no enviado",
        apiError.getErrors().get(0).getMessage());
  }

  /**
   * No Envio productId
   */
  @Test
  void getPrice_without_productId() {
    ApiError apiError = RestAssured.given()
        .queryParams("brandId", 1, "applicationDate", "2020-06-16-21:00:00")
        .when()
        .get(PRICES).then().statusCode(
            HttpStatus.BAD_REQUEST.value())
        .extract()
        .as(ApiError.class);

    assertEquals(
        "productId no enviado",
        apiError.getErrors().get(0).getMessage());
  }

  /**
   * No Envio brandId
   */
  @Test
  void getPrice_without_brandId() {
    ApiError apiError = RestAssured.given()
        .queryParams("productId", 1, "applicationDate", "2020-06-16-21:00:00")
        .when()
        .get(PRICES).then().statusCode(
            HttpStatus.BAD_REQUEST.value())
        .extract()
        .as(ApiError.class);

    assertEquals(
        "brandId no enviado",
        apiError.getErrors().get(0).getMessage());
  }

  /**
   * Precio no encontrado para los datos ingresados
   */
  @Test
  void getPrice_price_notFound() {
    ApiError apiError = RestAssured.given()
        .queryParams("productId", 35456, "brandId", 1, "applicationDate", "2020-06-16-21:00:00")
        .when()
        .get(PRICES).then().statusCode(
            HttpStatus.NOT_FOUND.value())
        .extract()
        .as(ApiError.class);

    assertEquals(
        "Precio no encontrado para la cadena 1, producto 35456 y fecha de aplicacion 2020-06-16-21:00:00.",
        apiError.getErrors().get(0).getMessage());
  }

  /**
   * Marca no encontrada
   */
  @Test
  void getPrice_brand_notFound() {
    ApiError apiError = RestAssured.given()
        .queryParams("productId", 35455, "brandId", 11, "applicationDate", "2020-06-16-21:00:00")
        .when()
        .get(PRICES).then().statusCode(
            HttpStatus.NOT_FOUND.value())
        .extract()
        .as(ApiError.class);

    assertEquals(
        "Cadena con id 11 no encontrada.",
        apiError.getErrors().get(0).getMessage());
  }

}
