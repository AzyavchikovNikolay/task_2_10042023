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
@Table(name="news")
public class News {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="brief")
	private String brief;
	
	@Column(name="content")
	private String content;
	
	@Column(name="date_of_create")
	private String dateOfCreate;
	
	/*@Column(name="status_of_news_id")
	private String statusOfNewsId;*/
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="status_of_news_id")
	private StatusOfNews statusOfNews;
	
	/*@Column(name="user_author_id")
	private String userAuthorId;*/
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="user_author_id")
	private User user;
	
	@OneToOne(mappedBy = "news", cascade = CascadeType.ALL)
	private NewsDelete newsDelete;
	
	@OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
	private List<NewsUpdate> newsUpdate;
	
	public News() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBrief() {
		return brief;
	}
	
	public void setBrief(String brief) {
		this.brief = brief;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getDateOfCreate() {
		return dateOfCreate;
	}
	
	public void setDateOfCreate(String dateOfCreate) {
		this.dateOfCreate = dateOfCreate;
	}
	
	/*public String getStatusOfNewsId() {
		return statusOfNewsId;
	}
	
	public void setStatusOfNewsId(String statusOfNewsId) {
		this.statusOfNewsId = statusOfNewsId;
	}*/

	public StatusOfNews getStatusOfNews() {
		return statusOfNews;
	}
	
	public void setStatusOfNews(StatusOfNews statusOfNews) {
		this.statusOfNews = statusOfNews;
	}
	
	/*public String getUserAuthorId() {
		return userAuthorId;
	}
	
	public void setUserAuthorId(String userAuthorId) {
		this.userAuthorId = userAuthorId;
	}*/
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public NewsDelete getNewsDelete() {
		return newsDelete;
	}
	
	public void setNewsDelete(NewsDelete newsDelete) {
		this.newsDelete = newsDelete;
	}
	
	public List<NewsUpdate> getNewsUpdate() {
		return newsUpdate;
	}
	
	public void setNewsUpdate(List<NewsUpdate> newsUpdate) {
		this.newsUpdate = newsUpdate;
	}
	
	/*public String toString() {
		return "News [id=" + id + ", title=" + title + ", brief=" + brief + ", content=" +
				content +", dateOfCreate=" + dateOfCreate +", statusOfNewsId=" + statusOfNewsId +
				", userAuthorId=" + userAuthorId +"]";
	}*/
	
	@Override
	public int hashCode() {

		return Objects.hash(id, title, brief, content, dateOfCreate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if(obj==null || getClass() != obj.getClass()) {
			return false;
		}
		News news = (News)obj;
		return Objects.equals(id, news.id)&&Objects.equals(title, news.title)&&
				Objects.equals(brief, news.brief)&&Objects.equals(content, news.content)&&
				Objects.equals(dateOfCreate, news.dateOfCreate);
	}
}

