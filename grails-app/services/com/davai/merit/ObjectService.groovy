package com.davai.merit

import com.davai.merit.criteria.*

class ObjectService {
	def sessionFactory

	public Object read(Class clazz, Object id) {
		return sessionFactory.getCurrentSession().load(clazz, id)
	}
	
	public List find(Criteria criteria) {
		def davaiQuery = criteria.buildQuery()
		def queryString = davaiQuery.buildQueryString()
		log.info "queryString:" + queryString
	
		def query = sessionFactory.getCurrentSession().createQuery(
			queryString
		)
		
		for (param in davaiQuery.parameters) {
			log.info "param value class: " + param.value.getClass().getName()
		
			query.setParameter(param.key, param.value)
		}
		
		query.list()
	}
	
	public void save(Object object) {
		object.save()
	}
}