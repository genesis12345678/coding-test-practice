SELECT fi.id AS ID, fni.fish_name AS FISH_NAME, fi.length AS LENGTH

FROM fish_info fi
    JOIN fish_name_info fni ON fi.fish_type = fni.fish_type
    
WHERE fi.fish_type IN ( 
                        SELECT fish_type
                        FROM fish_info
                        GROUP BY fish_type
                        HAVING length = MAX(length)
                      )
ORDER BY fi.id                      