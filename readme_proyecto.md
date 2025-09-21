# Cambios realizados



agregamos el archivo ´src/main/resources/db.properties´ para la conexion
aunque no sea lo recomendable se solicitaba, cambiamos el gitignore para agregarlo



### cambiamos el script de MySQL para que tenga el campo Problema.descripcion

```sql
-- Crear la base de datos
CREATE DATABASE EcoActivistasDB;
USE EcoActivistasDB;

-- Tabla Cliente
CREATE TABLE Cliente (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(150),
    telefonos VARCHAR(50)
);

-- Tabla Problema
CREATE TABLE Problema (
    idProblema INT AUTO_INCREMENT PRIMARY KEY,
    fch_ini DATE NOT NULL,
    fch_fin DATE,
    estado ENUM('pendiente','concluido','cancelado') NOT NULL,
    descripcion VARCHAR(255),
    idCliente INT NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)
);

-- Tabla Activista
CREATE TABLE Activista (
    idActivista INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(50),
    fchIngreso DATE NOT NULL
);

-- Tabla intermedia Problema_Activista (NM)
CREATE TABLE Problema_Activista (
    idProblema INT NOT NULL,
    idActivista INT NOT NULL,
    PRIMARY KEY (idProblema, idActivista),
    FOREIGN KEY (idProblema) REFERENCES Problema(idProblema),
    FOREIGN KEY (idActivista) REFERENCES Activista(idActivista)
);
```

> **Nota**: por lo tanto adaptamos ´src/main/java/com/mycompany/ecoActivistas/model/Problema´

<br>

> **Nota**: por lo tanto adaptamos ´src/main/java/com/mycompany/ecoActivistas/dao/ProblemaDAO´

<br>

completamos los CRUD para Activistas junto con su UI al igual para la relacion N:M Activistas - Problemas

