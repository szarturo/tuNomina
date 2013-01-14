package com.sim.listacobro



import org.junit.*
import grails.test.mixin.*

@TestFor(ListaCobroDetalleController)
@Mock(ListaCobroDetalle)
class ListaCobroDetalleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/listaCobroDetalle/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.listaCobroDetalleInstanceList.size() == 0
        assert model.listaCobroDetalleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.listaCobroDetalleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.listaCobroDetalleInstance != null
        assert view == '/listaCobroDetalle/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/listaCobroDetalle/show/1'
        assert controller.flash.message != null
        assert ListaCobroDetalle.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/listaCobroDetalle/list'

        populateValidParams(params)
        def listaCobroDetalle = new ListaCobroDetalle(params)

        assert listaCobroDetalle.save() != null

        params.id = listaCobroDetalle.id

        def model = controller.show()

        assert model.listaCobroDetalleInstance == listaCobroDetalle
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/listaCobroDetalle/list'

        populateValidParams(params)
        def listaCobroDetalle = new ListaCobroDetalle(params)

        assert listaCobroDetalle.save() != null

        params.id = listaCobroDetalle.id

        def model = controller.edit()

        assert model.listaCobroDetalleInstance == listaCobroDetalle
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/listaCobroDetalle/list'

        response.reset()

        populateValidParams(params)
        def listaCobroDetalle = new ListaCobroDetalle(params)

        assert listaCobroDetalle.save() != null

        // test invalid parameters in update
        params.id = listaCobroDetalle.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/listaCobroDetalle/edit"
        assert model.listaCobroDetalleInstance != null

        listaCobroDetalle.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/listaCobroDetalle/show/$listaCobroDetalle.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        listaCobroDetalle.clearErrors()

        populateValidParams(params)
        params.id = listaCobroDetalle.id
        params.version = -1
        controller.update()

        assert view == "/listaCobroDetalle/edit"
        assert model.listaCobroDetalleInstance != null
        assert model.listaCobroDetalleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/listaCobroDetalle/list'

        response.reset()

        populateValidParams(params)
        def listaCobroDetalle = new ListaCobroDetalle(params)

        assert listaCobroDetalle.save() != null
        assert ListaCobroDetalle.count() == 1

        params.id = listaCobroDetalle.id

        controller.delete()

        assert ListaCobroDetalle.count() == 0
        assert ListaCobroDetalle.get(listaCobroDetalle.id) == null
        assert response.redirectedUrl == '/listaCobroDetalle/list'
    }
}
