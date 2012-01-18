package com.davai.merit



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Category)
class CategoryTests {

    void testNameBeUnique() {
        def inputName = "inputName"
        def existingCategory = new Category(name: inputName)
        
        mockForConstraintsTests(Category, [existingCategory])
        
        def newCategory = new Category(name: inputName)

        newCategory.validate()
        
        println newCategory.errors
        
        assert "unique" == newCategory.errors["name"]
    }
    
}
