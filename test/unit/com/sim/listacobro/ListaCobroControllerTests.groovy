package com.sim.listacobro



import org.junit.*
import grails.test.mixin.*

@TestFor(ListaCobroController)
@Mock(ListaCobro)
class ListaCobroControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/listaCobro/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.listaCobroInstanceList.size() == 0
        assert model.listaCobroInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.listaCobroInstance != null
    }

    void testSave() {
        controller.save()

        assert model.listaCobroInstance != null
        assert view == '/listaCobro/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/listaCobro/show/1'
        assert controller.flash.message != null
        assert ListaCobro.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/listaCobro/list'

        populateValidParams(params)
        def listaCobro = new ListaCobro(params)

        assert listaCobro.save() != null

        params.id = listaCobro.id

        def model = controller.show()

        assert model.listaCobroInstance == listaCobro
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/listaCobro/list'

        populateValidParams(params)
        def listaCobro = new ListaCobro(params)

        assert listaCobro.save() != null

        params.id = listaCobro.id

        def model = controller.edit()

        assert model.listaCobroInstance == listaCobro
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/listaCobro/list'

        response.reset()

        populateValidParams(params)
        def listaCobro = new ListaCobro(params)

        assert listaCobro.save() != null

        // test invalid parameters in update
        params.id = listaCobro.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/listaCobro/edit"
        assert model.listaCobroInstance != null

        listaCobro.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/listaCobro/show/$listaCobro.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        listaCobro.clearErrors()

        populateValidParams(params)
        params.id = listaCobro.id
        params.version = -1
        controller.update()

        assert view == "/listaCobro/edit"
        assert model.listaCobroInstance != null
        assert model.listaCobroInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/listaCobro/list'

        response.reset()

        populateValidParams(params)
        def listaCobro = new ListaCobro(params)

        assert listaCobro.save() != null
        assert ListaCobro.count() == 1

        params.id = listaCobro.id

        controller.delete()

        assert ListaCobro.count() == 0
        assert ListaCobro.get(listaCobro.id) == null
        assert response.redirectedUrl == '/listaCobro/list'
    }
}
