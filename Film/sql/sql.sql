SELECT *
FROM ((SELECT film_id, theatre_id, video_hall, time, platform_id, price FROM show_infos WHERE film_id = '?' AND theatre_id = '?' AND DATE_FORMAT(time,'%Y-%m-%d') = '?') AS t1 INNER JOIN platforms ON t1.platform_id = platforms.id) INNER JOIN films ON t1.film_id = films.id
ORDER BY t1.time

SELECT films.id, films.name, platform_films.description, platform_films.score, platform_films.type, platforms.id, platforms.platform
FROM films, platform_films, platforms
WHERE films.id = 1 AND platform_films.film_id = films.id AND platform_films.platform_id = platforms.id

SELECT theatres.id, theatres.name, theatres.phone, theatres.address, COUNT(*)
FROM (SELECT theatre_id FROM show_infos WHERE film_id = 1 AND DATE_FORMAT(time,'%Y-%m-%d') = '?') AS t INNER JOIN theatres ON t.theatre_id = theatres.id
GROUP BY theatres.id, theatres.name, theatres.phone, theatres.address