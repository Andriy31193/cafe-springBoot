CREATE OR REPLACE FUNCTION add_to_blacklist(staff_id INTEGER, reason VARCHAR(255))
RETURNS VOID
AS $$
BEGIN
    INSERT INTO blacklist (staff_id, reason) VALUES (staff_id, reason);
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION is_blacklisted(p_staff_id INTEGER)
RETURNS BOOLEAN
AS $$
DECLARE
    v_is_blacklisted BOOLEAN;
BEGIN
    SELECT EXISTS (SELECT 1 FROM blacklist WHERE staff_id = p_staff_id) INTO v_is_blacklisted;
    RETURN v_is_blacklisted;
END;
$$ LANGUAGE plpgsql;