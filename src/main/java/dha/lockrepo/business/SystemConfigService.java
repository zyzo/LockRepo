package dha.lockrepo.business;

import dha.lockrepo.dao.SystemConfigDAO;

public class SystemConfigService {

    private SystemConfigDAO configDAO;

    public SystemConfigService() {
        configDAO = new SystemConfigDAO();
    }

    public Object get(String key) {
        return configDAO.getProperty(key);
    }
}
