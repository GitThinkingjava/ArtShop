package cn.test.common;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component("eBCMSModuleRepository")
@Scope("singleton")
public class EBCMSModuleRepository extends ModuleRepositoryImpl {
    
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
}
