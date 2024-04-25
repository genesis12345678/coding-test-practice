SELECT ii.INGREDIENT_TYPE, SUM(fh.total_order) AS TOTAL_ORDER
FROM first_half fh
    JOIN icecream_info ii ON fh.flavor = ii.flavor

GROUP BY ii.ingredient_type

ORDER BY TOTAL_ORDER