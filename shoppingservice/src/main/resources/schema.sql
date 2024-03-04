DROP TABLE IF EXISTS tbl_invoice_items;
DROP TABLE IF EXISTS tbl_invoices;

CREATE TABLE tbl_invoices (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  number_invoice VARCHAR(11) NOT NULL,
  description VARCHAR(250) NOT NULL,
  customer_id BIGINT NOT NULL,
  create_at TIMESTAMP,
  state VARCHAR(250) NULL
);

CREATE TABLE tbl_invoice_items (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  invoice_id BIGINT,
  product_id BIGINT NOT NULL,
  quantity DOUBLE NOT NULL,
  price DOUBLE NOT NULL
);