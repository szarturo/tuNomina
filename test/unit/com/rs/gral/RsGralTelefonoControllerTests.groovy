package com.rs.gral



import org.junit.*
import grails.test.mixin.*

@TestFor(RsGralTelefonoController)
@Mock(RsGralTelefono)
class RsGralTelefonoControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/rsGralTelefono/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.rsGralTelefonoInstanceList.size() == 0
        assert model.rsGralTelefonoInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.rsGralTelefonoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.rsGralTelefonoInstance != null
        assert view == '/rsGralTelefono/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/rsGralTelefono/show/1'
        assert controller.flash.message != null
        assert RsGralTelefono.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/rsGralTelefono/list'


        populateValidParams(params)
        def rsGralTelefono = new RsGralTelefono(params)

        assert rsGralTelefono.save() != null

        params.id = rsGralTelefono.id

        def model = controller.show()

        assert model.rsGralTelefonoInstance == rsGralTelefono
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/rsGralTelefono/list'


        populateValidParams(params)
        def rsGralTelefono = new RsGralTelefono(params)

        assert rsGralTelefono.save() != null

        params.id = rsGralTelefono.id

        def model = controller.edit()

        assert model.rsGralTelefonoInstance == rsGralTelefono
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/rsGralTelefono/list'

        response.reset()


        populateValidParams(params)
        def rsGralTelefono = new RsGralTelefono(params)

        assert rsGralTelefono.save() != null

        // test invalid parameters in update
        params.id = rsGralTelefono.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/rsGralTelefono/edit"
        assert model.rsGralTelefonoInstance != null

        rsGralTelefono.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/rsGralTelefono/show/$rsGralTelefono.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        rsGralTelefono.clearErrors()

        populateValidParams(params)
        params.id = rsGralTelefono.id
        params.version = -1
        controller.update()

        assert view == "/rsGralTelefono/edit"
        assert model.rsGralTelefonoInstance != null
        assert model.rsGralTelefonoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/rsGralTelefono/list'

        response.reset()

        populateValidParams(params)
        def rsGralTelefono = new RsGralTelefono(params)

        assert rsGralTelefono.save() != null
        assert RsGralTelefono.count() == 1

        params.id = rsGralTelefono.id

        controller.delete()

        assert RsGralTelefono.count() == 0
        assert RsGralTelefono.get(rsGralTelefono.id) == null
        assert response.redirectedUrl == '/rsGralTelefono/list'
    }
}
