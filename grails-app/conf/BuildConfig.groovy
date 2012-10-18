grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

//CAMBIO DE PUERTO
grails.server.port.http=8088

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()
		mavenRepo name: "Activiti", root: "https://maven.alfresco.com/nexus/content/groups/public"
		mavenRepo "http://resources.automatedbusinesslogic.com/maven2"

        // uncomment these to enable remote dependency resolution from public Maven repositories
        //mavenCentral()
        //mavenLocal()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
		compile ('com.autobizlogic.abl:autobizlogic:2.1.6'){  
            excludes 'slf4j-log4j12'
		}
        runtime 'mysql:mysql-connector-java:5.1.16'
		runtime 'mylib:BusLogicExt:1.0'
		// LIBRERIAS PARA CLIENTE DE WEBSERVICES
		compile 'mylib:credito-client:1.0'
		runtime 'mylib:axis:1.0'
		runtime 'mylib:commons-discovery:0.2'
		runtime 'mylib:commons-logging:1.0'
		runtime 'mylib:jaxrpc:1.0'
		runtime 'mylib:saaj:1.0'
		runtime 'mylib:wsdl4j:1.0'

    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.7.1"
        runtime ":resources:1.2.RC2"
		
        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        build   ":tomcat:$grailsVersion"
		compile ":activiti-spring-security:0.4.9"
		 
		compile ":jquery-ui:1.8.15"
		compile ":modernizr:2.5.3"
		compile ":uploadr:0.5.8"
    }
}
