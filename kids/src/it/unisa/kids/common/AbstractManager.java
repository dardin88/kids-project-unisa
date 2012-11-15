package it.unisa.kids.common;

public abstract class AbstractManager<T> {
	protected T imp;
	
	public abstract T getManagerImplementor();
}
