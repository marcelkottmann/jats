package com.github.pepe79.jats.repository;

public interface RepositoryFactory<T>
{
	Repository<T> createRepository();
}
