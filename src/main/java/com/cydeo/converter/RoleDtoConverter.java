package com.cydeo.converter;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class RoleDtoConverter implements Converter<String, RoleDTO> { //"2" dropdown sends it as String
//                             view: th:value:${role.id},ex:for manager -->> roleDTO.id: "2", RoleDTO object
    RoleService roleService;

    public RoleDtoConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleDTO convert(String source) { // roleDto.id -->> th:value : "2" returns String

        if(source == null || source.equals("")){ // Select -> "" empty String
            return null;
        }


        //                 findById accepts Long
        return roleService.findById(Long.parseLong(source));
                                    //based on the id

    }


}
