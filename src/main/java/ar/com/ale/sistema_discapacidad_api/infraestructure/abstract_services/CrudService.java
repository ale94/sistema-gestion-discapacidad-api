package ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services;

import java.util.List;

public interface CrudService<RQ, RS, ID> {

        RS create(RQ request);

        List<RS> readAll();

        RS update(RQ request, ID id);

        void delete(ID id);

}
