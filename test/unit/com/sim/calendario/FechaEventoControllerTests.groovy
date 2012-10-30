package com.sim.calendario



import org.junit.*
import grails.test.mixin.*

@TestFor(FechaEventoController)
@Mock(FechaEvento)
class FechaEventoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/fechaEvento/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.fechaEventoInstanceList.size() == 0
        assert model.fechaEventoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.fechaEventoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.fechaEventoInstance != null
        assert view == '/fechaEvento/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/fechaEvento/show/1'
        assert controller.flash.message != null
        assert FechaEvento.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/fechaEvento/list'

        populateValidParams(params)
        def fechaEvento = new FechaEvento(params)

        assert fechaEvento.save() != null

        params.id = fechaEvento.id

        def model = controller.show()

        assert model.fechaEventoInstance == fechaEvento
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/fechaEvento/list'

        populateValidParams(params)
        def fechaEvento = new FechaEvento(params)

        assert fechaEvento.save() != null

        params.id = fechaEvento.id

        def model = controller.edit()

        assert model.fechaEventoInstance == fechaEvento
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/fechaEvento/list'

        response.reset()

        populateValidParams(params)
        def fechaEvento = new FechaEvento(params)

        assert fechaEvento.save() != null

        // test invalid parameters in update
        params.id = fechaEvento.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/fechaEvento/edit"
        assert model.fechaEventoInstance != null

        fechaEvento.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/fechaEvento/show/$fechaEvento.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        fechaEvento.clearErrors()

        populateValidParams(params)
        params.id = fechaEvento.id
        params.version = -1
        controller.update()

        assert view == "/fechaEvento/edit"
        assert model.fechaEventoInstance != null
        assert model.fechaEventoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/fechaEvento/list'

        response.reset()

        populateValidParams(params)
        def fechaEvento = new FechaEvento(params)

        assert fechaEvento.save() != null
        assert FechaEvento.count() == 1

        params.id = fechaEvento.id

        controller.delete()

        assert FechaEvento.count() == 0
        assert FechaEvento.get(fechaEvento.id) == null
        assert response.redirectedUrl == '/fechaEvento/list'
    }
}
