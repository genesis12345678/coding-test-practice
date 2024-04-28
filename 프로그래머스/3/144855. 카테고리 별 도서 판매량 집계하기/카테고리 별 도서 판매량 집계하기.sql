SELECT b.CATEGORY, SUM(bs.sales) AS TOTAL_SALES
FROM book b
    JOIN book_sales bs ON b.book_id = bs.book_id
WHERE YEAR(bs.sales_date) = 2022 AND MONTH(bs.sales_date) = 1

GROUP BY b.category

ORDER BY b.category