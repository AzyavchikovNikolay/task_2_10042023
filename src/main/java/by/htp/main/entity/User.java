package by.htp.main.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="login")
	private String login;
	
	@Column(name="password")
	private String password;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="role_id")
	private Role role;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="status_user_id")
	private StatusUser statusUser;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserDetails userDetails;
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<News> news;
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<NewsDelete> newsDelete;
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<NewsUpdate> newsUpdate;
	
	public User() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public StatusUser getStatusUser() {
		return statusUser;
	}
	
	public void setStatusUser(StatusUser statusUser) {
		this.statusUser = statusUser;
	}
	
	public UserDetails getUserDetails() {
		return userDetails;
	}
	
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	
	public List<News> getNews() {
		return news;
	}
	
	public void setNews(List<News> news) {
		this.news = news;
	}
	
	public List<NewsDelete> getNewsDelete() {
		return newsDelete;
	}
	
	public void setNewsDelete(List<NewsDelete> newsDelete) {
		this.newsDelete = newsDelete;
	}
	
	public List<NewsUpdate> getNewsUpdate() {
		return newsUpdate;
	}
	
	public void setNewsUpdate(List<NewsUpdate> newsUpdate) {
		this.newsUpdate = newsUpdate;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(login, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		User user = (User) obj;
		return Objects.equals(login, user.login)&&Objects.equals(password, user.password);
	}
}
