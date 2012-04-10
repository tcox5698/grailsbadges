package com.davai.merit.criteria

public class DQuery {
	def parameters = [:]
	def fromClause

	def addWhereParameter(String paramName, Object paramValue) {
		parameters.put(paramName, paramValue)		
	}
	
	def buildQueryString() {
		def queryString = new StringBuilder(fromClause)
		
		if (!parameters.isEmpty()) {
			queryString.append(" where ")
		}
		
		def first = true
		
		for (param in parameters) {
			if (!first) {
				queryString.append(" and ")
			}
			queryString.append(param.key)
			queryString.append(" = :")
			queryString.append(param.key)
			first = false
		}
		
		queryString.toString()
	}
}