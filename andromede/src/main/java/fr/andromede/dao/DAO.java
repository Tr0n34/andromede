package fr.andromede.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface DAO<T> {

	public abstract T create(T dto) throws DataAccessException;

	public abstract List<T> read(T dto) throws DataAccessException;

	public abstract T read(String id) throws DataAccessException;

	public abstract T update(T dto) throws DataAccessException;

	public abstract void delete(T dto) throws DataAccessException;

	public abstract void delete(String id) throws DataAccessException;

	public abstract void deleteAll() throws DataAccessException;

}
