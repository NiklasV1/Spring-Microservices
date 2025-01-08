CREATE TABLE products (
  id uuid PRIMARY KEY,
  name varchar(100) NOT NULL UNIQUE,
  amount bigint NOT NULL,
  price money NOT NULL
);
