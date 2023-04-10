package by.htp.main.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="status_of_news")
public class StatusOfNews {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="status_news")
	private String statusNews;
	
	@OneToMany(mappedBy = "statusOfNews", cascade = CascadeType.ALL)
	private List<News> news;
	
	public StatusOfNews() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getStatusNews() {
		return statusNews;
	}
	
	public void setStatusNews(String statusNews) {
		this.statusNews = statusNews;
	}
	
	public List<News> getNews() {
		return news;
	}
	
	public void setNews(List<News> news) {
		this.news = news;
	}
	
}

