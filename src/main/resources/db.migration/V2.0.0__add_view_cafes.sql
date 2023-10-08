CREATE OR REPLACE VIEW cafe_information AS
SELECT
    c.id AS cafe_id,
    c.name AS cafe_name,
    c.address AS cafe_address,
    COUNT(s.id) AS total_staff
FROM
    cafes c
        LEFT JOIN
    staff s ON c.id = s.cafe_id
GROUP BY
    c.id, c.name, c.address;