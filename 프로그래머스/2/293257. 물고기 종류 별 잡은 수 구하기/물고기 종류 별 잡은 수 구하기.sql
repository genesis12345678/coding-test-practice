SELECT COUNT(*) AS FISH_COUNT, fni.FISH_NAME
FROM fish_info fi
    JOIN fish_name_info fni ON fi.fish_type = fni.fish_type
GROUP BY fni.FISH_NAME
ORDER BY FISH_COUNT DESC
    