package com.sim.pfin



import org.junit.*
import grails.test.mixin.*

@TestFor(CatAfectaOperacionController)
@Mock(CatAfectaOperacion)
class CatAfectaOperacionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/catAfectaOperacion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.catAfectaOperacionInstanceList.size() == 0
        assert model.catAfectaOperacionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.catAfectaOperacionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.catAfectaOperacionInstance != null
        assert view == '/catAfectaOperacion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/catAfectaOperacion/show/1'
        assert controller.flash.message != null
        assert CatAfectaOperacion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/catAfectaOperacion/list'

        populateValidParams(params)
        def catAfectaOperacion = new CatAfectaOperacion(params)

        assert catAfectaOperacion.save() != null

        params.id = catAfectaOperacion.id

        def model = controller.show()

        assert model.catAfectaOperacionInstance == catAfectaOperacion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/catAfectaOperacion/list'

        populateValidParams(params)
        def catAfectaOperacion = new CatAfectaOperacion(params)

        assert catAfectaOperacion.save() != null

        params.id = catAfectaOperacion.id

        def model = controller.edit()

        assert model.catAfectaOperacionInstance == catAfectaOperacion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/catAfectaOperacion/list'

        response.reset()

        populateValidParams(params)
        def catAfectaOperacion = new CatAfectaOperacion(params)

        assert catAfectaOperacion.save() != null

        // test invalid parameters in update
        params.id = catAfectaOperacion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/catAfectaOperacion/edit"
        assert model.catAfectaOperacionInstance != null

        catAfectaOperacion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/catAfectaOperacion/show/$catAfectaOperacion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        catAfectaOperacion.clearErrors()

        populateValidParams(params)
        params.id = catAfectaOperacion.id
        params.version = -1
        controller.update()

        assert view == "/catAfectaOperacion/edit"
        assert model.catAfectaOperacionInstance != null
        assert model.catAfectaOperacionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/catAfectaOperacion/list'

        response.reset()

        populateValidParams(params)
        def catAfectaOperacion = new CatAfectaOperacion(params)

        assert catAfectaOperacion.save() != null
        assert CatAfectaOperacion.count() == 1

        params.id = catAfectaOperacion.id

        controller.delete()

        assert CatAfectaOperacion.count() == 0
        assert CatAfectaOperacion.get(catAfectaOperacion.id) == null
        assert response.redirectedUrl == '/catAfectaOperacion/list'
    }
}
