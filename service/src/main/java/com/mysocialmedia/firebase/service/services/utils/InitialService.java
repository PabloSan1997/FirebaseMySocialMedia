package com.mysocialmedia.firebase.service.services.utils;


import com.mysocialmedia.firebase.service.models.entities.RoleEntity;
import com.mysocialmedia.firebase.service.repositories.RoleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitialService {
    @Autowired
    private RoleRespository roleRespository;
    @Value("${server.port}")
    private Integer port;

    public void  generateInformation(){
        String[] rolesNames = {"USER", "ADMIN"};
        List<RoleEntity> roles = new ArrayList<>();

        for(String name:rolesNames){
            if(roleRespository.findByName(name).isEmpty())
                roles.add(RoleEntity.builder().name(name).build());
        }

        if(!roles.isEmpty())
            roleRespository.saveAll(roles);

        System.out.println("\nPort: "+port+"\n");
    }
}
