package com.sim.cliente



import org.junit.*
import grails.test.mixin.*

@TestFor(RsClienteEmpleoController)
@Mock(RsClienteEmpleo)
class RsClienteEmpleoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/rsClienteEmpleo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.rsClienteEmpleoInstanceList.size() == 0
        assert model.rsClienteEmpleoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.rsClienteEmpleoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.rsClienteEmpleoInstance != null
        assert view == '/rsClienteEmpleo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/rsClienteEmpleo/show/1'
        assert controller.flash.message != null
        assert RsClienteEmpleo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/rsClienteEmpleo/list'

        populateValidParams(params)
        def rsClienteEmpleo = new RsClienteEmpleo(params)

        assert rsClienteEmpleo.save() != null

        params.id = rsClienteEmpleo.id

        def model = controller.show()

        assert model.rsClienteEmpleoInstance == rsClienteEmpleo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/rsClienteEmpleo/list'

        populateValidParams(params)
        def rsClienteEmpleo = new RsClienteEmpleo(params)

        assert rsClienteEmpleo.save() != null

        params.id = rsClienteEmpleo.id

        def model = controller.edit()

        assert model.rsClienteEmpleoInstance == rsClienteEmpleo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/rsClienteEmpleo/list'

        response.reset()

        populateValidParams(params)
        def rsClienteEmpleo = new RsClienteEmpleo(params)

        assert rsClienteEmpleo.save() != null

        // test invalid parameters in update
        params.id = rsClienteEmpleo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/rsClienteEmpleo/edit"
        assert model.rsClienteEmpleoInstance != null

        rsClienteEmpleo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/rsClienteEmpleo/show/$rsClienteEmpleo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        rsClienteEmpleo.clearErrors()

        populateValidParams(params)
        params.id = rsClienteEmpleo.id
        params.version = -1
        controller.update()

        assert view == "/rsClienteEmpleo/edit"
        assert model.rsClienteEmpleoInstance != null
        assert model.rsClienteEmpleoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/rsClienteEmpleo/list'

        response.reset()

        populateValidParams(params)
        def rsClienteEmpleo = new RsClienteEmpleo(params)

        assert rsClienteEmpleo.save() != null
        assert RsClienteEmpleo.count() == 1

        params.id = rsClienteEmpleo.id

        controller.delete()

        assert RsClienteEmpleo.count() == 0
        assert RsClienteEmpleo.get(rsClienteEmpleo.id) == null
        assert response.redirectedUrl == '/rsClienteEmpleo/list'
    }
}
