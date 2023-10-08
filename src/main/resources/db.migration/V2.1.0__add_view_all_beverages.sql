CREATE OR REPLACE VIEW all_beverages AS
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
    cafes c ON b.cafe_id = c.id;