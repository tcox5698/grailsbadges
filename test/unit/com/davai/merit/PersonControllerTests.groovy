package com.davai.merit



import org.junit.*
import grails.test.mixin.*

@TestFor(PersonController)
@Mock(Person)
class PersonControllerTests {

    def populateValidParams(params) {
      assert params != null
      params["name"] = 'inputName'
      params["username"] = 'username@username.com'
      params["password"] = 'password' + (5 % new java.util.Date().getTime())
      params["enabled"] = 'true'
      params["accountExpired"] = 'false'
      params["accountLocked"] = 'false'
    }

    void testIndex() {
        controller.index()
        assert "/person/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.personInstanceList.size() == 0
        assert model.personInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.personInstance != null
    }

    void testSave() {
        controller.save()

        assert model.personInstance != null
        assert view == '/person/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/person/show/1'
        assert controller.flash.message != null
        assert Person.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/person/list'


        populateValidParams(params)
        def person = new Person(params)

        assert person.save() != null

        params.id = person.id

        def model = controller.show()

        assert model.personInstance == person
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/person/list'


        populateValidParams(params)
        def person = new Person(params)

        assert person.save() != null

        params.id = person.id

        def model = controller.edit()

        assert model.personInstance == person
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/person/list'

        response.reset()


        populateValidParams(params)
        def person = new Person(params)

        assert person.save() != null

        // test invalid parameters in update
        params.id = person.id
        params.username = null

        controller.update()

        assertTrue("was expected some person.errors: " + person.errors, person.errors.hasErrors())
        assertEquals("/person/edit",  view)
        assert model.personInstance != null

        person.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/person/show/$person.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        person.clearErrors()

        populateValidParams(params)
        params.id = person.id
        params.version = -1
        controller.update()

        assert view == "/person/edit"
        assert model.personInstance != null
        assert model.personInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/person/list'

        response.reset()

        populateValidParams(params)
        def person = new Person(params)

        assert person.save() != null
        assert Person.count() == 1

        params.id = person.id

        controller.delete()

        assert Person.count() == 0
        assert Person.get(person.id) == null
        assert response.redirectedUrl == '/person/list'
    }
}
