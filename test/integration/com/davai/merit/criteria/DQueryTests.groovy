package com.davai.merit.criteria

class DQueryTests extends GroovyTestCase {
	
	void testBuildQueryString() {
		def query = new DQuery(fromClause:"from Goat g")
		
		query.addWhereParameter("hair","shaggy")
		
		assertEquals("from Goat g where hair = :hair", query.buildQueryString())
	}
}