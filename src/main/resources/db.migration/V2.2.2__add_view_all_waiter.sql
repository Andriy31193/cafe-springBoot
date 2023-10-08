CREATE OR REPLACE VIEW waiter_information AS
SELECT
    s.id AS waiter_id,
    s.full_name AS waiter_full_name,
    s.phone AS waiter_phone,
    s.email AS waiter_email,
    s.position AS waiter_position,
    c.id AS cafe_id,
    c.name AS cafe_name
FROM
    staff s
        JOIN
    cafes c ON s.cafe_id = c.id
WHERE
        s.position = 'waiter';