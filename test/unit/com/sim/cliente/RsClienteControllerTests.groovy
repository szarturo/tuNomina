package com.sim.cliente



import org.junit.*
import grails.test.mixin.*

@TestFor(RsClienteController)
@Mock(RsCliente)
class RsClienteControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/rsCliente/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.rsClienteInstanceList.size() == 0
        assert model.rsClienteInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.rsClienteInstance != null
    }

    void testSave() {
        controller.save()

        assert model.rsClienteInstance != null
        assert view == '/rsCliente/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/rsCliente/show/1'
        assert controller.flash.message != null
        assert RsCliente.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/rsCliente/list'

        populateValidParams(params)
        def rsCliente = new RsCliente(params)

        assert rsCliente.save() != null

        params.id = rsCliente.id

        def model = controller.show()

        assert model.rsClienteInstance == rsCliente
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/rsCliente/list'

        populateValidParams(params)
        def rsCliente = new RsCliente(params)

        assert rsCliente.save() != null

        params.id = rsCliente.id

        def model = controller.edit()

        assert model.rsClienteInstance == rsCliente
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/rsCliente/list'

        response.reset()

        populateValidParams(params)
        def rsCliente = new RsCliente(params)

        assert rsCliente.save() != null

        // test invalid parameters in update
        params.id = rsCliente.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/rsCliente/edit"
        assert model.rsClienteInstance != null

        rsCliente.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/rsCliente/show/$rsCliente.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        rsCliente.clearErrors()

        populateValidParams(params)
        params.id = rsCliente.id
        params.version = -1
        controller.update()

        assert view == "/rsCliente/edit"
        assert model.rsClienteInstance != null
        assert model.rsClienteInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/rsCliente/list'

        response.reset()

        populateValidParams(params)
        def rsCliente = new RsCliente(params)

        assert rsCliente.save() != null
        assert RsCliente.count() == 1

        params.id = rsCliente.id

        controller.delete()

        assert RsCliente.count() == 0
        assert RsCliente.get(rsCliente.id) == null
        assert response.redirectedUrl == '/rsCliente/list'
    }
}
