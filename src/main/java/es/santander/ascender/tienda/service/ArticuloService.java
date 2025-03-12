package es.santander.ascender.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.tienda.model.Articulo;
import es.santander.ascender.tienda.repository.ArticuloRepository;
import jakarta.persistence.EntityNotFoundException;

@Transactional
@Service
public class ArticuloService {

    private final ArticuloRepository articuloRepository;

    @Autowired
    public ArticuloService(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    public Articulo crearArticulo(Articulo articulo) {
        if (articuloRepository.existsByCodigoBarras(articulo.getCodigoBarras())) {
            throw new IllegalArgumentException("El artículo con este código de barras ya existe.");
        //revisar este codigo porque no se si funcione
        }
        return articuloRepository.save(articulo);
    }

    public List<Articulo> obtenerTodos() {
        return articuloRepository.findAll();
    }

    public Optional<Articulo> obtenerPorId(Long id) {
        return articuloRepository.findById(id);
    }

    public Optional<Articulo> obtenerPorCodigoBarras(String codigoBarras) {
        return articuloRepository.findByCodigoBarras(codigoBarras);
    }

    public Articulo actualizarArticulo(Long id, Articulo articuloActualizado) {
        return articuloRepository.findById(id).map(articulo -> {
            articulo.setNombre(articuloActualizado.getNombre());
            articulo.setDescripcion(articuloActualizado.getDescripcion());
            articulo.setCategoria(articuloActualizado.getCategoria());
            articulo.setPrecioVenta(articuloActualizado.getPrecioVenta());
            articulo.setStock(articuloActualizado.getStock());
            return articuloRepository.save(articulo);
        }).orElseThrow(() -> new EntityNotFoundException("Artículo no encontrado"));
    }

    public void eliminarArticulo(Long id) {
        Articulo articulo = articuloRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artículo no encontrado"));
        // Aquí podríamos marcarlo como "borrado" en lugar de eliminarlo físicamente
        articuloRepository.delete(articulo);
    }
}


