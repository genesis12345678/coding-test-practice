SELECT SUM(hg.score) AS SCORE, hg.EMP_NO, he.EMP_NAME, he.POSITION, he.EMAIL

FROM hr_employees he
    JOIN hr_grade hg ON he.emp_no = hg.emp_no
    
GROUP BY hg.year, hg.emp_no
    HAVING hg.year = '2022'
    
ORDER BY SCORE DESC
LIMIT 1;