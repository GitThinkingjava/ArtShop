package cn.test.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.test.common.BaseServiceSupport;
import cn.test.common.EBCMSModuleRepository;
import cn.test.common.ModuleRepository;
import cn.test.model.User;
import cn.test.service.IUserService;

@Component("userService")
@Scope("singleton")
@org.springframework.stereotype.Repository
@Transactional(rollbackFor = Throwable.class)
public class UserServiceImpl extends BaseServiceSupport<User> implements IUserService {

	@Resource(name = "eBCMSModuleRepository")
	private EBCMSModuleRepository repository;

	@Override
	public ModuleRepository getRepository() {
		return repository;
	}

	@Override
	public Class<User> getDomainClass() {
		return User.class;
	}

	@Override
	public List<User> findAllUser(String userName, int begin, int size) {
		Criteria criteria = this.repository.createBasicCriteria(getDomainClass());
		if (StringUtils.isNotBlank(userName)) {
		    criteria.add(Restrictions.ilike("username", userName, MatchMode.ANYWHERE));
		}
		criteria.setFirstResult(begin);
		criteria.setMaxResults(size);
		return criteria.list();
	}

	@Override
	public int getTotal(String userName) {
		Criteria criteria = this.repository.createBasicCriteria(getDomainClass());
		if (StringUtils.isNotBlank(userName)) {
		    criteria.add(Restrictions.ilike("username", userName, MatchMode.ANYWHERE));
		}
		List<Object> tempList = criteria.setProjection(Projections.rowCount()).list();
		return Integer.parseInt(tempList.get(0).toString());
	}

	@Override
	public User findByUserName(String name) {
		Criteria c = this.getRepository().createBasicCriteria(this.getDomainClass());
		c.add(Restrictions.eq("loginNme", name));
		List<User> tempList = c.list();
		if (tempList.size() > 0) {
			return tempList.get(0);
		} else {
			return null;
		}
	}
	@Override
	public User findByUserNameAndPassword(String name, String password) {
		Criteria c = this.getRepository().createBasicCriteria(this.getDomainClass());
		c.add(Restrictions.eq("username", name));
		c.add(Restrictions.eq("passwd", password));
		List<User> tempList = c.list();
		if (tempList.size() > 0) {
			return tempList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void saveUser(User user) {
		this.save(user);
	}

    @Override
    public void deleteUser(String userUuid) throws Exception {
        User user = this.findById(userUuid);
        if (user != null) {
            this.delete(user);
        }
    }

    @Override
    public void editUser(User user) throws Exception {
        this.update(user);
    }

    @Override
    public User findByUuid(String userUuid) {
        User user = this.findById(userUuid);
        return user;
    }
    
    @Override
    public void updateUser(User user){
    	this.update(user);
    }

}