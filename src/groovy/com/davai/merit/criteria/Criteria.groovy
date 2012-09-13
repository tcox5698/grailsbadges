package com.davai.merit.criteria

public class Criteria {
	def maxResults
	def offset
	def arguments = [:]
	def orderArgs = [:]
	def likeArgs = [:]
	
	Class<?> getDomainClass() {
		throw new RuntimeException(" Criteria " + this.getClass().getName() + " must implement getClass ")	
	}
	
	
	def count() {
		def finalQuery = buildFinalQuery(getBaseQuery())

		finalQuery = " select count(*) " + finalQuery
		
		likeArgs.each() {
			arguments.put(it.key, "%${it.value}%")
		}

		return getDomainClass().executeQuery(finalQuery, arguments)[0]
	}
	
	def find() {
		def finalQuery = buildFinalQuery(getBaseQuery())
		


		likeArgs.each() {
			arguments.put(it.key, "%${it.value}%")
		}
		
		def pagingParams = [:]
				
		if (maxResults) pagingParams.put('max', maxResults)
		if (offset) pagingParams.put('offset',offset)
				
		log.trace "finalQuery: " + ["queryString":finalQuery,"arguments":arguments]	

		return getDomainClass().findAll(finalQuery, arguments, pagingParams)
	}
	
	def doFind(finalQuery, arguments) {
		throw new RuntimeException(" Criteria " + this.getClass().getName() + " does not implement doFind(finalQuery, arguments) ")	
	}
	
	def buildFinalQuery(baseQuery) {
		def returnQuery = baseQuery
		
		log.trace "starting with base query: " + baseQuery
		
		returnQuery = addWhereClause(returnQuery)
		returnQuery = addOrderClause(returnQuery)

		log.trace "finalQuery: " + ["queryString":returnQuery,"arguments":arguments]	

		return returnQuery
	}	
	
	def addOrderClause(startQuery) {
		def returnQuery = startQuery
		
		if (!orderArgs.isEmpty()) {
			returnQuery += " order by "
		}
		
		orderArgs.eachWithIndex() {it, i ->
			if (i > 0) {
				returnQuery += " , "
			}
			returnQuery += " ${this.alias}.${it.key} ${it.value} "
		}

		log.trace "withOrderByClause: " + ["queryString":returnQuery,"arguments":arguments]	
		
		return returnQuery
	}
	
	def addWhereClause(startQuery) {
		def returnQuery = startQuery
	
		if (!arguments.isEmpty() || !likeArgs.isEmpty()) {
			returnQuery += " where "
		}
		
		arguments.eachWithIndex() {it, i ->
			if (i > 0) {
				returnQuery += " and "
			}
			returnQuery += " ${this.alias}.${it.key} = :${it.key} "
		}
		
		likeArgs.eachWithIndex() {it, i ->
			if (i > 0) {
				returnQuery += " and "
			}
			returnQuery += " upper(${this.alias}.${it.key}) like upper(:${it.key}) "
		}

		log.trace "withWhereClause: " + ["queryString":returnQuery,"arguments":arguments]	
		
		return returnQuery
	}
}