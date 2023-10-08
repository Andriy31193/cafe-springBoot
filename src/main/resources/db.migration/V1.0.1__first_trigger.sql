CREATE OR REPLACE FUNCTION archive_beverage()
RETURNS TRIGGER AS $$
BEGIN
INSERT INTO beverages_archive (id, name_english, name_ukrainian, price, rating, cafe_id)
VALUES (OLD.id, OLD.name_english, OLD.name_ukrainian, OLD.price, OLD.rating, OLD.cafe_id);

RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER on_beverage_delete
    BEFORE DELETE ON beverages
    FOR EACH ROW
    EXECUTE FUNCTION archive_beverage();