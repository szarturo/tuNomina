package vacationRequest



import org.junit.*
import grails.test.mixin.*

@TestFor(VacationRequestController)
@Mock(VacationRequest)
class VacationRequestControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/vacationRequest/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.vacationRequestInstanceList.size() == 0
        assert model.vacationRequestInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.vacationRequestInstance != null
    }

    void testSave() {
        controller.save()

        assert model.vacationRequestInstance != null
        assert view == '/vacationRequest/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/vacationRequest/show/1'
        assert controller.flash.message != null
        assert VacationRequest.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/vacationRequest/list'


        populateValidParams(params)
        def vacationRequest = new VacationRequest(params)

        assert vacationRequest.save() != null

        params.id = vacationRequest.id

        def model = controller.show()

        assert model.vacationRequestInstance == vacationRequest
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/vacationRequest/list'


        populateValidParams(params)
        def vacationRequest = new VacationRequest(params)

        assert vacationRequest.save() != null

        params.id = vacationRequest.id

        def model = controller.edit()

        assert model.vacationRequestInstance == vacationRequest
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/vacationRequest/list'

        response.reset()


        populateValidParams(params)
        def vacationRequest = new VacationRequest(params)

        assert vacationRequest.save() != null

        // test invalid parameters in update
        params.id = vacationRequest.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/vacationRequest/edit"
        assert model.vacationRequestInstance != null

        vacationRequest.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/vacationRequest/show/$vacationRequest.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        vacationRequest.clearErrors()

        populateValidParams(params)
        params.id = vacationRequest.id
        params.version = -1
        controller.update()

        assert view == "/vacationRequest/edit"
        assert model.vacationRequestInstance != null
        assert model.vacationRequestInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/vacationRequest/list'

        response.reset()

        populateValidParams(params)
        def vacationRequest = new VacationRequest(params)

        assert vacationRequest.save() != null
        assert VacationRequest.count() == 1

        params.id = vacationRequest.id

        controller.delete()

        assert VacationRequest.count() == 0
        assert VacationRequest.get(vacationRequest.id) == null
        assert response.redirectedUrl == '/vacationRequest/list'
    }
}
