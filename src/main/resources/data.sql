CREATE TABLE carti (
    id INT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(20),
    titlul VARCHAR(255),
    autorul VARCHAR(255)
);


INSERT INTO carti (isbn, titlul, autorul) VALUES
('ISBN1', '1984', 'George Orwell'),
('ISBN2', 'Sapiens', 'Yuval Noah Harari'),
('ISBN3', 'De veghe în lanul de secară', 'J.D. Salinger');