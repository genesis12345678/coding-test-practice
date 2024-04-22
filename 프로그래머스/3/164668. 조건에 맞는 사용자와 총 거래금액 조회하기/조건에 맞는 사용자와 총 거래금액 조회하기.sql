SELECT ugu.USER_ID AS USER_ID, ugu.NICKNAME AS NICKNAME, SUM(ugb.price) AS TOTAL_SALES
FROM used_goods_board ugb
    JOIN used_goods_user ugu    ON ugb.writer_id = ugu.user_id

WHERE ugb.status = 'DONE'

GROUP BY ugb.writer_id 
    HAVING TOTAL_SALES >= 700000
    
ORDER BY TOTAL_SALES