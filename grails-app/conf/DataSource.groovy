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
		dataSource {
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
		dataSource {
			driverClassName = 'oracle.jdbc.driver.OracleDriver'
			username = 'nominaTest'
			password = 'nominaTest'
			url = 'jdbc:oracle:thin:@localhost:1521:XE'
			hibernate.current_session_context_class = 'com.autobizlogic.abl.session.CurrentSessionContextProxy'
		}
	}
	
	production {
		dataSource {
			driverClassName = 'oracle.jdbc.driver.OracleDriver'
			username = 'nominaProd'
			password = 'nominaProd'
			url = 'jdbc:oracle:thin:@localhost:1521:XE'
			hibernate.current_session_context_class = 'com.autobizlogic.abl.session.CurrentSessionContextProxy'
		}
	}

}