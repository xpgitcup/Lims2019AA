package cn.edu.cup.common


import cn.edu.cup.system.QueryStatement
import grails.gorm.transactions.Transactional

@Transactional
class CommonQueryService {

    def queryStatementService
    def dataSource

    def listFunction(params) {
        def result = [:]
        def keyString = generateKeyString(params)
        def objectList
        result.view = "default"
        def pl = []
        def queryStatement = QueryStatement.findByKeyString(keyString)
        if (queryStatement) {
            if (queryStatement.hql || queryStatement.viewName) {
                if (queryStatement.paramsList) {
                    pl.addAll(queryStatement.paramsList.split(","))
                }
                result.view = queryStatement.viewName
                def ps = [:]
                ps.offset = params.offset
                ps.max = params.max
                pl.each { e ->
                    ps.put(e, params.get(e))
                }
                println("list 参数：${ps}")
                if (queryStatement.isSQL) {
                    def db = new groovy.sql.Sql(dataSource)
                    println("执行SQL ${queryStatement.hql}")
                    objectList = db.rows(ps, queryStatement.hql)
                    println("列表SQL: ${objectList}")
                } else {
                    objectList = QueryStatement.executeQuery(queryStatement.hql, ps)
                    result.objectList = objectList
                }
            } else {
                result.message = "请完善list查询."
            }
        } else {
            def nq = new QueryStatement(keyString: keyString);
            queryStatementService.save(nq)
            result.message = "创建新的list查询."
        }
        return result
    }

    Object countFunction(params) {
        def keyString = generateKeyString(params)
        def result = [:]
        result.count = -1
        result.message = "请完善count查询!"
        def queryStatement = QueryStatement.findByKeyString(keyString)
        def pl = []
        if (queryStatement) {
            println("统计语句； ${queryStatement.hql}")
            if (queryStatement.hql!='') {
                if (queryStatement.paramsList) {
                    pl.addAll(queryStatement.paramsList.split(","))
                }
                def ps = [:]
                pl.each { e ->
                    ps.put(e, params.get(e))
                }
                println("count 参数：${ps}")
                // 区分HQL以及SQL
                if (queryStatement.isSQL) {
                    def db = new groovy.sql.Sql(dataSource)
                    println("执行SQL ${queryStatement.hql}")
                    def c = db.rows(ps, queryStatement.hql)
                    count = [c[0].getProperty('count(*)')]
                    println("SQL 执行结果：${count}")
                } else {
                    count = QueryStatement.executeQuery(queryStatement.hql, ps)
                }
            } else {
                message = "请完善count查询."
            }
        } else {
            def nq = new QueryStatement(keyString: keyString);
            queryStatementService.save(nq)
            count = -1
        }
        result
    }

    private def generateKeyString(params) {
        println("内部：${params}")
        def keyString = ""
        def exclude = ["offset", "max", "id", "format"]
        def include = ["controller", "action", "key"]
        params.keySet().sort().each { e ->
            if (!exclude.contains(e)) {
                if (include.contains(e)) {
                    if (keyString.isEmpty()) {
                        keyString += "${params.get(e)}"
                    } else {
                        keyString += ".${params.get(e)}"
                    }
                } else {
                    if (keyString.isEmpty()) {
                        keyString += "${e}"
                    } else {
                        keyString += ".${e}"
                    }
                }
            }
        }
        println("Query ${keyString}")
        keyString
    }


}
