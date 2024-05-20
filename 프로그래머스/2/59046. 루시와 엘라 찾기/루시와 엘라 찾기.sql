SELECT ANIMAL_ID, NAME, SEX_UPON_INTAKE
FROM animal_ins
WHERE name IN ('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty')
ORDER BY animal_id