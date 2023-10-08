CREATE OR REPLACE FUNCTION archive_dessert()
RETURNS TRIGGER AS $$
BEGIN
INSERT INTO desserts_archive (id, name_english, name_ukrainian, price, rating, cafe_id)
VALUES (OLD.id, OLD.name_english, OLD.name_ukrainian, OLD.price, OLD.rating, OLD.cafe_id);

RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER on_dessert_delete
    BEFORE DELETE ON desserts
    FOR EACH ROW
    EXECUTE FUNCTION archive_dessert();