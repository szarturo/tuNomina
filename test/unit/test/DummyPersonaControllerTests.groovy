package test



import org.junit.*
import grails.test.mixin.*

@TestFor(DummyPersonaController)
@Mock(DummyPersona)
class DummyPersonaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/dummyPersona/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.dummyPersonaInstanceList.size() == 0
        assert model.dummyPersonaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.dummyPersonaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.dummyPersonaInstance != null
        assert view == '/dummyPersona/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/dummyPersona/show/1'
        assert controller.flash.message != null
        assert DummyPersona.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/dummyPersona/list'

        populateValidParams(params)
        def dummyPersona = new DummyPersona(params)

        assert dummyPersona.save() != null

        params.id = dummyPersona.id

        def model = controller.show()

        assert model.dummyPersonaInstance == dummyPersona
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/dummyPersona/list'

        populateValidParams(params)
        def dummyPersona = new DummyPersona(params)

        assert dummyPersona.save() != null

        params.id = dummyPersona.id

        def model = controller.edit()

        assert model.dummyPersonaInstance == dummyPersona
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/dummyPersona/list'

        response.reset()

        populateValidParams(params)
        def dummyPersona = new DummyPersona(params)

        assert dummyPersona.save() != null

        // test invalid parameters in update
        params.id = dummyPersona.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/dummyPersona/edit"
        assert model.dummyPersonaInstance != null

        dummyPersona.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/dummyPersona/show/$dummyPersona.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        dummyPersona.clearErrors()

        populateValidParams(params)
        params.id = dummyPersona.id
        params.version = -1
        controller.update()

        assert view == "/dummyPersona/edit"
        assert model.dummyPersonaInstance != null
        assert model.dummyPersonaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/dummyPersona/list'

        response.reset()

        populateValidParams(params)
        def dummyPersona = new DummyPersona(params)

        assert dummyPersona.save() != null
        assert DummyPersona.count() == 1

        params.id = dummyPersona.id

        controller.delete()

        assert DummyPersona.count() == 0
        assert DummyPersona.get(dummyPersona.id) == null
        assert response.redirectedUrl == '/dummyPersona/list'
    }
}
