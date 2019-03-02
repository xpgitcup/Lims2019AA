package cn.edu.cup.lims

class Team {

    Person leader
    Thing thing

    static hasMany = [members: Person, progresses: Progress]

    static constraints = {
        leader()
        thing()
    }

    static mapping = {
        sort('leader')
        members sort: 'name'  //这是排序的标准做法
    }

    String toString() {
        return "${thing}.${leader}"
    }
}
