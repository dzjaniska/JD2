DROP SCHEMA shop CASCADE;
CREATE SCHEMA shop;
SET SEARCH_PATH TO shop;

CREATE TABLE user_info (
  id          BIGSERIAL PRIMARY KEY,
  name        CHARACTER VARYING(128) NOT NULL,
  surname     CHARACTER VARYING(128),
  second_name CHARACTER VARYING(128)
);

INSERT INTO user_info (name, surname, second_name) VALUES
  ('Ivan', 'Ivanov', 'Ivanovich'),
  ('Petr', 'Petrov', 'Petrovich'),
  ('Alexandr', 'Ivanov', 'Ivanovich'),
  ('Fedor', 'Fedorov', 'Ivanovich');

CREATE TABLE "user" (
  id           BIGSERIAL PRIMARY KEY,
  login        CHARACTER VARYING(128) NOT NULL UNIQUE,
  password     CHARACTER VARYING(64)  NOT NULL,
  role         CHARACTER VARYING(32)  NOT NULL,
  user_info_id BIGINT                 NOT NULL REFERENCES user_info (id)
);

INSERT INTO shop."user" (login, password, role, user_info_id) VALUES
  ('admin', 'admin', 'ADMIN', 1),
  ('admin1', 'admin', 'ADMIN', 2),
  ('customer', 'customer', 'CUSTOMER', 3),
  ('customer1', 'customer', 'CUSTOMER', 4);

CREATE TABLE shop (
  id          BIGSERIAL PRIMARY KEY,
  name        CHARACTER VARYING(64)   NOT NULL UNIQUE,
  description CHARACTER VARYING(1024) NOT NULL,
  logo        TEXT                    NOT NULL,
  regNumber   BIGINT                  NOT NULL UNIQUE
);

INSERT INTO shop (name, description, logo, regNumber) VALUES
  ('TTN', 'TTN.by - №1 по отзывам и ассортименту на Onliner!
Мы рады предложить Вам более 110 000 товаров из самых разных категорий:
компьютеры, комплектующие, ноутбуки, мониторы и принтеры, смартфоны и радиотелефоны, весь спектр бытовой техники, телевизоры и аудиотехника, ручной и электроинструмент, садовая техника и товары для отдыха, спортивные товары и тренажеры, сантехника, товары для детей, электроника для автомобиля и многое другое!
', 'https://content.onliner.by/b2b/2118/logotype/90b48e4557859197f2987bbd40c481a4.png', 12345678),
  ('SOCKET.BY', 'Интернет-гипермаркет SOCKET.BY – это:

•Оперативная и грамотная консультация
•Наличный и безналичный расчет
•Ассортимент, насчитывающий более 80 000 наименований товаров
•Доставка в любой населённый пункт Республики Беларусь
•Низкие цены и система скидок для постоянных клиентов
•Кредит до 36 месяцев и рассрочка на полгода
•Оплата наличными курьеру, пластиковой картой а также картами рассрочки "ХАЛВА" "КАРТА ПОКУПОК" "СМАРТ КАРТА" и "ЧЕРЕПАХА"
•Удобная оплата из любого уголка страны картами рассрочки и обычными картами VISA MASTERCARD и БЕЛКАРТ с помощь онлайн оплаты на нашем сайте SOCKET.BY',
   'https://content.onliner.by/b2b/1378/logotype/42e16d0dee3be5f260035aa6eedcf777.png', 12345677),
  ('imarket.by', 'imarket.by – один из крупнейших интернет-магазинов электроники и бытовой техники Беларуси.

График работы интернет-магазина:
Прием заказов по телефону с 9.00 до 21.00 (без выходных)
Оформить заказ на нашем сайте можно круглосуточно (24/7)
Большинство заказов "сегодня на сегодня" принимаются до 14:00 ',
   'https://content.onliner.by/b2b/581/logotype/8fb54e2efc8fdba82838395ae1ad1f74.jpeg', 12345676);

CREATE TABLE admin (
  user_id BIGINT NOT NULL UNIQUE REFERENCES "user" (id),
  shop_id BIGINT NOT NULL UNIQUE REFERENCES shop (id)
);

INSERT INTO admin (user_id, shop_id) VALUES
  (1, 1),
  (2, 2);

CREATE TABLE customer (
  user_id BIGINT                 NOT NULL UNIQUE REFERENCES "user" (id),
  address CHARACTER VARYING(256) NOT NULL
);

INSERT INTO customer (user_id, address) VALUES
  (3, 'Minsk'),
  (4, 'Moskow');

CREATE TABLE product (
  id          BIGSERIAL PRIMARY KEY,
  category    CHARACTER VARYING(64)  NOT NULL,
  description CHARACTER VARYING(256) NOT NULL UNIQUE,
  image       TEXT                   NOT NULL
);

INSERT INTO product (category, description, image) VALUES
  ('RAM', 'CT8G4DFD824A', 'https://content2.onliner.by/catalog/device/main/9aaae7df3804263a0e938d11ba855435.png'),
  ('RAM', 'M378A1K43CB2-CRC',
   'https://content2.onliner.by/catalog/device/main/8cf80f9b94eddad2b75625feac348eb7.jpeg'),
  ('RAM', 'HX426C16FB2/8', 'https://content2.onliner.by/catalog/device/main/7b569fdb356b18e8b1720c5e97583600.jpeg'),
  ('RAM', 'CT4G4DFS824A', 'https://content2.onliner.by/catalog/device/main/2ce8f76e46691b915ce6e8c911eb00d3.png'),
  ('RAM', 'CMK16GX4M2B3000C15',
   'https://content2.onliner.by/catalog/device/main/68140f5269512b2ed061e93d1f223e31.png'),
  ('RAM', 'BLS2C8G4D26BFSBK', 'https://content2.onliner.by/catalog/device/main/2d2296d405c88bafbf37532b743eae6d.jpeg'),
  ('RAM', 'HX426C15FBK2/8', 'https://content2.onliner.by/catalog/device/main/0c58138bcce7fca35aed691c9ffd2fd1.jpg');

CREATE TABLE shop_product (
  id         BIGSERIAL PRIMARY KEY,
  product_id BIGINT  NOT NULL   REFERENCES product (id),
  shop_id    BIGINT  NOT NULL REFERENCES shop (id),
  quantity   INTEGER NOT NULL,
  price      INTEGER NOT NULL,
  version    BIGINT  NOT NULL
);

INSERT INTO shop_product (product_id, shop_id, quantity, price, version) VALUES
  (1, 1, 5, 176, 0),
  (1, 2, 5, 171, 0),
  (1, 3, 5, 221, 0),
  (2, 1, 5, 183, 0),
  (2, 2, 5, 184, 0),
  (2, 3, 5, 226, 0);

CREATE TABLE option (
  id    BIGSERIAL PRIMARY KEY,
  name  CHARACTER VARYING(64)  NOT NULL,
  value CHARACTER VARYING(128) NOT NULL
);

CREATE TABLE option_product (
  product_id BIGINT NOT NULL   REFERENCES product (id),
  option_id  BIGINT NOT NULL   REFERENCES option (id)
);

CREATE TABLE review (
  id      BIGSERIAL PRIMARY KEY,
  text    CHARACTER VARYING(512) NOT NULL,
  rating  INTEGER                NOT NULL,
  date    DATE                   NOT NULL,
  user_id BIGINT                 NOT NULL REFERENCES "user" (id)
);

CREATE TABLE review_product (
  product_id BIGINT NOT NULL REFERENCES product (id),
  review_id  BIGINT NOT NULL REFERENCES review (id)
);

CREATE TABLE review_shop (
  shop_id   BIGINT NOT NULL REFERENCES shop (id),
  review_id BIGINT NOT NULL REFERENCES review (id)
);

CREATE TABLE orders (
  id            BIGSERIAL PRIMARY KEY,
  user_id       BIGINT    NOT NULL REFERENCES "user" (id),
  order_time    TIMESTAMP NOT NULL,
  delivery_time TIMESTAMP NOT NULL
);

CREATE TABLE product_order (
  id         BIGSERIAL PRIMARY KEY,
  product_id BIGINT  NOT NULL   REFERENCES product (id),
  order_id   BIGINT  NOT NULL REFERENCES orders (id),
  shop_id    BIGINT  NOT NULL REFERENCES shop (id),
  quantity   INTEGER NOT NULL
);

SELECT
  p.id,
  description,
  category,
  image
FROM shop_database.shop.product p
  INNER JOIN shop_product s2
    ON p.id = s2.product_id
GROUP BY p.id
ORDER BY min(s2.price) DESC;
