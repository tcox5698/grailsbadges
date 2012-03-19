package com.davai.merit.criteria

public class Criteria {
	def buildQueryString() {
		throw new RuntimeException("buildQueryString not implemented by " + this.getClass().getName())
	}
}