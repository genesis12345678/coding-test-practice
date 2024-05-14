SELECT p.PRODUCT_CODE, (SUM(os.sales_amount) * p.price) AS SALES
FROM product p
    JOIN offline_sale os ON p.product_id = os.product_id
GROUP BY os.product_id        
ORDER BY SALES DESC, p.product_code
