package by.htp.main.service;

import java.util.List;

import by.htp.main.entity.News;

public interface NewsService {

	public List<News> getList() throws ServiceException;
	public List<News> getLatestList(int count) throws ServiceException;
	public News fetchById(int id) throws ServiceException;
	public void addNews(News news) throws ServiceException;
	public void updateNews(News news) throws ServiceException;
	public void deleteNews(int id) throws ServiceException;
	public void deleteNewses(String[] idNewses) throws ServiceException;
}
