package com.cydeo.boostrap;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.CrudService;
import com.cydeo.service.RoleService;
import org.springframework.boot.CommandLineRunner;

public class DataGenerator implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception { // when you executed application, it will be run implicitly

        //Initially

        //This run method will execute FIRST BEFORE ANYTHING when you start the application

        // create some rules and put it in the DB(map)

        RoleDTO adminRole = new RoleDTO(1L,"Admin");
        RoleDTO managerRole = new RoleDTO(2L,"Manager");
        RoleDTO employeeRole = new RoleDTO(3L,"Employee");


        //RoleDTO object-->> List<RoleDTO>  roleList
        // --->> RoleService DependencyInjection --->> roleService.createAll(roleList)
        //--->>CrudService  List<T> createAll(List<T> list);
        //-->> AbstractMapService
        /*
          T create(ID id, T object) {
                map.put(id, object);
           return object;
          }
         */

        //RoleService Impl
        /*
         @Override
        public Region create(Region object) {
            return super.create(object.getId(), object);
        }
         */


    }


}
