package com.github.pepe79.jats.repository;

import java.util.Collection;


public interface Repository<T>
{
	Collection<T> find(String type, String id);
}
