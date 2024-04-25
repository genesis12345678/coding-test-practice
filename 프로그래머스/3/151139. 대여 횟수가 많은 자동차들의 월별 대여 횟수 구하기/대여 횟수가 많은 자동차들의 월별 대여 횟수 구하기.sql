SELECT MONTH(start_date) AS MONTH, CAR_ID, COUNT(*) AS RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY 

WHERE YEAR(start_date) = 2022 AND MONTH(start_date) BETWEEN 8 AND 10
        AND car_id IN (SELECT car_id
                       FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                       WHERE YEAR(start_date) = 2022 AND MONTH(start_date) BETWEEN 8 AND 10
                       GROUP BY car_id
                            HAVING COUNT(*) >= 5
                      )

GROUP BY MONTH, car_id
    HAVING RECORDS >= 0

ORDER BY MONTH, car_id DESC