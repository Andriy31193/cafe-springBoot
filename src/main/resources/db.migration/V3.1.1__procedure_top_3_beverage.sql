CREATE OR REPLACE FUNCTION get_top3_beverages()
RETURNS TABLE (
    beverage_id INTEGER,
    beverage_name_english VARCHAR(255),
    beverage_name_ukrainian VARCHAR(255),
    beverage_price DECIMAL,
    beverage_rating DECIMAL,
    cafe_id INTEGER,
    cafe_name VARCHAR(255)
)
AS $$
BEGIN
RETURN QUERY
SELECT
    b.id AS beverage_id,
    b.name_english AS beverage_name_english,
    b.name_ukrainian AS beverage_name_ukrainian,
    b.price AS beverage_price,
    b.rating AS beverage_rating,
    c.id AS cafe_id,
    c.name AS cafe_name
FROM
    beverages b
        JOIN
    cafes c ON b.cafe_id = c.id
ORDER BY
    b.rating DESC
    LIMIT 3;
END;
$$ LANGUAGE plpgsql;