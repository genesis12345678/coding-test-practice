SELECT CAR_ID, CASE WHEN CAR_ID IN (SELECT CAR_ID 
                               FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY 
                               WHERE '2022-10-16' BETWEEN start_date AND end_date) THEN '대여중'
                    ELSE '대여 가능'
                       END AS AVALIABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY   
GROUP BY car_id
ORDER BY car_id DESC;