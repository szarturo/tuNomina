package demo

class Address {

    String line1
    String line2
    String city
    String state
    String zip

    static belongsTo = [contact: Contact]

    static constraints = {
      line1(blank: false)
      line2(nullable: true)
      city(blank: false)
      state(blank: false)
      zip(blank: false)
    }
}
