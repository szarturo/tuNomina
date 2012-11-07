package com.sim.tablaAmortizacion



import org.junit.*
import grails.test.mixin.*

@TestFor(TablaAmortizacionController)
@Mock(TablaAmortizacion)
class TablaAmortizacionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tablaAmortizacion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tablaAmortizacionInstanceList.size() == 0
        assert model.tablaAmortizacionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.tablaAmortizacionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tablaAmortizacionInstance != null
        assert view == '/tablaAmortizacion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tablaAmortizacion/show/1'
        assert controller.flash.message != null
        assert TablaAmortizacion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tablaAmortizacion/list'

        populateValidParams(params)
        def tablaAmortizacion = new TablaAmortizacion(params)

        assert tablaAmortizacion.save() != null

        params.id = tablaAmortizacion.id

        def model = controller.show()

        assert model.tablaAmortizacionInstance == tablaAmortizacion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tablaAmortizacion/list'

        populateValidParams(params)
        def tablaAmortizacion = new TablaAmortizacion(params)

        assert tablaAmortizacion.save() != null

        params.id = tablaAmortizacion.id

        def model = controller.edit()

        assert model.tablaAmortizacionInstance == tablaAmortizacion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tablaAmortizacion/list'

        response.reset()

        populateValidParams(params)
        def tablaAmortizacion = new TablaAmortizacion(params)

        assert tablaAmortizacion.save() != null

        // test invalid parameters in update
        params.id = tablaAmortizacion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tablaAmortizacion/edit"
        assert model.tablaAmortizacionInstance != null

        tablaAmortizacion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tablaAmortizacion/show/$tablaAmortizacion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tablaAmortizacion.clearErrors()

        populateValidParams(params)
        params.id = tablaAmortizacion.id
        params.version = -1
        controller.update()

        assert view == "/tablaAmortizacion/edit"
        assert model.tablaAmortizacionInstance != null
        assert model.tablaAmortizacionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tablaAmortizacion/list'

        response.reset()

        populateValidParams(params)
        def tablaAmortizacion = new TablaAmortizacion(params)

        assert tablaAmortizacion.save() != null
        assert TablaAmortizacion.count() == 1

        params.id = tablaAmortizacion.id

        controller.delete()

        assert TablaAmortizacion.count() == 0
        assert TablaAmortizacion.get(tablaAmortizacion.id) == null
        assert response.redirectedUrl == '/tablaAmortizacion/list'
    }
}
