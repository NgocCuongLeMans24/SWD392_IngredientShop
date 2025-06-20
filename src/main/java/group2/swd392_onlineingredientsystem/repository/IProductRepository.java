package group2.swd392_onlineingredientsystem.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository {
    List<Product> findAll();

    Product findById(Long id);

    void save(Product product);
}