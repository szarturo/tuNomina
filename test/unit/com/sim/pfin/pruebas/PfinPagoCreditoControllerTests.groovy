package com.sim.pfin.pruebas



import org.junit.*
import grails.test.mixin.*

@TestFor(PfinPagoCreditoController)
@Mock(PfinPagoCredito)
class PfinPagoCreditoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pfinPagoCredito/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pfinPagoCreditoInstanceList.size() == 0
        assert model.pfinPagoCreditoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pfinPagoCreditoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pfinPagoCreditoInstance != null
        assert view == '/pfinPagoCredito/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pfinPagoCredito/show/1'
        assert controller.flash.message != null
        assert PfinPagoCredito.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pfinPagoCredito/list'

        populateValidParams(params)
        def pfinPagoCredito = new PfinPagoCredito(params)

        assert pfinPagoCredito.save() != null

        params.id = pfinPagoCredito.id

        def model = controller.show()

        assert model.pfinPagoCreditoInstance == pfinPagoCredito
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pfinPagoCredito/list'

        populateValidParams(params)
        def pfinPagoCredito = new PfinPagoCredito(params)

        assert pfinPagoCredito.save() != null

        params.id = pfinPagoCredito.id

        def model = controller.edit()

        assert model.pfinPagoCreditoInstance == pfinPagoCredito
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pfinPagoCredito/list'

        response.reset()

        populateValidParams(params)
        def pfinPagoCredito = new PfinPagoCredito(params)

        assert pfinPagoCredito.save() != null

        // test invalid parameters in update
        params.id = pfinPagoCredito.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/pfinPagoCredito/edit"
        assert model.pfinPagoCreditoInstance != null

        pfinPagoCredito.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pfinPagoCredito/show/$pfinPagoCredito.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pfinPagoCredito.clearErrors()

        populateValidParams(params)
        params.id = pfinPagoCredito.id
        params.version = -1
        controller.update()

        assert view == "/pfinPagoCredito/edit"
        assert model.pfinPagoCreditoInstance != null
        assert model.pfinPagoCreditoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pfinPagoCredito/list'

        response.reset()

        populateValidParams(params)
        def pfinPagoCredito = new PfinPagoCredito(params)

        assert pfinPagoCredito.save() != null
        assert PfinPagoCredito.count() == 1

        params.id = pfinPagoCredito.id

        controller.delete()

        assert PfinPagoCredito.count() == 0
        assert PfinPagoCredito.get(pfinPagoCredito.id) == null
        assert response.redirectedUrl == '/pfinPagoCredito/list'
    }
}
