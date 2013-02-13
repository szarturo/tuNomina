package com.sim.cliente



import org.junit.*
import grails.test.mixin.*

@TestFor(RsClienteReferenciaController)
@Mock(RsClienteReferencia)
class RsClienteReferenciaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/rsClienteReferencia/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.rsClienteReferenciaInstanceList.size() == 0
        assert model.rsClienteReferenciaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.rsClienteReferenciaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.rsClienteReferenciaInstance != null
        assert view == '/rsClienteReferencia/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/rsClienteReferencia/show/1'
        assert controller.flash.message != null
        assert RsClienteReferencia.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/rsClienteReferencia/list'

        populateValidParams(params)
        def rsClienteReferencia = new RsClienteReferencia(params)

        assert rsClienteReferencia.save() != null

        params.id = rsClienteReferencia.id

        def model = controller.show()

        assert model.rsClienteReferenciaInstance == rsClienteReferencia
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/rsClienteReferencia/list'

        populateValidParams(params)
        def rsClienteReferencia = new RsClienteReferencia(params)

        assert rsClienteReferencia.save() != null

        params.id = rsClienteReferencia.id

        def model = controller.edit()

        assert model.rsClienteReferenciaInstance == rsClienteReferencia
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/rsClienteReferencia/list'

        response.reset()

        populateValidParams(params)
        def rsClienteReferencia = new RsClienteReferencia(params)

        assert rsClienteReferencia.save() != null

        // test invalid parameters in update
        params.id = rsClienteReferencia.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/rsClienteReferencia/edit"
        assert model.rsClienteReferenciaInstance != null

        rsClienteReferencia.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/rsClienteReferencia/show/$rsClienteReferencia.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        rsClienteReferencia.clearErrors()

        populateValidParams(params)
        params.id = rsClienteReferencia.id
        params.version = -1
        controller.update()

        assert view == "/rsClienteReferencia/edit"
        assert model.rsClienteReferenciaInstance != null
        assert model.rsClienteReferenciaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/rsClienteReferencia/list'

        response.reset()

        populateValidParams(params)
        def rsClienteReferencia = new RsClienteReferencia(params)

        assert rsClienteReferencia.save() != null
        assert RsClienteReferencia.count() == 1

        params.id = rsClienteReferencia.id

        controller.delete()

        assert RsClienteReferencia.count() == 0
        assert RsClienteReferencia.get(rsClienteReferencia.id) == null
        assert response.redirectedUrl == '/rsClienteReferencia/list'
    }
}
