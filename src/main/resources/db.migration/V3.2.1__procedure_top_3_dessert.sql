CREATE OR REPLACE FUNCTION get_top3_desserts()
RETURNS TABLE (
    dessert_id INTEGER,
    dessert_name_english VARCHAR(255),
    dessert_name_ukrainian VARCHAR(255),
    dessert_price DECIMAL,
    dessert_rating DECIMAL,
    cafe_id INTEGER,
    cafe_name VARCHAR(255)
)
AS $$
BEGIN
RETURN QUERY
SELECT
    d.id AS dessert_id,
    d.name_english AS dessert_name_english,
    d.name_ukrainian AS dessert_name_ukrainian,
    d.price AS dessert_price,
    d.rating AS dessert_rating,
    c.id AS cafe_id,
    c.name AS cafe_name
FROM
    desserts d
        JOIN
    cafes c ON d.cafe_id = c.id
ORDER BY
    d.rating DESC
    LIMIT 3;
END;
$$ LANGUAGE plpgsql;