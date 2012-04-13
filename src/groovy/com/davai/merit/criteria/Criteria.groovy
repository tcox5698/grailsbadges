package com.davai.merit.criteria

public class Criteria {
	def buildQueryString() {
		throw new RuntimeException("buildQueryString is deprecated")
	}
	
	def buildQuery() {
		throw new RuntimeException("buildQuery not implemented by " + this.getClass().getName())
	}	
}