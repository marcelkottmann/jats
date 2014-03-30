package com.github.pepe79.jats.demo.repository;

public class IdGenerator {
	private int id = 0;

	public IdGenerator(int start) {
		this.id = start;
	}

	public int nextId() {
		return ++id;
	}
}
