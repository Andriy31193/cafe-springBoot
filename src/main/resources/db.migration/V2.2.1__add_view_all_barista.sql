CREATE OR REPLACE VIEW barista_information AS
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
        s.position = 'barista';