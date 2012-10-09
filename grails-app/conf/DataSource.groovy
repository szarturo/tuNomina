dataSource {
	pooled = true
	driverClassName = "com.mysql.jdbc.Driver"
	username = "root"
	password = "system"
	//loggingSql = true
	//Mostrar consultas en la consola
}

hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
	development {
		
		//DEPLOY EN TOMCAT LOCAL
		dataSource {
			//url = "jdbc:mysql://localhost:3306/simGrails2"
			driverClassName = 'oracle.jdbc.driver.OracleDriver'
			username = 'nomina'
			password = 'nomina'
			url = 'jdbc:oracle:thin:@localhost:1521:XE'
			dbCreate = 'create-drop'
			hibernate.current_session_context_class = 'com.autobizlogic.abl.session.CurrentSessionContextProxy'
		}
		
		//EJEMPLO DE COMO USAR UN SEGUNDO DATASOURCE
		/*
		dataSource_activiti {
			driverClassName = "com.mysql.jdbc.Driver"
			username = "root"
			password = "system"
			dbCreate = "update"
			url = "jdbc:mysql://localhost:3306/activiti"
		}*/
		
	}
	
	test {
		//DEPLOY EN JELASTIC
		dataSource {
			username = "mrugerio"
			password = "creditos"
			dbCreate = "create-drop"
			url = "jdbc:mysql://mysql-simcreditos.jelastic.servint.net/simGrails2"
		}
	}
	production {
		//DEPLOY EN CLOUDFOUNDRY
		dataSource {
			dialect= org.hibernate.dialect.MySQLInnoDBDialect
			driverClassName = "com.mysql.jdbc.Driver"
			username = "n/a"
			password = "n/a"
			url = "n/a"
			dbCreate = "update"
		}
	}

}
