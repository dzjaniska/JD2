DROP SCHEMA shop CASCADE;

CREATE SCHEMA shop;

SET SEARCH_PATH TO shop;

CREATE TABLE user_info (
  id          BIGSERIAL PRIMARY KEY,
  name        CHARACTER VARYING(128) NOT NULL,
  surname     CHARACTER VARYING(128),
  second_name CHARACTER VARYING(128)
);

CREATE TABLE "user" (
  id           BIGSERIAL PRIMARY KEY,
  login        CHARACTER VARYING(128) NOT NULL UNIQUE,
  password     CHARACTER VARYING(64)  NOT NULL,
  role         CHARACTER VARYING(32)  NOT NULL,
  user_info_id BIGINT                 NOT NULL REFERENCES user_info (id)
);

CREATE TABLE shop (
  id          BIGSERIAL PRIMARY KEY,
  name        CHARACTER VARYING(64)  NOT NULL UNIQUE,
  description CHARACTER VARYING(512) NOT NULL
);

CREATE TABLE admin (
  user_id BIGINT NOT NULL UNIQUE REFERENCES "user" (id),
  shop_id BIGINT NOT NULL UNIQUE REFERENCES shop (id)
);

CREATE TABLE customer (
  user_id BIGINT                 NOT NULL UNIQUE REFERENCES "user" (id),
  address CHARACTER VARYING(256) NOT NULL
);

CREATE TABLE courier (
  user_id  BIGINT                 NOT NULL UNIQUE REFERENCES "user" (id),
  car_type CHARACTER VARYING(256) NOT NULL
);

CREATE TABLE product (
  id          BIGSERIAL PRIMARY KEY,
  category    CHARACTER VARYING(64)  NOT NULL,
  description CHARACTER VARYING(256) NOT NULL,
  image       TEXT                   NOT NULL
);

CREATE TABLE shop_product (
  id         BIGSERIAL PRIMARY KEY,
  product_id BIGINT  NOT NULL   REFERENCES product (id),
  shop_id    BIGINT  NOT NULL REFERENCES shop (id),
  quantity   INTEGER NOT NULL,
  price      INTEGER NOT NULL
);

CREATE TABLE option (
  id    BIGSERIAL PRIMARY KEY,
  name  CHARACTER VARYING(64)  NOT NULL,
  value CHARACTER VARYING(128) NOT NULL
);

CREATE TABLE option_shop (
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
  user_id       BIGINT                NOT NULL REFERENCES "user" (id),
  order_time    TIMESTAMP             NOT NULL,
  delivery_time TIMESTAMP             NOT NULL,
  status        CHARACTER VARYING(16) NOT NULL
);

CREATE TABLE product_order (
  id         BIGSERIAL PRIMARY KEY,
  product_id BIGINT  NOT NULL   REFERENCES product (id),
  order_id   BIGINT  NOT NULL REFERENCES orders (id),
  quantity   INTEGER NOT NULL
);

CREATE TABLE route (
  id         BIGSERIAL PRIMARY KEY,
  courier_id BIGINT                NOT NULL REFERENCES "user" (id),
  date       DATE                  NOT NULL,
  status     CHARACTER VARYING(16) NOT NULL
);

CREATE TABLE order_route (
  order_id BIGINT NOT NULL REFERENCES orders (id),
  route_id BIGINT NOT NULL REFERENCES route (id)
);