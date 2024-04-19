SELECT child.id AS ID, child.genotype AS GENOTYPE, parent.genotype AS PARENT_GENOTYPE
FROM ecoli_data child
 JOIN ecoli_data parent ON child.parent_id = parent.id

WHERE child.genotype & parent.genotype = parent.genotype
 
ORDER BY child.id;
    