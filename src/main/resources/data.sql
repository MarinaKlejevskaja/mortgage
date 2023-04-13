CREATE TABLE IF NOT EXISTS applications
(
    id SERIAL PRIMARY KEY,
    monthly_income BIGINT NOT NULL,
    monthly_obligations BIGINT NOT NULL,
    real_estate_price BIGINT NOT NULL,
    down_payment INT NOT NULL,
    loan_amount BIGINT NOT NULL,
    loan_term INT NOT NULL,
    interest_rate_margin FLOAT NOT NULL,
    interest_rate_euribor FLOAT NOT NULL,
    payment_schedule_type VARCHAR(255) NOT NULL,
    children_amount INT NOT NULL,
    applicants_amount INT NOT NULL,
    application_status VARCHAR(255) NOT NULL
    );

INSERT INTO applications (id,
                          monthly_income,
                          monthly_obligations,
                          real_estate_price,
                          down_payment,
                          loan_amount,
                          loan_term,
                          interest_rate_margin,
                          interest_rate_euribor,
                          payment_schedule_type,
                          children_amount,
                          applicants_amount,
                          application_status)
VALUES (1,
        5000,
        2000,
        200000,
        30000,
        170000,
        20,
        2.5,
        0.5,
        'annuity',
        2,
        2,
        'received')
ON CONFLICT (id) DO NOTHING;

CREATE TABLE IF NOT EXISTS constants (
                           id SERIAL PRIMARY KEY,
                           min_loan_term INT NOT NULL,
                           max_loan_term INT NOT NULL,
                           max_num_of_applicants FLOAT NOT NULL,
                           loan_amount_percentage FLOAT NOT NULL,
                           interest_rate_margin FLOAT NOT NULL,
                           max_kids INT NOT NULL,
                           min_kids INT NOT NULL,
                           max_monthly_obligations_percentage FLOAT NOT NULL,
                           application_status VARCHAR(255) NOT NULL
);

INSERT INTO constants (
    id,
    min_loan_term,
    max_loan_term,
    max_num_of_applicants,
    loan_amount_percentage,
    interest_rate_margin,
    max_kids,
    min_kids,
    max_monthly_obligations_percentage,
    application_status
) VALUES (
             1,
             1,
             30,
             2,
             0.85,
             0.025,
             10,
             0,
             0.4,
             'received'
         ) ON CONFLICT (id) DO NOTHING;

