package cn.test.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;

/**
 * 定义通用的CRUD操作
 * 
 */
public interface BaseService<T> {
    
    void save(T object);
    
    void update(T object);
    
    T findById(Serializable id);
    
    List<T> findAll();
    
    void delete(T object);
    
    void deleteComponent(Object object);
    
    List<T> findAllBy(Criteria criteria);
    
    long getTotalCount();
    
    T findBy(Criteria criteria);
    
    <O> List<O> findAllObjectsBy(Class<O> resultClass, Criteria criteria);
    
    <O> O findObjectBy(Class<O> resultClass, Criteria criteria);
    
    void evict(T object);
}
