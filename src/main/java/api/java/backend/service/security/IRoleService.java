package api.java.backend.service.security;

import api.java.backend.domain.security.Role;

public interface IRoleService {
    Role findByName(String name);
    void register(Role rol);
}
