CREATE OR REPLACE FUNCTION get_barista_in_all_cafes()
RETURNS TABLE (
    barista_id INTEGER,
    barista_full_name VARCHAR(255),
    barista_phone VARCHAR(255),
    barista_email VARCHAR(255),
    barista_position VARCHAR(255),
    cafe_id INTEGER,
    cafe_name VARCHAR(255)
)
AS $$
BEGIN
RETURN QUERY
SELECT
    s.id AS barista_id,
    s.full_name AS barista_full_name,
    s.phone AS barista_phone,
    s.email AS barista_email,
    s.position AS barista_position,
    c.id AS cafe_id,
    c.name AS cafe_name
FROM
    staff s
        JOIN
    cafes c ON s.cafe_id = c.id
WHERE
        s.position = 'barista'
GROUP BY
    s.id, s.full_name, s.phone, s.email, s.position, c.id, c.name
HAVING
        COUNT(DISTINCT c.id) = (SELECT COUNT(*) FROM cafes);
END;
$$ LANGUAGE plpgsql;