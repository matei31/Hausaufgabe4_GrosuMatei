package repository;

import java.util.ArrayList;
import java.util.List;

public abstract class InMemoryRepo<T> implements ICrudRepo<T> {
    protected List<T> repo_list;
    public InMemoryRepo() {
        repo_list = new ArrayList<>();
    }

    /**
     * @param entity is the entity to be returned and must not be null
     * @return the specified entity or null - if there is no such entity
     */
    @Override
    public T findOne(T entity) {
        for(T t:repo_list)
        {
            if(t.equals(entity))
                return t;
        }
        return null;
    }

    /**
     * @return all entities
     */
    @Override
    public Iterable<T> findAll() {
        return repo_list;
    }

    /**
     * @param entity entity must be not null
     * @return null- if the given entity is saved otherwise returns the entity (id already exists)
     */
    @Override
    public T save(T entity) {
        for(T t:repo_list)
        {
            if(t.equals(entity))
                return t;
        }
        repo_list.add(entity);
        return null;
    }

    /**
     * removes the entity with the specified id
     *
     * @param entity must be not null
     * @return the removed entity or null if there is no entity with the given id
     */
    @Override
    public T delete(T entity) {
        for(T t: repo_list){
            if(t.equals(entity)) {
                repo_list.remove(t);
                return t;
            }
        }
        return null;

    }
}


