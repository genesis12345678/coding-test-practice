SELECT ANIMAL_ID, NAME
FROM animal_ins
WHERE animal_type = 'Dog' AND NAME LIKE '%el%'
ORDER BY name;