INSERT INTO products (sku, name, description, ean, price, stock)
SELECT 'SKU-' || LPAD(CAST(X AS VARCHAR), 5, '0'),
       'Product ' || X,
       'Description for product ' || X,
       7890000000000 + X,
       ROUND(RAND() * 1000 + 1, 2),
       CAST(RAND() * 500 AS INT)
FROM SYSTEM_RANGE(1, 10000);
