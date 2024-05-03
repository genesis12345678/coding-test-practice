SELECT CATEGORY, price AS MAX_PRICE, PRODUCT_NAME
FROM food_product
WHERE price IN(SELECT MAX(price)
               FROM food_product
               GROUP BY category)
           AND category IN('과자', '국', '김치', '식용유')
ORDER BY price DESC;