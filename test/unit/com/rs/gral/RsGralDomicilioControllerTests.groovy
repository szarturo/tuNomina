package com.rs.gral



import org.junit.*
import grails.test.mixin.*

@TestFor(RsGralDomicilioController)
@Mock(RsGralDomicilio)
class RsGralDomicilioControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/rsGralDomicilio/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.rsGralDomicilioInstanceList.size() == 0
        assert model.rsGralDomicilioInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.rsGralDomicilioInstance != null
    }

    void testSave() {
        controller.save()

        assert model.rsGralDomicilioInstance != null
        assert view == '/rsGralDomicilio/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/rsGralDomicilio/show/1'
        assert controller.flash.message != null
        assert RsGralDomicilio.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/rsGralDomicilio/list'


        populateValidParams(params)
        def rsGralDomicilio = new RsGralDomicilio(params)

        assert rsGralDomicilio.save() != null

        params.id = rsGralDomicilio.id

        def model = controller.show()

        assert model.rsGralDomicilioInstance == rsGralDomicilio
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/rsGralDomicilio/list'


        populateValidParams(params)
        def rsGralDomicilio = new RsGralDomicilio(params)

        assert rsGralDomicilio.save() != null

        params.id = rsGralDomicilio.id

        def model = controller.edit()

        assert model.rsGralDomicilioInstance == rsGralDomicilio
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/rsGralDomicilio/list'

        response.reset()


        populateValidParams(params)
        def rsGralDomicilio = new RsGralDomicilio(params)

        assert rsGralDomicilio.save() != null

        // test invalid parameters in update
        params.id = rsGralDomicilio.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/rsGralDomicilio/edit"
        assert model.rsGralDomicilioInstance != null

        rsGralDomicilio.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/rsGralDomicilio/show/$rsGralDomicilio.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        rsGralDomicilio.clearErrors()

        populateValidParams(params)
        params.id = rsGralDomicilio.id
        params.version = -1
        controller.update()

        assert view == "/rsGralDomicilio/edit"
        assert model.rsGralDomicilioInstance != null
        assert model.rsGralDomicilioInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/rsGralDomicilio/list'

        response.reset()

        populateValidParams(params)
        def rsGralDomicilio = new RsGralDomicilio(params)

        assert rsGralDomicilio.save() != null
        assert RsGralDomicilio.count() == 1

        params.id = rsGralDomicilio.id

        controller.delete()

        assert RsGralDomicilio.count() == 0
        assert RsGralDomicilio.get(rsGralDomicilio.id) == null
        assert response.redirectedUrl == '/rsGralDomicilio/list'
    }
}
