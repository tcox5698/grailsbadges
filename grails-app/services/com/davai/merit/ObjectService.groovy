package com.davai.merit

import com.davai.merit.criteria.*

class ObjectService {
	def sessionFactory

	public Object read(Class clazz, Object id) {
		return sessionFactory.getCurrentSession().load(clazz, id)
	}
	
	public List find(Criteria criteria) {
		return null
	}
	
	public void save(Object object) {
		object.save()
	}
}