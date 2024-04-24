SELECT b.AUTHOR_ID, a.AUTHOR_NAME, b.CATEGORY, SUM(b.price * bs.sales) AS TOTAL_SALES
FROM book b
    JOIN author a ON b.author_id = a.author_id
    JOIN book_sales bs ON b.book_id = bs.book_id

WHERE YEAR(bs.sales_date) = 2022 AND MONTH(bs.sales_date) = 1

GROUP BY b.author_id, b.category
ORDER BY b.author_id, b.category DESC;