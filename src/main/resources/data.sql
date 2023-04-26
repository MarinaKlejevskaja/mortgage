-- DROP TABLE IF EXISTS applications, users, constants CASCADE;
CREATE TABLE IF NOT EXISTS users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR(20)  NOT NULL
);

CREATE TABLE IF NOT EXISTS applications
(
    id                    BIGSERIAL PRIMARY KEY,
    user_id               BIGINT         NOT NULL,
    monthly_income        NUMERIC(12, 2) NOT NULL,
    obligations           boolean NOT NULL,
    mortgage_loans        NUMERIC(12, 2),
    consumer_loans        NUMERIC(12, 2),
    leasing_amount        NUMERIC(12, 2),
    credit_card_limit     NUMERIC(12, 2),
    real_estate_price     NUMERIC(12, 2) NOT NULL,
    down_payment          NUMERIC(12, 2) NOT NULL,
    loan_amount           NUMERIC(12, 2) NOT NULL,
    loan_term             INTEGER        NOT NULL,
    interest_rate_margin  NUMERIC(5, 4)  NOT NULL,
    interest_rate_euribor NUMERIC(5, 4)  NOT NULL,
    euribor_term          INTEGER        NOT NULL,
    payment_schedule_type VARCHAR(255)   NOT NULL,
    children_amount       INTEGER        NOT NULL,
    applicants_amount     INTEGER        NOT NULL,
    application_status    VARCHAR(255)   NOT NULL,
    CONSTRAINT fk_applications_users
        FOREIGN KEY (user_id)
            REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS constants
(
    id                                 SERIAL PRIMARY KEY,
    min_loan_term                      INT   NOT NULL,
    max_loan_term                      INT   NOT NULL,
    max_num_of_applicants              FLOAT NOT NULL,
    loan_amount_percentage             FLOAT NOT NULL,
    interest_rate_margin               FLOAT NOT NULL,
    max_kids                           INT   NOT NULL,
    min_kids                           INT   NOT NULL,
    max_monthly_obligations_percentage FLOAT NOT NULL
);

INSERT INTO constants (id,
                       min_loan_term,
                       max_loan_term,
                       max_num_of_applicants,
                       loan_amount_percentage,
                       interest_rate_margin,
                       max_kids,
                       min_kids,
                       max_monthly_obligations_percentage)
VALUES (1,
        1,
        30,
        2,
        0.85,
        0.025,
        10,
        0,
        0.4)
ON CONFLICT (id) DO NOTHING;

INSERT INTO users (first_name, last_name, email, password, role)
VALUES ('adminname', 'adminsurname', 'admin@admin.lt', '$2a$12$I0f8HXsBiaPwmfB2xV8p8e1fzFWDhxs/BR51RWnAceL7MbGb86dqK',
        'ADMIN')
ON CONFLICT (email) DO NOTHING;
-- admin