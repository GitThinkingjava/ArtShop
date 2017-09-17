package cn.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.PolymorphismType;

@Entity(name = "T_USER")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, optimisticLock = OptimisticLockType.NONE, polymorphism = PolymorphismType.EXPLICIT)
public class User {

	@Id
	@Column(name = "USERID")
	private String userid;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWD")
	private String passwd;

	@Column(name = "NICKNAME")
	private String nickname;

	@Column(name = "AGE")
	private int age;

	@Column(name = "SEX")
	private String sex;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", passwd=" + passwd + ", nickname=" + nickname
				+ ", age=" + age + ", sex=" + sex + "]";
	}

}
