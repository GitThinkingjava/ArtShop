package cn.test.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Throwable.class)
public abstract class BaseServiceSupport<T> implements BaseService<T> {
    public static final String ID_FIELD_NAME = "id";
    
    @Override
    public void save(T object) {
        getRepository().save(object);
    }
    
    @Override
    public void update(T object) {
        getRepository().update(object);
    }
    
    @Override
    public T findById(Serializable id) {
        return getRepository().findById(getDomainClass(), getIdFieldName(), id);
    }
    
    public String getIdFieldName() {
        return ID_FIELD_NAME;
    }
    
    @Override
    public List<T> findAll() {
        return getRepository().findAll(getDomainClass());
    }
    
    @Override
    public void delete(T object) {
        getRepository().delete(object);
    }
    
    @Override
    public void deleteComponent(Object object) {
        getRepository().delete(object);
    }
    
    @Override
    public List<T> findAllBy(Criteria criteria) {
        return getRepository().findAllBy(getDomainClass(), criteria);
    }
    
    @Override
    public long getTotalCount() {
        return getRepository().getTotalCount(getDomainClass());
    }
    
    @Override
    public T findBy(Criteria criteria) {
        return getRepository().findBy(getDomainClass(), criteria);
    }
    
    @Override
    public <O> List<O> findAllObjectsBy(Class<O> resultClass, Criteria criteria) {
        return criteria.list();
    }
    
    @Override
    public <O> O findObjectBy(Class<O> resultClass, Criteria criteria) {
        List<O> result = findAllObjectsBy(resultClass, criteria);
        return result.isEmpty() ? null : result.get(0);
    }
    
    public abstract ModuleRepository getRepository();
    
    public abstract Class<T> getDomainClass();
    
    @Override
    public void evict(T object) {
        getRepository().evict(object);
    }
}
