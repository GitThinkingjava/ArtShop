package cn.test.service;

import java.util.List;

import cn.test.common.BaseService;
import cn.test.model.User;

public interface IUserService extends BaseService<User> {
	
	public List<User> findAllUser(String userName, int begin, int size);

	public int getTotal(String userName);
	public void saveUser(User user);
	public void deleteUser(String userUuid) throws Exception;
	public void editUser(User user) throws Exception;
	User findByUserName(String name);

	User findByUserNameAndPassword(String name, String password);

    public User findByUuid(String userUuid);

    public void updateUser(User user);
	

	
}
