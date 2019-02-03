package cn.edu.cup.lims

class QueryStatement {

    String keyString
    String hql
    boolean isSQL = false
    String viewName
    String paramsList
    String discription

    static constraints = {
        keyString(unique: true)
        hql(nullable: true)
        isSQL(nullable: true)
        viewName(nullable: true)
        paramsList(nullable: true)
        discription(nullable: true)
    }

    String toString() {
        return "${keyString}"
    }
}
