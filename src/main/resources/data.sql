-- BRANDS ( ID NAME)
INSERT INTO BRANDS (id, name)
VALUES (1, 'ZARA');
INSERT INTO BRANDS (id, name)
VALUES (2, 'PULL & BEAR');
INSERT INTO BRANDS (id, name)
VALUES (3, 'MASSIMO DUTTI');
INSERT INTO BRANDS (id, name)
VALUES (4, 'BERSHKA');
INSERT INTO BRANDS (id, name)
VALUES (5, 'STRADIVARIUS');
INSERT INTO BRANDS (id, name)
VALUES (6, 'OYSHO');
INSERT INTO BRANDS (id, name)
VALUES (7, 'ZARA HOME');

-- PRICES (ID  	CURR  	END_DATE  	PRICE  	PRICE_LIST  	PRIORITY  	PRODUCT_ID  	START_DATE  	BRAND_ID  )
INSERT INTO PRICES (id, curr, end_date, price, price_list, priority, product_id, start_date,
                    brand_id)
VALUES (UUID(), 'EUR', '2020-12-31 23.59.59', 35.50, 1, 0, 35455, '2020-06-14 00.00.00', 1);
INSERT INTO PRICES (id, curr, end_date, price, price_list, priority, product_id, start_date,
                    brand_id)
VALUES (UUID(), 'EUR', '2020-06-14 18.30.00', 25.45, 2, 1, 35455, '2020-06-14 15.00.00', 1);
INSERT INTO PRICES (id, curr, end_date, price, price_list, priority, product_id, start_date,
                    brand_id)
VALUES (UUID(), 'EUR', '2020-06-15 11.00.00', 30.50, 3, 1, 35455, '2020-06-15 00.00.00', 1);
INSERT INTO PRICES (id, curr, end_date, price, price_list, priority, product_id, start_date,
                    brand_id)
VALUES (UUID(), 'EUR', '2020-12-31 23.59.59', 38.95, 4, 1, 35455, '2020-06-15 16.00.00', 1);