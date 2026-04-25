-- ============================================================
-- Tabla: games  (reemplaza movies)
-- Base de datos: SQL Server  |  DB: EduSmartDB
-- ============================================================

-- 1. Eliminar tabla antigua si migras de movies
-- DROP TABLE IF EXISTS movies;

CREATE TABLE games (
    id          BIGINT          IDENTITY(1,1) PRIMARY KEY,
    title       NVARCHAR(255)   NOT NULL,
    description NVARCHAR(1000),
    genre       NVARCHAR(255)   NOT NULL,
    platform    NVARCHAR(255)   NOT NULL,
    developer   NVARCHAR(255)   NOT NULL,
    release_date DATE,
    rating      DECIMAL(4,1),
    price       DECIMAL(8,2),
    image_url   NVARCHAR(500),
    available   BIT             DEFAULT 1
);

-- ============================================================
-- Seed Data: videojuegos reales populares
-- ============================================================
INSERT INTO games (title, description, genre, platform, developer, release_date, rating, price, image_url, available)
VALUES
('GTA V',
 'Mundo abierto criminal en Los Santos. Juego más vendido de la historia.',
 'Action-Adventure', 'PC, PS5, Xbox Series X', 'Rockstar Games',
 '2013-09-17', 9.7, 29.99, '', 1),

('Minecraft',
 'Construcción y supervivencia en un mundo infinito de bloques.',
 'Sandbox', 'PC, PS5, Xbox Series X, Switch, Mobile', 'Mojang Studios',
 '2011-11-18', 9.5, 26.95, '', 1),

('Elden Ring',
 'RPG de acción en mundo abierto creado por FromSoftware y George R.R. Martin.',
 'Action RPG', 'PC, PS5, Xbox Series X', 'FromSoftware',
 '2022-02-25', 9.6, 59.99, '', 1),

('FIFA 26',
 'El simulador de fútbol más popular del mundo con modo carrera y Ultimate Team.',
 'Sports', 'PC, PS5, Xbox Series X, Switch', 'EA Sports',
 '2025-09-26', 8.2, 69.99, '', 1),

('Call of Duty: Black Ops 6',
 'Shooter táctico multijugador con campaña épica y modo zombies.',
 'FPS', 'PC, PS5, Xbox Series X', 'Treyarch / Activision',
 '2024-10-25', 8.5, 69.99, '', 1),

('God of War Ragnarök',
 'Kratos y Atreus se enfrentan a Ragnarök en la mitología nórdica.',
 'Action-Adventure', 'PS5, PS4', 'Santa Monica Studio',
 '2022-11-09', 9.8, 59.99, '', 1),

('Fortnite',
 'Battle Royale gratuito con construcción, colaboraciones y eventos en vivo.',
 'Battle Royale', 'PC, PS5, Xbox Series X, Switch, Mobile', 'Epic Games',
 '2017-07-25', 8.0, 0.00, '', 1);
