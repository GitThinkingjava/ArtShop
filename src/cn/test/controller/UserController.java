package cn.test.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.test.model.User;
import cn.test.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping("/login")
	@ResponseBody
	public User getUserAll(HttpServletRequest request, HttpServletResponse response) {

		User users = userService.findByUserNameAndPassword("zhangsan", "123");

		return users;
	}

	@RequestMapping("/pageUser")
	@ResponseBody
	public List<User> addUseer(HttpServletRequest request, HttpServletResponse response) {

		int currPage = 1;// 当前页
		String currentPage = request.getParameter("currPage");
		String loginNme = request.getParameter("username");
		if (StringUtils.isNotBlank(currentPage)) {
			currPage = Integer.parseInt(currentPage.trim());
		}
		int totalPage = 1;
		int size = 5; // 每页个数
		int countAll = 0;// 总个数
		countAll = userService.getTotal(loginNme);
		totalPage = (countAll + size - 1) / size;
		if (currPage > totalPage) {
			currPage = totalPage;
		}
		int begin = (currPage - 1) * size;
		List<User> users = userService.findAllUser(loginNme, begin, size);
		
		System.out.println("currPage:"+currPage+"totalPage:"+totalPage+"countAll:"+countAll);

		return users;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteUser", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> deleteUser(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			String userUuid = request.getParameter("userid");
			userService.deleteUser(userUuid);
			modelMap.put("deleteFlag", true);
		} catch (Exception e) {
			modelMap.put("deleteFlag", false);
			e.printStackTrace();
		}
		return modelMap;
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addUser", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> addUser(User user, HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {

			System.out.println(user.toString());

			userService.save(user);

			modelMap.put("addFlag", true);
		} catch (Exception e) {
			modelMap.put("addFlag", false);
			e.printStackTrace();
		}
		return modelMap;
	}
	

	/**
	 * 修改
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateUser", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String editUser(User user, HttpServletRequest request, HttpServletResponse response) {

		try {

			User userid = userService.findByUuid(user.getUserid());

			if (userid != null) {
				userService.update(user);
				return "success";
			} else {

				return "userid 不存在";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fall";
	}

}
