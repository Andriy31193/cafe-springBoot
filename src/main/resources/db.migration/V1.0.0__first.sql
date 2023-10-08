CREATE TABLE IF NOT EXISTS public.cafes (
                                            id SERIAL PRIMARY KEY,
                                            name VARCHAR(255),
    address VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS public.beverages (
                                                id SERIAL PRIMARY KEY,
                                                name_english VARCHAR(255),
    name_ukrainian VARCHAR(255),
    price DECIMAL,
    rating DECIMAL CHECK (rating >= 0 AND rating <= 5),
    cafe_id INTEGER REFERENCES cafes(id)
    );

CREATE TABLE IF NOT EXISTS public.desserts (
                                               id SERIAL PRIMARY KEY,
                                               name_english VARCHAR(255),
    name_ukrainian VARCHAR(255),
    price DECIMAL,
    rating DECIMAL CHECK (rating >= 0 AND rating <= 5),
    cafe_id INTEGER REFERENCES cafes(id)
    );

CREATE TABLE IF NOT EXISTS public.staff (
                                            id SERIAL PRIMARY KEY,
                                            full_name VARCHAR(255),
    phone VARCHAR(255),
    email VARCHAR(255),
    position VARCHAR(255) CHECK (position IN ('barista', 'waiter', 'confectioner')),
    cafe_id INTEGER REFERENCES cafes(id)
    );

CREATE TABLE IF NOT EXISTS public.customers (
                                                id SERIAL PRIMARY KEY,
                                                full_name VARCHAR(255),
    birth_date DATE,
    phone VARCHAR(255),
    email VARCHAR(255),
    discount DECIMAL,
    cafe_id INTEGER REFERENCES cafes(id)
    );

CREATE TABLE IF NOT EXISTS public.staff_work_schedule (
                                                          id SERIAL PRIMARY KEY,
                                                          staff_id INTEGER REFERENCES staff(id),
    day_of_week VARCHAR(255),
    start_time TIME,
    end_time TIME
    );

CREATE TABLE IF NOT EXISTS public.orders (
                                             id SERIAL PRIMARY KEY,
                                             customer_id INTEGER REFERENCES customers(id),
    staff_id INTEGER REFERENCES staff(id),
    date DATE,
    total_amount DECIMAL,
    cafe_id INTEGER REFERENCES cafes(id)
    );


CREATE TABLE IF NOT EXISTS public.beverages_archive (
                                                        id SERIAL PRIMARY KEY,
                                                        name_english VARCHAR(255),
    name_ukrainian VARCHAR(255),
    price DECIMAL,
    rating DECIMAL CHECK (rating >= 0 AND rating <= 5),
    cafe_id INTEGER,
    deleted_at TIMESTAMP DEFAULT current_timestamp
    );

CREATE TABLE IF NOT EXISTS public.desserts_archive (
                                                       id SERIAL PRIMARY KEY,
                                                       name_english VARCHAR(255),
    name_ukrainian VARCHAR(255),
    price DECIMAL,
    rating DECIMAL CHECK (rating >= 0 AND rating <= 5),
    cafe_id INTEGER,
    deleted_at TIMESTAMP DEFAULT current_timestamp
    );

CREATE TABLE IF NOT EXISTS public.employee_transfers (
                                                         id SERIAL PRIMARY KEY,
                                                         employee_id INTEGER REFERENCES staff(id),
    old_cafe_id INTEGER REFERENCES cafes(id),
    new_cafe_id INTEGER REFERENCES cafes(id),
    transfer_date DATE
    );

CREATE TABLE IF NOT EXISTS blacklist (
                                         id SERIAL PRIMARY KEY,
                                         staff_id INTEGER REFERENCES staff(id),
    reason VARCHAR(255),
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );