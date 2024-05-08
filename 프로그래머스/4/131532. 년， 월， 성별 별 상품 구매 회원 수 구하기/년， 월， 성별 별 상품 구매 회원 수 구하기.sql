SELECT YEAR(os.sales_date) AS YEAR, 
        MONTH(os.sales_date) AS MONTH, 
        ui.gender AS GENDER, 
        COUNT(DISTINCT ui.user_id) AS USERS
        
FROM user_info ui
    JOIN online_sale os ON ui.user_id = os.user_id
    
GROUP BY YEAR(os.sales_date), MONTH(os.sales_date), ui.gender
    HAVING ui.gender IS NOT NULL
    
ORDER BY YEAR(os.sales_date), MONTH(os.sales_date), ui.gender