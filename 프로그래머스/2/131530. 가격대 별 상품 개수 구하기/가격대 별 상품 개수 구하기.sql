SELECT CASE 
            WHEN (0 <= price) AND (price < 10000) THEN 0
            WHEN (10000 <= price) AND (price < 20000) THEN 10000
            WHEN (20000 <= price) AND (price < 30000) THEN 20000
            WHEN (30000 <= price) AND (price < 40000) THEN 30000
            WHEN (40000 <= price) AND (price < 50000) THEN 40000
            WHEN (50000 <= price) AND (price < 60000) THEN 50000
            WHEN (60000 <= price) AND (price < 70000) THEN 60000
            WHEN (70000 <= price) AND (price < 80000) THEN 70000
            WHEN (80000 <= price) AND (price < 90000) THEN 80000
        END
            AS PRICE_GROUP, COUNT(*) AS PRODUCTS
FROM product
GROUP BY PRICE_GROUP
ORDER BY PRICE_GROUP