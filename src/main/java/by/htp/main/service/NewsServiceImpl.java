package by.htp.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.main.dao.DAOException;
import by.htp.main.dao.NewsDAO;
import by.htp.main.entity.News;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDAO newsDAO;
	
	@Transactional
	public List<News> getList() throws ServiceException {
		
		try {
			return newsDAO.getList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Transactional
	public List<News> getLatestList(int count) throws ServiceException {
		
		try {
			return newsDAO.getLatestList(count);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Transactional
	public News fetchById(int id) throws ServiceException {
		
		try {
			return newsDAO.fetchById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}
	
	@Transactional
	public void addNews(News news) throws ServiceException {
		
		try {
			newsDAO.addNews(news);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}
	
	@Transactional
	public void updateNews(News news) throws ServiceException {
		
		try {
			newsDAO.updateNews(news);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}
	
	@Transactional
	public void deleteNews(int id) throws ServiceException {
		
		try {
			newsDAO.deleteNews(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}		
	}
	
	@Transactional
	public void deleteNewses(String[] idNewses) throws ServiceException {
		
		try {
			for(String id:idNewses) {
				newsDAO.deleteNews((int)Integer.valueOf(id));
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}
}
