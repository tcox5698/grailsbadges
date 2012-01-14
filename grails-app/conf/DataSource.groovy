dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "validate" // one of 'create', 'create-drop', 'update', 'validate', ''
            driverClassName = "org.postgresql.Driver"
            dialect = "org.hibernate.dialect.PostgreSQLDialect"
            url = "jdbc:postgresql://localhost/mbgrails_dev"
            username = "postgres"
            password = "password"
        }
    }
    test {
        dataSource {
            dbCreate = "validate" // one of 'create', 'create-drop', 'update', 'validate', ''
            driverClassName = "org.postgresql.Driver"
            dialect = "org.hibernate.dialect.PostgreSQLDialect"
            url = "jdbc:postgresql://localhost/mbgrails_test"
            username = "postgres"
            password = "password"
        }
    }
    production {
        dataSource {
            dbCreate = "validate" // one of 'create', 'create-drop', 'update', 'validate', ''
            driverClassName = "org.postgresql.Driver"
            dialect = "org.hibernate.dialect.PostgreSQLDialect"
            //uri = new URI(System.env.DATABASE_URL)
    
            //postgres://zpeakbajrn:bLSv_jzZC_00tWTLE1Qn@ec2-107-21-110-190.compute-1.amazonaws.com/zpeakbajrn
    
            url = "jdbc:postgresql://ec2-107-21-110-190.compute-1.amazonaws.com/zpeakbajrn"
            username = "zpeakbajrn"
            password = "bLSv_jzZC_00tWTLE1Qn"
        }
    }
}
