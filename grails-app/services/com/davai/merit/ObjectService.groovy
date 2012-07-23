package com.davai.merit

import com.davai.merit.criteria.*

class ObjectService {
	def sessionFactory

	public Object read(Class clazz, Object id) {
		return sessionFactory.getCurrentSession().load(clazz, id)
	}
	
	def find(Criteria criteria) {
		return criteria.find()
	}
	
	def save(Object object) {
		if (object.isAttached()) {
			object = object.merge()
		}
	
		def saveResult = object.save()
		log.trace "save result: " + saveResult
		return saveResult
	}
	
	def select(String queryString) {
		return Person.executeQuery(queryString)
	}
}