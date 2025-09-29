import com.qpetit.entities.Category;
import com.qpetit.entities.Menu;
import com.qpetit.entities.MenuFactory;
import com.qpetit.entities.Supplier;

public class GourmetMenuFactory implements MenuFactory {
    @Override
    public Menu createMenu() {
        return new Menu(
                2,
                "Menú Gourmet",
                Category.GOURMET,
                false,
                new Supplier(202, "Proveedor Gourmet", "98765"),
                2,
                true,  // entrada
                true,  // plato principal
                true,  // postre
                "Incluye vino premium"
        );
    }
}
