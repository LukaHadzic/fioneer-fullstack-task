CREATE TABLE status (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    status VARCHAR(36) NOT NULL UNIQUE
);

CREATE TABLE class_of_business (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(36) NOT NULL UNIQUE
);

CREATE TABLE accumulation (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(36) UNIQUE NOT NULL,
    class_of_business_id INTEGER NOT NULL,
    status_id INTEGER NOT NULL,
    description VARCHAR(100),
    FOREIGN KEY (class_of_business_id) REFERENCES class_of_business(id),
    FOREIGN KEY (status_id) REFERENCES status(id)
);

CREATE TABLE coverage (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(36) NOT NULL UNIQUE,
    sum_insured NUMERIC NOT NULL,
    class_of_business_id INTEGER NOT NULL,
    accumulation_id INTEGER NOT NULL,
    FOREIGN KEY (class_of_business_id) REFERENCES class_of_business(id),
    FOREIGN KEY (accumulation_id) REFERENCES accumulation(id) ON DELETE CASCADE
);