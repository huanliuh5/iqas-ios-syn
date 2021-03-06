package com.zlcook.iqas.ios.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlcook.iqas.ios.bean.User;
import com.zlcook.iqas.ios.bean.UserExample;
import com.zlcook.iqas.ios.dao.UserDao;
import com.zlcook.iqas.ios.dto.LoginDTO;
import com.zlcook.iqas.ios.form.RegisterForm;
import com.zlcook.iqas.ios.mapper.UserMapper;
import com.zlcook.iqas.ios.service.UserService;
import com.zlcook.iqas.ios.unitl.TokenUtils;

/**
* @author 周亮 
* @version 创建时间：2017年2月26日 下午10:09:08
* 
*/
@Service
public class UserServiceImpl implements UserService {
	//日志类
	private final Logger logger = org.apache.log4j.LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	@Override
	public int register(RegisterForm form) {
		User user = new User();
		BeanUtils.copyProperties(form, user);
		
		User existUser =userDao.getByLoginName(form.getLoginName());
		if( existUser !=null )
			return -1;
		try{
			userDao.save(user);
			return 1;
		}catch(Exception e)
		{
			logger.error("注册用户失败:"+e.getMessage());
			return -1;
		}
	}

	@Override
	public LoginDTO login(String loginName, String password) {
		
		LoginDTO loginDTO =new LoginDTO();
		if(loginName==null || "".equals(loginName.trim()) || password==null ||"".equals(password.trim())){
			loginDTO.setStatus(-1);
			return loginDTO;
		}
		
		User existUser =userDao.getByLoginName(loginName);
		if( existUser==null || !password.equals(existUser.getPassword())){
			loginDTO.setStatus(-1);
			return loginDTO;
		}
		//生成token值
		String token =TokenUtils.generatorToken();
		existUser.setToken(token);
		
		//更新用户
		if(userDao.update(existUser) !=1 ){
			loginDTO.setStatus(0);
			return loginDTO;
		}
		
		BeanUtils.copyProperties(existUser, loginDTO);
		loginDTO.setStatus(1);
		return loginDTO;
	}
	


	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
