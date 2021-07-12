package elemica.shipment.service;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, I extends Serializable> extends Repository<T, I>, JpaSpecificationExecutor<T> {

    void delete(T entityObject) throws IllegalArgumentException, DataAccessException;

    @Override
    List<T> findAll(Specification<T> specification);

    Optional<T> findOne(I id) throws IllegalArgumentException, DataAccessException;

    T save(T entityObject) throws IllegalArgumentException, DataAccessException;

}
