package api.java.backend.service.security.Impl;

import api.java.backend.domain.security.Role;
import api.java.backend.repository.security.RoleRepository;
import api.java.backend.service.security.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role findByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    public void register(Role rol){

        if(!roleRepository.existsByName(rol.getName())){
            roleRepository.save(rol);
        }
    }
}
