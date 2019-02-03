package cn.edu.cup.lims

class Team {

    Person leader
    Thing thing

    static hasMany = [members: Person, progresses: Progress]

    static constraints = {
        leader()
        thing()
    }

    String toString() {
        return "${thing}.${leader}"
    }
}
