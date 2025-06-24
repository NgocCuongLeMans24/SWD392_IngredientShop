package group2.swd392_onlineingredientsystem.repository;

import group2.swd392_onlineingredientsystem.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IIngredientRepository extends JpaRepository<Ingredient, Integer>, JpaSpecificationExecutor<Ingredient> {
    // Có thể thêm custom query nếu cần
} 