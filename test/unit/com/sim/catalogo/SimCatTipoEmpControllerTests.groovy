package com.sim.catalogo

import grails.test.mixin.*

@TestFor(SimCatTipoEmpController)
@Mock(SimCatTipoEmp)
class SimCatTipoEmpControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/simCatTipoEmpleadoDep/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.simCatTipoEmpleadoDepInstanceList.size() == 0
        assert model.simCatTipoEmpleadoDepInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.simCatTipoEmpleadoDepInstance != null
    }

    void testSave() {
        controller.save()

        assert model.simCatTipoEmpleadoDepInstance != null
        assert view == '/simCatTipoEmpleadoDep/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/simCatTipoEmpleadoDep/show/1'
        assert controller.flash.message != null
        assert SimCatTipoEmp.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/simCatTipoEmpleadoDep/list'

        populateValidParams(params)
        def simCatTipoEmpleadoDep = new SimCatTipoEmp(params)

        assert simCatTipoEmpleadoDep.save() != null

        params.id = simCatTipoEmpleadoDep.id

        def model = controller.show()

        assert model.simCatTipoEmpleadoDepInstance == simCatTipoEmpleadoDep
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/simCatTipoEmpleadoDep/list'

        populateValidParams(params)
        def simCatTipoEmpleadoDep = new SimCatTipoEmp(params)

        assert simCatTipoEmpleadoDep.save() != null

        params.id = simCatTipoEmpleadoDep.id

        def model = controller.edit()

        assert model.simCatTipoEmpleadoDepInstance == simCatTipoEmpleadoDep
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/simCatTipoEmpleadoDep/list'

        response.reset()

        populateValidParams(params)
        def simCatTipoEmpleadoDep = new SimCatTipoEmp(params)

        assert simCatTipoEmpleadoDep.save() != null

        // test invalid parameters in update
        params.id = simCatTipoEmpleadoDep.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/simCatTipoEmpleadoDep/edit"
        assert model.simCatTipoEmpleadoDepInstance != null

        simCatTipoEmpleadoDep.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/simCatTipoEmpleadoDep/show/$simCatTipoEmpleadoDep.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        simCatTipoEmpleadoDep.clearErrors()

        populateValidParams(params)
        params.id = simCatTipoEmpleadoDep.id
        params.version = -1
        controller.update()

        assert view == "/simCatTipoEmpleadoDep/edit"
        assert model.simCatTipoEmpleadoDepInstance != null
        assert model.simCatTipoEmpleadoDepInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/simCatTipoEmpleadoDep/list'

        response.reset()

        populateValidParams(params)
        def simCatTipoEmpleadoDep = new SimCatTipoEmp(params)

        assert simCatTipoEmpleadoDep.save() != null
        assert SimCatTipoEmp.count() == 1

        params.id = simCatTipoEmpleadoDep.id

        controller.delete()

        assert SimCatTipoEmp.count() == 0
        assert SimCatTipoEmp.get(simCatTipoEmpleadoDep.id) == null
        assert response.redirectedUrl == '/simCatTipoEmpleadoDep/list'
    }
}
