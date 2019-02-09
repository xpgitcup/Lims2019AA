package cn.edu.cup.system

class QueryStatement {

    String keyString
    String hql
    boolean isSQL = false
    String viewName
    String paramsList
    String discription
    Integer refCount = 0

    static constraints = {
        keyString(unique: true)
        hql(nullable: true)
        isSQL(nullable: true)
        viewName(nullable: true)
        paramsList(nullable: true)
        discription(nullable: true)
        refCount()
    }

    String toString() {
        return "${keyString}"
    }
}
