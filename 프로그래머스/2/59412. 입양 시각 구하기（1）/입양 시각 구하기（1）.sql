SELECT HOUR(datetime) AS HOUR, COUNT(*) AS COUNT
FROM animal_outs

WHERE HOUR(datetime) BETWEEN 9 AND 19

GROUP BY HOUR(datetime)

ORDER BY HOUR(datetime)