package lpnt.cg.service;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T> {

    Iterable<T> findAll();

//    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void remove(Long id);
}
