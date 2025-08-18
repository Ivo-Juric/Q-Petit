package entitys;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private int idService;
    private ServiceType serviceType;
    private List<Menu> menus = new ArrayList<>();

    public Service(int idService, List<Menu> menus) {
        this.idService = idService;
        this.menus = menus;
    }
}
