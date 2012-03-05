package com.davai.merit

class ObjectService {
	def sessionFactory

	public Object read(Class clazz, Object id) {
		return sessionFactory.getCurrentSession().load(clazz, id)
	}
}