package by.htp.main.dao;

import java.util.List;

import by.htp.main.entity.News;

public interface NewsDAO {

	public List<News> getList() throws DAOException;
	public List<News> getLatestList(int count) throws DAOException;
	public News fetchById(int id) throws DAOException;
	public void addNews(News news) throws DAOException;
	public void updateNews(News news) throws DAOException;
	public void deleteNews(int id) throws DAOException;
	public void deleteNewses(String[] idNewses) throws DAOException;
	
}
