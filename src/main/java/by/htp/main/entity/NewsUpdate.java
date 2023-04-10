package by.htp.main.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="news_update")
public class NewsUpdate {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	/*@Column(name="news_id")
	private int newsId;*/
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "news_id")
	private News news;
	
	/*@Column(name="user_id")
	private int userId;*/

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="date_of_update")
	private String dateOfUpdate;
	
	public NewsUpdate() {
		
	}
	
	/*public int getNewsId() {
		return newsId;
	}
	
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}*/
	
	public News getNews() {
		return news;
	}
	
	public void setNews(News news) {
		this.news = news;
	}
	
	/*public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}*/
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getDateOfUpdate() {
		return dateOfUpdate;
	}
	
	public void setDateOfUpdate(String dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}
	
	
}

