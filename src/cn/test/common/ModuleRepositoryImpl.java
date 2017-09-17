package cn.test.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public abstract class ModuleRepositoryImpl implements ModuleRepository {
	
    public static final String ID_FIELD_NAME = "id";
    
    public abstract SessionFactory getSessionFactory();
    
    public void save(Object object) {
        getCurrentSession().save(object);
    }
    
    public void update(Object object) {
        getCurrentSession().merge(object);
    }
    
    public void delete(Object object) {
        getCurrentSession().delete(object);
    }
    
    public <T> T findById(Class<T> objectClass, String idFieldName, Serializable id) {
        Criteria criteria = getCurrentSession().createCriteria(objectClass);
        criteria.add(Restrictions.eq(idFieldName, id));
        List<T> results = criteria.list();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
    
    public <T> T findById(Class<T> objectClass, Serializable id) {
        return findById(objectClass, getIdFieldName(), id);
    }
    
    public String getIdFieldName() {
        return ID_FIELD_NAME;
    }
    
    public <T> List<T> findAll(Class<T> objectClass) {
        Criteria criteria = getCurrentSession().createCriteria(objectClass);
        return (List<T>) criteria.list();
    }
    
    protected Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }
    
    @Override
    public <T> List<T> findAllBy(Class<T> domainObjectClass, Criteria criteria) {
        return criteria.list();
    }
    
    public <T> long getTotalCount(Class<T> objectClass) {
        Criteria criteria = getCurrentSession().createCriteria(objectClass);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.list().get(0);
    }
    
    @Override
    public Criteria createBasicCriteria(Class domainObjectClass) {
        return getSessionFactory().getCurrentSession().createCriteria(domainObjectClass);
    }
    
    @Override
    public <T> T findBy(Class<T> domainObjectClass, Criteria criteria) {
        List<T> result = findAllBy(domainObjectClass, criteria);
        return result.isEmpty() ? null : result.get(0);
    }
    
    @Override
    public Query getNamedQuery(String queryName) {
        return getCurrentSession().getNamedQuery(queryName);
    }
    
    @Override
    public <T, O> List<O> findAllObjectsBy(Class<O> resultClass, Class<T> domainObjectClass, Criteria criteria) {
        return criteria.list();
    }
    
    @Override
    public void evict(Object object) {
        getCurrentSession().evict(object);
    }
}
