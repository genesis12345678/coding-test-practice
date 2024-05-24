SELECT WAREHOUSE_ID, WAREHOUSE_NAME, ADDRESS, IFNULL(FREEZER_YN, 'N')
FROM FOOD_WAREHOUSE
WHERE LEFT(ADDRESS, 3) = '경기도'
ORDER BY WAREHOUSE_ID