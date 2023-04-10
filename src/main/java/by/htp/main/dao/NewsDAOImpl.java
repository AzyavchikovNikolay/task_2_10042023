package by.htp.main.dao;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.main.entity.News;
import by.htp.main.entity.StatusOfNews;
import by.htp.main.entity.User;
import by.htp.main.entity.NewsDelete;
import by.htp.main.entity.NewsUpdate;


@Repository
public class NewsDAOImpl implements NewsDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String QUERY_NEWS_LIST = "FROM News ORDER BY dateOfCreate DESC";
	private static final String QUERY_LATEST_NEWS = "FROM News WHERE statusOfNews.statusNews = :paramStatus ORDER BY dateOfCreate DESC";
	private static final String PARAM_STATUS = "paramStatus";
	private static final String NEW = "new";
	private final static String PUBLISHED = "published";
	private static final String REMOTE = "remote";
	private final static String DATE_FORMAT = "yyyy-MM-dd HH*mm*ss";
	
	public List<News> getList() throws DAOException{
		
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<News> theQuery = currentSession.createQuery(QUERY_NEWS_LIST, News.class);
			List<News> news = theQuery.getResultList();
		
			return news;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
		
	public List<News> getLatestList(int count) throws DAOException{
		
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<News> theQuery = currentSession.createQuery(QUERY_LATEST_NEWS, News.class);
			theQuery.setParameter(PARAM_STATUS, PUBLISHED);
			theQuery.setMaxResults(count);
			List<News> news = theQuery.getResultList();
			return news;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public News fetchById(int id) throws DAOException{
		
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			News theNews = currentSession.get(News.class, id);
			return theNews;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void addNews(News news) throws DAOException{
		
		try {
			StatusOfNews statusOfNews = new StatusOfNews();
			if(news.getStatusOfNews().getId()==1) {
				statusOfNews.setId(1);
				statusOfNews.setStatusNews(NEW);
			}
			if(news.getStatusOfNews().getId()==2) {
				statusOfNews.setId(2);
				statusOfNews.setStatusNews(PUBLISHED);
			}
			news.setStatusOfNews(statusOfNews);
			
			Date dateOfNewsDeletion = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			String formattedDateOfNewsDeletion = formatter.format(dateOfNewsDeletion);
			
			news.setDateOfCreate(formattedDateOfNewsDeletion);
			
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.saveOrUpdate(news);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void updateNews(News news) throws DAOException{
		
		int idEditNews;
		
		try {
			StatusOfNews statusOfNews = new StatusOfNews();
			if(news.getStatusOfNews().getId()==1) {
				statusOfNews.setId(1);
				statusOfNews.setStatusNews(NEW);
			}
			if(news.getStatusOfNews().getId()==2) {
				statusOfNews.setId(2);
				statusOfNews.setStatusNews(PUBLISHED);
			}
					
			news.setStatusOfNews(statusOfNews);
			idEditNews = news.getId();
			
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.saveOrUpdate(news);
			
			idEditNews = news.getId();
			News editNews = currentSession.get(News.class, idEditNews);
			
			NewsUpdate updateNewsParam = new NewsUpdate();
	
			Date dateOfNewsUpdate = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			String formattedDateOfNewsUpdate = formatter.format(dateOfNewsUpdate);
		
			User user = editNews.getUser();
			
			updateNewsParam.setDateOfUpdate(formattedDateOfNewsUpdate);
			updateNewsParam.setNews(news);
			updateNewsParam.setUser(user);
		
			currentSession.saveOrUpdate(updateNewsParam);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
		
	public void deleteNews(int id) throws DAOException{
		
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			News deletedNews = currentSession.get(News.class, id);
			
			StatusOfNews statusOfNews = new StatusOfNews();
			statusOfNews.setId(3);
			statusOfNews.setStatusNews(REMOTE);
			deletedNews.setStatusOfNews(statusOfNews);
			
			NewsDelete deletedNewsParam = new NewsDelete();
			
			Date dateOfNewsDeletion = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			String formattedDateOfNewsDeletion = formatter.format(dateOfNewsDeletion);
		
			User user = deletedNews.getUser();
			
			deletedNewsParam.setDateOfDeleteon(formattedDateOfNewsDeletion);
			deletedNewsParam.setNews(deletedNews);
			deletedNewsParam.setUser(user);
			deletedNews.setNewsDelete(deletedNewsParam);
			
			currentSession.saveOrUpdate(deletedNews);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	public void deleteNewses(String[] idNewses) throws DAOException{
		
		try {
			for (String id : idNewses) {	
				deleteNews((int)Integer.valueOf(id));
			}
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
