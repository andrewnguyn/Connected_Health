package connectedhealth

import com.google.gson.JsonArray
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.web.json.JSONArray
import org.codehaus.groovy.grails.web.json.JSONException
import org.codehaus.groovy.grails.web.json.JSONObject
import spock.lang.Specification
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(JournalEntryController)
@Mock([JournalEntry, Patient])
class JournalEntryControllerSpec extends Specification {

    private static Patient p1;
    private static int PATIENT1_ID;

    def setup() {
        Date currentDate = new Date()

        p1 = new Patient(firstName: "John", lastName: "Smith", homeAddress: "123 Example St Fakeville", phone: "12340987", contactEmail: "john@smith.ru")
        p1.save()
        PATIENT1_ID = p1.getId()

        JournalEntry b = new JournalEntry(patient: p1, content: "This is my small Journal", created: currentDate, updated: currentDate)
        b.save()

        b = new JournalEntry(patient: p1, content: "A second Journal", created: currentDate, updated: currentDate)
        b.save()
    }

    def cleanup() {
    }

    //entries tests
    void "entries no patient ID test"() {
        when:
        controller.entries()

        then:
        response.status == 404
        response.text == "Patient ID is required"
    }

    void "entries patient ID doesn't exist test"() {
        when:
        params.patientID = PATIENT1_ID + 100       // patient with this id doesn't exist
        controller.entries()

        then:
        response.status == 404
        response.text != "[]"
        response.text == "Patient with this ID does not exist"
    }

    void "entries valid patient ID test"() {
        when:
        params.patientID = PATIENT1_ID
        controller.entries()

        then:
        response.status == 200
        response.text != "[]"
        response.text != "Patient ID is required"
    }

    void "entries response format must be JSONArray"() {
        when:
        params.patientID = PATIENT1_ID
        controller.entries()

        then:
        response.status == 200
        try{
            JSONArray journalList = new JSONArray(response.text)
        }catch (JSONException ex){
            fail("Could not parse the response string to JSONArray")
        }
    }


    void "entries response content correctly"() {
        when:
        params.patientID = p1.getId()
        controller.entries()

        then:
        response.status == 200
        JSONArray journalList = new JSONArray(response.text)
        try{
            for (int i = 0 ; i < journalList.length();i++){
                JSONObject singleJournal = journalList.getJSONObject(i)
                String content = singleJournal.getString("content")
                if(singleJournal.getInt("ID") == 1){
                    content == "This is my small Journal"
                }else if(singleJournal.getInt("ID") == 2){
                    content == "A second Journal"
                }
            }
        }catch (JSONException ex){
            fail("Journal Array doesn't contains correct content")
        }
    }

    //create new journal entry
    void "new entries no patient ID test"() {
        when:
        controller.newEntry()

        then:
        response.status == 404
        response.text == "Patient ID is required"
    }

    void "new entries no content test"() {
        when:
        params.patientID = p1.getId()
        controller.newEntry()

        then:
        response.status == 404
        response.text == "Content is required"
    }

    void "new entries valid input and content"() {
        when:
        params.patientID = p1.getId()
        params.content = "testing used new journal entry content"
        controller.newEntry()

        then:
        response.status == 200
        response.text == "Journal Entry Created"
    }

    void "new entries validate the created new entry"() {
        when:
        params.patientID = p1.getId()
        params.content = "testing used new journal entry content"
        controller.newEntry()

        then:
        response.status == 200
        JournalEntry[] jn = JournalEntry.findAllByPatient(p1)
        boolean found = false;
        for (int i = 0; i < jn.size(); i++) {
            if (jn[i].getContent() == params.content) {
                found = true
            }
        }
        if (!found) {
            fail("new journal entry not created")
        }
    }

    //update a journal entry
    void "update entries no patient ID test"() {
        when:
        controller.updateEntry()

        then:
        response.status == 404
        response.text == "Patient ID is required"
    }

    void "update entries no Journal ID test"() {
        when:
        params.patientID = p1.getId()
        params.content = "update test content here"
        controller.updateEntry()

        then:
        response.status == 404
        response.text == "Journal Entry ID is required"
    }

    void "update entries no content test"() {
        when:
        params.patientID = p1.getId()
        params.journalEntryID = "1"
        controller.updateEntry()

        then:
        response.status == 404
        response.text == "Journal content is required"
    }

    void "update entries valid request test"() {
        when:
        params.patientID = p1.getId()
        params.journalEntryID = "1"
        params.content = "update test content here"
        controller.updateEntry()

        then:
        response.status == 200
        response.text == "Journal Entry Updated"
    }

    void "update entries correct updated test"() {
        when:
        params.patientID = p1.getId()
        params.journalEntryID = "1"
        params.content = "update test content here"
        controller.updateEntry()

        then:
        response.status == 200
        JournalEntry jn = JournalEntry.findById("1")
        jn.getContent() == "update test content here"
    }

    //remove journal action
    void "remove entries no patient ID test"() {
        when:
        controller.removeEntry()

        then:
        response.status == 404
        response.text == "Patient ID is required"
    }

    void "remove entries no Journal ID test"() {
        when:
        params.patientID = p1.getId()
        controller.removeEntry()

        then:
        response.status == 404
        response.text == "Journal Entry ID is required"
    }

    void "remove entries with valid request"() {
        when:
        params.patientID = p1.getId()
        params.journalEntryID = "1"
        controller.removeEntry()

        then:
        response.status == 200
        response.text == "Journal Entry Deleted"
    }

    void "remove entries remove journal correctly"() {
        when:
        params.patientID = p1.getId()
        params.journalEntryID = "1"
        controller.removeEntry()

        then:
        response.status == 200
        JournalEntry jn = JournalEntry.findById("1")
        jn == null
    }

}
