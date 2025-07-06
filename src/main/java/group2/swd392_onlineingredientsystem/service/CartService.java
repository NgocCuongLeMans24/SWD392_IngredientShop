package group2.swd392_onlineingredientsystem.service;

import group2.swd392_onlineingredientsystem.model.CartItem;
import group2.swd392_onlineingredientsystem.model.Ingredient;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService {

    private static final String CART_SESSION_KEY = "cart";

    @Override
    public List<CartItem> getCart(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    @Override
    public void addToCart(HttpSession session, Ingredient ingredient, int quantity) {
        List<CartItem> cart = getCart(session);
        for (CartItem item : cart) {
            if (item.getIngredient().getIngredientId().equals(ingredient.getIngredientId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cart.add(new CartItem(ingredient, quantity));
    }

    @Override
    public void updateQuantity(HttpSession session, int ingredientId, int quantity) {
        List<CartItem> cart = getCart(session);
        for (CartItem item : cart) {
            if (item.getIngredient().getIngredientId() == ingredientId) {
                item.setQuantity(quantity);
                break;
            }
        }
    }

    @Override
    public void removeFromCart(HttpSession session, int ingredientId) {
        List<CartItem> cart = getCart(session);
        cart.removeIf(item -> item.getIngredient().getIngredientId() == ingredientId);
    }
    @Override
    public BigDecimal calculateTotal(HttpSession session) {
        List<CartItem> cart = getCart(session);
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : cart) {
            total = total.add(item.getTotalPrice()); // đã là BigDecimal
        }

        return total;
    }



    @Override
    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }
}

