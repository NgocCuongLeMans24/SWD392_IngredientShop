package group2.swd392_onlineingredientsystem.service;

import group2.swd392_onlineingredientsystem.repository.IProductRepository;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final IProductRepository productRepository;

    // Constructor injection
    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id);
    }
}