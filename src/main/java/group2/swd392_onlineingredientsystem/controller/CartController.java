package group2.swd392_onlineingredientsystem.controller;
import group2.swd392_onlineingredientsystem.model.Ingredient;
import group2.swd392_onlineingredientsystem.repository.IIngredientRepository;
import group2.swd392_onlineingredientsystem.service.ICartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartController {

    private final ICartService cartService;
    private final IIngredientRepository ingredientRepository;

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        model.addAttribute("cartItems", cartService.getCart(session));
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("totalAmount", cartService.calculateTotal(session));

        return "cart"; // cart.html
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam int ingredientId,
                            @RequestParam int quantity,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElse(null);
        if (ingredient != null) {
            cartService.addToCart(session, ingredient, quantity);
            redirectAttributes.addFlashAttribute("message", "✔️ " + ingredient.getName() + " added to cart");
        } else {
            redirectAttributes.addFlashAttribute("error", "❌ Không tìm thấy nguyên liệu.");
        }
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam int ingredientId,
                             @RequestParam int quantity,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        cartService.updateQuantity(session, ingredientId, quantity);
        redirectAttributes.addFlashAttribute("message", "Updated");
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam int ingredientId,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        cartService.removeFromCart(session, ingredientId);
        redirectAttributes.addFlashAttribute("message", "Deleted");
        return "redirect:/cart";
    }
}
