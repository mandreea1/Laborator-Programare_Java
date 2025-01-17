CREATE TABLE eveniment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nume VARCHAR(255) NOT NULL,
    data DATE NOT NULL,
    locatie VARCHAR(255) NOT NULL
);
INSERT INTO eveniment (nume, data, locatie) VALUES
('Concert Rock', '2025-05-20', 'Stadionul Central'),
('Expozitie Arta', '2025-06-15', 'Muzeul de Artă'),
('Festival Gastronomic', '2025-07-01', 'Parcul Municipal');