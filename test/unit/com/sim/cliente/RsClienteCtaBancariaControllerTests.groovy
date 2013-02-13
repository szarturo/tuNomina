package com.sim.cliente



import org.junit.*
import grails.test.mixin.*

@TestFor(RsClienteCtaBancariaController)
@Mock(RsClienteCtaBancaria)
class RsClienteCtaBancariaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/rsClienteCtaBancaria/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.rsClienteCtaBancariaInstanceList.size() == 0
        assert model.rsClienteCtaBancariaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.rsClienteCtaBancariaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.rsClienteCtaBancariaInstance != null
        assert view == '/rsClienteCtaBancaria/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/rsClienteCtaBancaria/show/1'
        assert controller.flash.message != null
        assert RsClienteCtaBancaria.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/rsClienteCtaBancaria/list'

        populateValidParams(params)
        def rsClienteCtaBancaria = new RsClienteCtaBancaria(params)

        assert rsClienteCtaBancaria.save() != null

        params.id = rsClienteCtaBancaria.id

        def model = controller.show()

        assert model.rsClienteCtaBancariaInstance == rsClienteCtaBancaria
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/rsClienteCtaBancaria/list'

        populateValidParams(params)
        def rsClienteCtaBancaria = new RsClienteCtaBancaria(params)

        assert rsClienteCtaBancaria.save() != null

        params.id = rsClienteCtaBancaria.id

        def model = controller.edit()

        assert model.rsClienteCtaBancariaInstance == rsClienteCtaBancaria
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/rsClienteCtaBancaria/list'

        response.reset()

        populateValidParams(params)
        def rsClienteCtaBancaria = new RsClienteCtaBancaria(params)

        assert rsClienteCtaBancaria.save() != null

        // test invalid parameters in update
        params.id = rsClienteCtaBancaria.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/rsClienteCtaBancaria/edit"
        assert model.rsClienteCtaBancariaInstance != null

        rsClienteCtaBancaria.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/rsClienteCtaBancaria/show/$rsClienteCtaBancaria.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        rsClienteCtaBancaria.clearErrors()

        populateValidParams(params)
        params.id = rsClienteCtaBancaria.id
        params.version = -1
        controller.update()

        assert view == "/rsClienteCtaBancaria/edit"
        assert model.rsClienteCtaBancariaInstance != null
        assert model.rsClienteCtaBancariaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/rsClienteCtaBancaria/list'

        response.reset()

        populateValidParams(params)
        def rsClienteCtaBancaria = new RsClienteCtaBancaria(params)

        assert rsClienteCtaBancaria.save() != null
        assert RsClienteCtaBancaria.count() == 1

        params.id = rsClienteCtaBancaria.id

        controller.delete()

        assert RsClienteCtaBancaria.count() == 0
        assert RsClienteCtaBancaria.get(rsClienteCtaBancaria.id) == null
        assert response.redirectedUrl == '/rsClienteCtaBancaria/list'
    }
}
