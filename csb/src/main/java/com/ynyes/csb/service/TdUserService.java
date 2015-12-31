package com.ynyes.csb.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.csb.entity.TdSetting;
import com.ynyes.csb.entity.TdUser;
import com.ynyes.csb.repository.TdUserRepo;

/**
 * TdUser 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdUserService {
	@Autowired
	TdUserRepo repository;

	@Autowired
	TdSettingService tdSettingService;


	/**
	 * 删除用户数据
	 * 
	 * @param username
	 */
	private void userDataDelete(String username) {
		if (null == username) {
			return;
		}

	}

	public TdUser addNewUser(String username, String password, String mobile, String email, String carCode) {
		if (null == username || null == password || null == email || username.isEmpty() || password.isEmpty()
				|| email.isEmpty()) {
			return null;
		}

		if (null != repository.findByUsernameIgnoreCase(username)) {
			return null;
		}

		TdUser user = new TdUser();

		user.setUsername(username);
		user.setPassword(password);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setRegisterTime(new Date());
		user.setLastLoginTime(new Date());
		user.setStatusId(1L); // 正常
		user.setRoleId(0L); // 普通用户

		TdSetting setting = tdSettingService.findTopBy();


		return user;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 *            菜单项ID
	 */
	public void delete(Long id) {
		if (null != id) {
			TdUser user = repository.findOne(id);

			if (null != user) {
				userDataDelete(user.getUsername());
			}

			repository.delete(id);
		}
	}

	/**
	 * 删除
	 * 
	 * @param e
	 *            菜单项
	 */
	public void delete(TdUser e) {
		if (null != e) {

			userDataDelete(e.getUsername());
			repository.delete(e);
		}
	}

	/**
	 * 查找
	 * 
	 * @param id
	 *            ID
	 * @return
	 */
	public TdUser findOne(Long id) {
		if (null == id) {
			return null;
		}

		return repository.findOne(id);
	}

	public TdUser findByUsernameAndIsEnabled(String username) {
		if (null == username) {
			return null;
		}

		return repository.findByUsernameAndStatusIdOrUsernameAndStatusIdOrUsernameAndStatusIdIsNull(username, 0L, username, 1L , username);
	}

	public TdUser findByUsername(String username) {
		if (null == username) {
			return null;
		}

		return repository.findByUsernameIgnoreCase(username);
	}

	public TdUser findByUsernameAndIdNot(String username, Long id) {
		if (null == username || null == id) {
			return null;
		}

		return repository.findByUsernameAndIdNot(username, id);
	}


	/**
	 * 手机号查找
	 * 
	 * @author libiao
	 * @param mobile
	 * @return
	 */
	public TdUser findByMobileAndIsEnabled(String mobile) {
		if (null == mobile) {
			return null;
		}
		return repository.findByMobileAndStatusIdOrMobileAndStatusId(mobile, 0L, mobile, 1L);
	}

	/**
	 * 
	 * 注册查找----按手机号
	 * 
	 * @author libiao
	 * @param mobile
	 * @return
	 */
	public TdUser findByMobile(String mobile) {
		if (null == mobile) {
			return null;
		}
		return repository.findByMobile(mobile);
	}



	/**
	 * 查找
	 * 
	 * @param ids
	 * @return
	 */
	public List<TdUser> findAll(Iterable<Long> ids) {
		return (List<TdUser>) repository.findAll(ids);
	}

	public Page<TdUser> findAllOrderBySortIdAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));

		return repository.findAll(pageRequest);
	}

	public Page<TdUser> findByRoleIdOrderByIdDesc(Long roleId, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByRoleIdOrderByIdDesc(roleId, pageRequest);
	}

	public Page<TdUser> searchAndFindByRoleIdOrderByIdDesc(String keywords, Long roleId, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository
				.findByUsernameContainingAndRoleIdOrMobileContainingAndRoleIdOrEmailContainingAndRoleIdOrRealNameContainingAndRoleIdOrderByIdDesc(
						keywords, roleId, keywords, roleId, keywords, roleId, keywords , roleId , pageRequest);
	}

	public Page<TdUser> searchAndOrderByIdDesc(String keywords, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByUsernameContainingOrMobileContainingOrEmailContainingOrderByIdDesc(keywords, keywords,
				keywords, pageRequest);
	}

	/**
	 * 保存
	 * 
	 * @param e
	 * @return
	 */
	public TdUser save(TdUser e) {

		return repository.save(e);
	}

	public List<TdUser> save(List<TdUser> entities) {

		return (List<TdUser>) repository.save(entities);
	}
}
