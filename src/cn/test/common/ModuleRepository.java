package cn.test.common;

import org.hibernate.Criteria;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;

public interface ModuleRepository {
    
    void save(Object object);
    
    void update(Object object);
    
    void delete(Object object);
    
    <T> T findById(Class<T> domainObjectClass, Serializable id);
    
    <T> T findById(Class<T> domainObjectClass, String idFieldName, Serializable id);
    
    <T> List<T> findAll(Class<T> domainObjectClass);
    
    <T> List<T> findAllBy(Class<T> domainObjectClass, Criteria criteria);
    
    <T> T findBy(Class<T> domainObjectClass, Criteria criteria);
    
    <T> long getTotalCount(Class<T> domainObjectClass);
    
    Criteria createBasicCriteria(Class domainObjectClass);
    
    Query getNamedQuery(String queryName);
    
    <T, O> List<O> findAllObjectsBy(Class<O> resultClass, Class<T> domainObjectClass, Criteria criteria);
    
    void evict(Object object);
}
