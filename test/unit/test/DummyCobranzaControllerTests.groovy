package test



import org.junit.*
import grails.test.mixin.*

@TestFor(DummyCobranzaController)
@Mock(DummyCobranza)
class DummyCobranzaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/dummyCobranza/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.dummyCobranzaInstanceList.size() == 0
        assert model.dummyCobranzaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.dummyCobranzaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.dummyCobranzaInstance != null
        assert view == '/dummyCobranza/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/dummyCobranza/show/1'
        assert controller.flash.message != null
        assert DummyCobranza.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/dummyCobranza/list'

        populateValidParams(params)
        def dummyCobranza = new DummyCobranza(params)

        assert dummyCobranza.save() != null

        params.id = dummyCobranza.id

        def model = controller.show()

        assert model.dummyCobranzaInstance == dummyCobranza
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/dummyCobranza/list'

        populateValidParams(params)
        def dummyCobranza = new DummyCobranza(params)

        assert dummyCobranza.save() != null

        params.id = dummyCobranza.id

        def model = controller.edit()

        assert model.dummyCobranzaInstance == dummyCobranza
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/dummyCobranza/list'

        response.reset()

        populateValidParams(params)
        def dummyCobranza = new DummyCobranza(params)

        assert dummyCobranza.save() != null

        // test invalid parameters in update
        params.id = dummyCobranza.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/dummyCobranza/edit"
        assert model.dummyCobranzaInstance != null

        dummyCobranza.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/dummyCobranza/show/$dummyCobranza.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        dummyCobranza.clearErrors()

        populateValidParams(params)
        params.id = dummyCobranza.id
        params.version = -1
        controller.update()

        assert view == "/dummyCobranza/edit"
        assert model.dummyCobranzaInstance != null
        assert model.dummyCobranzaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/dummyCobranza/list'

        response.reset()

        populateValidParams(params)
        def dummyCobranza = new DummyCobranza(params)

        assert dummyCobranza.save() != null
        assert DummyCobranza.count() == 1

        params.id = dummyCobranza.id

        controller.delete()

        assert DummyCobranza.count() == 0
        assert DummyCobranza.get(dummyCobranza.id) == null
        assert response.redirectedUrl == '/dummyCobranza/list'
    }
}
