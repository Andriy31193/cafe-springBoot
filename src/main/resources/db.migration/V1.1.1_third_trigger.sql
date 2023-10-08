CREATE OR REPLACE FUNCTION log_employee_transfer()
RETURNS TRIGGER AS $$
BEGIN
    IF OLD.cafe_id IS DISTINCT FROM NEW.cafe_id THEN
        INSERT INTO employee_transfers (employee_id, old_cafe_id, new_cafe_id, transfer_date)
        VALUES (OLD.id, OLD.cafe_id, NEW.cafe_id, CURRENT_DATE);
END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER on_employee_transfer
    AFTER UPDATE ON staff
    FOR EACH ROW
    EXECUTE FUNCTION log_employee_transfer();