package com.rs.gral



import org.junit.*
import grails.test.mixin.*

@TestFor(RsPersonaController)
@Mock(RsPersona)
class RsPersonaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/rsPersona/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.rsPersonaInstanceList.size() == 0
        assert model.rsPersonaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.rsPersonaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.rsPersonaInstance != null
        assert view == '/rsPersona/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/rsPersona/show/1'
        assert controller.flash.message != null
        assert RsPersona.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/rsPersona/list'

        populateValidParams(params)
        def rsPersona = new RsPersona(params)

        assert rsPersona.save() != null

        params.id = rsPersona.id

        def model = controller.show()

        assert model.rsPersonaInstance == rsPersona
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/rsPersona/list'

        populateValidParams(params)
        def rsPersona = new RsPersona(params)

        assert rsPersona.save() != null

        params.id = rsPersona.id

        def model = controller.edit()

        assert model.rsPersonaInstance == rsPersona
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/rsPersona/list'

        response.reset()

        populateValidParams(params)
        def rsPersona = new RsPersona(params)

        assert rsPersona.save() != null

        // test invalid parameters in update
        params.id = rsPersona.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/rsPersona/edit"
        assert model.rsPersonaInstance != null

        rsPersona.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/rsPersona/show/$rsPersona.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        rsPersona.clearErrors()

        populateValidParams(params)
        params.id = rsPersona.id
        params.version = -1
        controller.update()

        assert view == "/rsPersona/edit"
        assert model.rsPersonaInstance != null
        assert model.rsPersonaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/rsPersona/list'

        response.reset()

        populateValidParams(params)
        def rsPersona = new RsPersona(params)

        assert rsPersona.save() != null
        assert RsPersona.count() == 1

        params.id = rsPersona.id

        controller.delete()

        assert RsPersona.count() == 0
        assert RsPersona.get(rsPersona.id) == null
        assert response.redirectedUrl == '/rsPersona/list'
    }
}
