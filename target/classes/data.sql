CREATE TABLE Integrante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    franquia VARCHAR(100) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    funcao VARCHAR(100) NOT NULL
);

-- Tabela de Times
CREATE TABLE Time (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data DATE NOT NULL
);

-- Tabela de Composição de Time (relaciona Time com Integrantes)
CREATE TABLE ComposicaoTime (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_time INT NOT NULL,
    id_integrante INT NOT NULL,
    FOREIGN KEY (id_time) REFERENCES Time(id) ON DELETE CASCADE,
    FOREIGN KEY (id_integrante) REFERENCES Integrante(id) ON DELETE CASCADE
);