import com.qpetit.entities.Category;
import com.qpetit.entities.Menu;
import com.qpetit.entities.MenuFactory;
import com.qpetit.entities.Supplier;

import java.util.Collections;

public class InfantilMenuFactory implements MenuFactory {
    @Override
    public Menu createMenu() {
        return new Menu(
                1,
                "Menú Infantil",
                Category.INFANTIL,
                true,
                new Supplier(101, "Proveedor Infantil", "12345"),
                1,
                true,  // entrada
                true,  // plato principal
                true,  // postre
                "Incluye juguete"
        );
    }
}
