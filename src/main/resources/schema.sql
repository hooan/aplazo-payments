CREATE TABLE input (
                         id   INTEGER      NOT NULL AUTO_INCREMENT,
                         amount DECIMAL NOT NULL,
                         rate INTEGER NOT NULL,
                         terms INTEGER NOT NULL,
                         PRIMARY KEY (id)
);

CREATE TABLE output (
                         id   INTEGER      NOT NULL AUTO_INCREMENT,
                         input_id INTEGER NOT NULL,
                         payment_number DECIMAL NOT NULL,
                         pending_amount DECIMAL NOT NULL,
                         payment_date DATE NOT NULL,
                         PRIMARY KEY (id)
);