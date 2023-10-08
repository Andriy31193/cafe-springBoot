CREATE OR REPLACE VIEW all_desserts AS
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
    cafes c ON d.cafe_id = c.id;