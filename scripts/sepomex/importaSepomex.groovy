@Grab('com.xlson.groovycsv:groovycsv:1.0')
import com.xlson.groovycsv.CsvParser
import groovy.sql.Sql

//CONEXION A LA BASE DE DATOS
sql = Sql.newInstance("jdbc:oracle:thin:@localhost:1521:XE", "plPrueba","plPrueba" , "oracle.jdbc.driver.OracleDriver")

def claveEstado
def nombreEstadoArchivo

def estados = [1:"Baja California Sur.csv", 2:"Colima.csv"]

estados.each{

	claveEstado = it.key
	nombreEstadoArchivo = it.value
	
	def row
	
	//PARSEA EL ARCHIVO
	def csv = new FileReader(new File("${nombreEstadoArchivo}"))
	def data = new CsvParser().parse(csv, separator: ',',readFirstLine:false,
		columnNames:['d_codigo','d_asenta', 'd_tipo_asenta','D_mnpio','d_estado','d_ciudad','d_CP','c_estado','c_oficina','c_tipo_asenta',
					 'c_mnpio','id_asenta_cpcons','d_zona','c_cve_ciudad','c_CP'])
	
	def id = 0
	
	//OBTIENE EL ID ACTUAL MAXIMO DE LA TABLA DELEGACION O MUNICIPIO
	row = sql.firstRow("select nvl(max(id)+1,1) ID from rs_gral_delegacion_municipio")
	id = row.ID
	
	println ("ID DELEGACION MUNICIPIO = ${id}")
	
	for(line in data) {
	
		try{

			//INSERTA DELEGACION O MUNICIPIO
			//LA TABLA RS_GRAL_DELEGACION_MUNICIPIO DEBE TENER COMO PRIMARY KEY LOS CAMPOS ESTADO_ID Y NOMBRE_DELEGACION_MUNICIPIO
			sql.execute(""" insert into RS_GRAL_DELEGACION_MUNICIPIO
							(ID, 
							 VERSION, 
							 ESTADO_ID, 
							 NOMBRE_DELEGACION_MUNICIPIO,
							 NOMBRE_CIUDAD) 
						values (${id}, 
								0,
								${claveEstado}, 
								${line.D_mnpio},
								${line.d_ciudad}) """)
			
			
			id = id+1
		}catch(java.sql.SQLIntegrityConstraintViolationException exception){
			//NO INSERTA EL REGISTRA YA QUE EXISTE
		}
	}
	
	
	
	//OBTIENE EL ID ACTUAL MAXIMO DE LOS ASENTAMIENTOS
	row = sql.firstRow("select nvl(max(id)+1,1) ID from RS_GRAL_ASENTAMIENTO")
	id = row.ID
	println ("ID COLONIA = ${id}")
	
	def csv1 = new FileReader(new File("${nombreEstadoArchivo}"))
	def data1 = new CsvParser().parse(csv1, separator: ',',readFirstLine:false,
		columnNames:['d_codigo','d_asenta', 'd_tipo_asenta','D_mnpio','d_estado','d_ciudad','d_CP','c_estado','c_oficina','c_tipo_asenta',
					 'c_mnpio','id_asenta_cpcons','d_zona','c_cve_ciudad','c_CP'])
	
	for(line in data1) {
	
		row = sql.firstRow("SELECT ID FROM RS_GRAL_DELEGACION_MUNICIPIO WHERE NOMBRE_DELEGACION_MUNICIPIO = ${line.D_mnpio} AND ESTADO_ID = ${claveEstado}")
		//println "SELECT ID FROM RS_GRAL_DELEGACION_MUNICIPIO WHERE NOMBRE_DELEGACION_MUNICIPIO = ${line.D_mnpio} AND ESTADO_ID = ${claveEstado}"
		
		if (row){
			//INSERTA EL ASENTAMIENTO
			sql.execute("""Insert into RS_GRAL_ASENTAMIENTO
							(ID,
							VERSION,
							CODIGO_POSTAL,
							DELEGACION_MUNICIPIO_ID,
							NOMBRE_ASENTAMIENTO,
							TIPO_ASENTAMIENTO) 
						values (
							${id},
							0,
							${line.d_codigo},
							${row.ID},
							${line.d_asenta},
							${line.d_tipo_asenta})""") 
				
			id = id+1
		}
	}
		
	
}

println "HA CONCLUIDO"
sql.close()
