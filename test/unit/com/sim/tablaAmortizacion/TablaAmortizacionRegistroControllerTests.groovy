package com.sim.tablaAmortizacion



import org.junit.*
import grails.test.mixin.*

@TestFor(TablaAmortizacionRegistroController)
@Mock(TablaAmortizacionRegistro)
class TablaAmortizacionRegistroControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tablaAmortizacionRegistro/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tablaAmortizacionRegistroInstanceList.size() == 0
        assert model.tablaAmortizacionRegistroInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.tablaAmortizacionRegistroInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tablaAmortizacionRegistroInstance != null
        assert view == '/tablaAmortizacionRegistro/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tablaAmortizacionRegistro/show/1'
        assert controller.flash.message != null
        assert TablaAmortizacionRegistro.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tablaAmortizacionRegistro/list'

        populateValidParams(params)
        def tablaAmortizacionRegistro = new TablaAmortizacionRegistro(params)

        assert tablaAmortizacionRegistro.save() != null

        params.id = tablaAmortizacionRegistro.id

        def model = controller.show()

        assert model.tablaAmortizacionRegistroInstance == tablaAmortizacionRegistro
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tablaAmortizacionRegistro/list'

        populateValidParams(params)
        def tablaAmortizacionRegistro = new TablaAmortizacionRegistro(params)

        assert tablaAmortizacionRegistro.save() != null

        params.id = tablaAmortizacionRegistro.id

        def model = controller.edit()

        assert model.tablaAmortizacionRegistroInstance == tablaAmortizacionRegistro
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tablaAmortizacionRegistro/list'

        response.reset()

        populateValidParams(params)
        def tablaAmortizacionRegistro = new TablaAmortizacionRegistro(params)

        assert tablaAmortizacionRegistro.save() != null

        // test invalid parameters in update
        params.id = tablaAmortizacionRegistro.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tablaAmortizacionRegistro/edit"
        assert model.tablaAmortizacionRegistroInstance != null

        tablaAmortizacionRegistro.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tablaAmortizacionRegistro/show/$tablaAmortizacionRegistro.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tablaAmortizacionRegistro.clearErrors()

        populateValidParams(params)
        params.id = tablaAmortizacionRegistro.id
        params.version = -1
        controller.update()

        assert view == "/tablaAmortizacionRegistro/edit"
        assert model.tablaAmortizacionRegistroInstance != null
        assert model.tablaAmortizacionRegistroInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tablaAmortizacionRegistro/list'

        response.reset()

        populateValidParams(params)
        def tablaAmortizacionRegistro = new TablaAmortizacionRegistro(params)

        assert tablaAmortizacionRegistro.save() != null
        assert TablaAmortizacionRegistro.count() == 1

        params.id = tablaAmortizacionRegistro.id

        controller.delete()

        assert TablaAmortizacionRegistro.count() == 0
        assert TablaAmortizacionRegistro.get(tablaAmortizacionRegistro.id) == null
        assert response.redirectedUrl == '/tablaAmortizacionRegistro/list'
    }
}
