package connectedhealth

class Patient {

    String contactEmail;
    String firstName;
    String homeAddress;
    String lastName;
    String phone;

    static hasMany = [journalEntries: JournalEntry, medicalNotes: MedicalNote]

    static constraints = {
    }
}