package com.ziyoujiayuan.web.sso.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ziyoujiayuan.data.sql.mybaties.entity.auto.UserDetailBean;
import com.ziyoujiayuan.data.sql.mybaties.entity.auto.UserRolesBean;

/**
 * 重写UserDetails
 * @Author wanghjbuf
 * @Date 2017年9月25日
 */
@SuppressWarnings("serial")
public class OnlineUser implements UserDetails {
	
	private String userid;
	private String username;
	private String password;
	private List<UserRolesBean> roles;
	
	@Override
	public String toString() {
		return "OnlineUser [userid=" + userid + ", username=" + username + ", password=" + password + ", roles=" + roles
				+ "]";
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<UserRolesBean> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRolesBean> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		List<UserRolesBean> roles=this.getRoles();
		for(UserRolesBean role:roles){
			auths.add(new SimpleGrantedAuthority(role.getAuthName()));
		}
		return auths;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public OnlineUser() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public OnlineUser(UserDetailBean userDetailBean) {
		super();
		this.userid = userDetailBean.getUserId();
		this.username = userDetailBean.getUserName();
		this.password = userDetailBean.getPassword();
		
	}

}
