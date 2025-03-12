package es.santander.ascender.tienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.santander.ascender.tienda.model.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    
    Optional<Articulo> findByCodigoBarras(String codigoBarras); 
    //Permite buscar un artículo por su código de barras

    boolean existsByCodigoBarras(String codigoBarras);
    //Verifica si un artículo ya está registrado en la base de datos
    }
