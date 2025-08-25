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

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    
}
