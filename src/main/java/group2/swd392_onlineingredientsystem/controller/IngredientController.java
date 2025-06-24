    package group2.swd392_onlineingredientsystem.controller;

    import group2.swd392_onlineingredientsystem.model.Category;
    import group2.swd392_onlineingredientsystem.model.Ingredient;
    import group2.swd392_onlineingredientsystem.repository.ICategoryRepository;
    import group2.swd392_onlineingredientsystem.service.IngredientService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.domain.Sort;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;

    @Controller
    @RequestMapping("/ingredients")
    public class IngredientController {
        @Autowired
        private IngredientService ingredientService;
        @Autowired
        private ICategoryRepository categoryRepository;

        @GetMapping
        public String listIngredients(
                @RequestParam(value = "page", defaultValue = "0") int page,
                @RequestParam(value = "size", defaultValue = "10") int size,
                @RequestParam(value = "sort", defaultValue = "name") String sort,
                @RequestParam(value = "dir", defaultValue = "asc") String dir,
                @RequestParam(value = "keyword", required = false) String keyword,
                @RequestParam(value = "categoryId", required = false) Integer categoryId,
                Model model) {
            Pageable pageable = PageRequest.of(page, size, dir.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending());
            Page<Ingredient> ingredientPage = ingredientService.searchAndFilter(keyword, categoryId, pageable);
            List<Category> categories = categoryRepository.findAll();
            model.addAttribute("ingredientPage", ingredientPage);
            model.addAttribute("categories", categories);
            model.addAttribute("keyword", keyword);
            model.addAttribute("categoryId", categoryId);
            model.addAttribute("sort", sort);
            model.addAttribute("dir", dir);
            return "ingredient/list";
        }

        @GetMapping("/create")
        public String showCreateForm(Model model) {
            model.addAttribute("ingredient", new Ingredient());
            model.addAttribute("categories", categoryRepository.findAll());
            return "ingredient/form";
        }

        @PostMapping("/save")
        public String saveIngredient(@ModelAttribute Ingredient ingredient) {
            ingredientService.save(ingredient);
            return "redirect:/ingredients";
        }

        @GetMapping("/edit/{id}")
        public String showEditForm(@PathVariable Integer id, Model model) {
            Optional<Ingredient> ingredient = ingredientService.findById(id);
            if (ingredient.isPresent()) {
                model.addAttribute("ingredient", ingredient.get());
                model.addAttribute("categories", categoryRepository.findAll());
                return "ingredient/form";
            }
            return "redirect:/ingredients";
        }

        @PostMapping("/update/{id}")
        public String updateIngredient(@PathVariable Integer id, @ModelAttribute Ingredient ingredient) {
            ingredientService.update(id, ingredient);
            return "redirect:/ingredients";
        }

        @GetMapping("/delete/{id}")
        public String deleteIngredient(@PathVariable Integer id) {
            ingredientService.delete(id);
            return "redirect:/ingredients";
        }
    }