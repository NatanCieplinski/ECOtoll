package dao.interfaces;

import java.util.Optional;

// Interfaccia del DAO che fornisce le CRUD
public interface DaoI<T> {
    void create(T t);
    Optional<T> read(long id);  
    void update(T t, String[] params);
    void delete(T t);
}