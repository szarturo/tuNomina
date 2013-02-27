package temporal

import com.rs.gral.RsPersona
import com.sim.catalogo.SimCatAccesorio
import com.sim.catalogo.SimCatDocumento
import com.sim.catalogo.SimCatEscolaridad
import com.sim.catalogo.SimCatFormaEntrega
import com.sim.catalogo.SimCatPeriodicidad
import com.sim.catalogo.SimCatTipoPersona
import com.sim.catalogo.SimCatUnidad
import com.sim.cliente.RsCliente
import com.sim.credito.Prestamo
import com.sim.credito.PrestamoAccesorio
import com.sim.credito.PrestamoEstatus
import com.sim.empresa.EmpEmpleado
import com.sim.entidad.EntDelegacion
import com.sim.entidad.EntDependencia
import com.sim.entidad.EntSucursal
import com.sim.pfin.PfinCatConcepto
import com.sim.producto.ProPromocion

class AltasService {

    boolean altaClientes() {
        /*
        // CLIENTE 1

        def javierHernandez = new RsPersona(
                email : "javierhernandez@gmail.com",
                apellidoPaterno: "HERNANDEZ",
                apellidoMaterno: "CHIHARITO",
                primerNombre: "JAVIER",
                segundoNombre: "JAVI",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('09/30/1974'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('LICENCIA_CONDUCIR'),
                numeroIdentificacionOficial : "JCHI727328328",
                rfc : "JCHI89778",
                curp : "JCHI76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteJavier
        clienteJavier = new RsCliente(
                persona: javierHernandez,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'JCHSDFYUYUI',
        ).save(failOnError: true)

        // CLIENTE 2

        def carlossalcido = new RsPersona(
                email : "carsalcido@gmail.com",
                apellidoPaterno: "SALCIDO",
                apellidoMaterno: "CAMPOS",
                primerNombre: "CARLOS",
                segundoNombre: "CHARLY",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('09/30/1970'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('LICENCIA_CONDUCIR'),
                numeroIdentificacionOficial : "CSAL727328328",
                rfc : "CSAL89778",
                curp : "CSAL76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('QUINDER'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteCarlos = new RsCliente(persona: carlossalcido,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'CSALSDFYUYUI',
        ).save(failOnError: true)

        // CLIENTE 3

        def franciscorodriguez = new RsPersona(
                email : "franciscorodriguez@gmail.com",
                apellidoPaterno: "RODRIGUEZ",
                apellidoMaterno: "MAZA",
                primerNombre: "FRANCISCO",
                segundoNombre: "EL MAZA",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('09/30/1980'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('LICENCIA_CONDUCIR'),
                numeroIdentificacionOficial : "MAZA727328328",
                rfc : "MAZA89778",
                curp : "MAZA76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PRIMARIA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteFrancisco = new RsCliente(persona: franciscorodriguez,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'MAZASDFYUYUI',
        ).save(failOnError: true)
        
        // CLIENTE 4

        def guillermoochoa = new RsPersona(
                email : "guillermoochoa@gmail.com",
                apellidoPaterno: "PACOMEMO",
                apellidoMaterno: "OCHOA",
                primerNombre: "FRANCISCO",
                segundoNombre: "PACO MEMO",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('09/30/1985'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "PACO727328328",
                rfc : "PACO89778",
                curp : "PACO76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('SECUNDARIA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteGuillermo = new RsCliente(persona: guillermoochoa,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'PACOSDFYUYUI',
        ).save(failOnError: true)



        // CLIENTE 5

        def jesuscorona = new RsPersona(
                email : "jesuscorona@gmail.com",
                apellidoPaterno: "CHUY",
                apellidoMaterno: "CORONA",
                primerNombre: "JESUS",
                segundoNombre: "CHUY CORONA",
                sexo: "MASCULINO",
                estadoCivil : "SOLTERO",
                fechaNacimiento : new Date('09/20/1973'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "CHUY727328328",
                rfc : "CHUY89778",
                curp : "CHUY76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LIC'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteJesus = new RsCliente(persona: jesuscorona,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'CHUYSDFYUYUI',
        ).save(failOnError: true)


        // CLIENTE 6

        def gerardotorrado = new RsPersona(
                email : "gerardotorrado@gmail.com",
                apellidoPaterno: "TORRADO",
                apellidoMaterno: "CASTRO",
                primerNombre: "GERARDO",
                segundoNombre: "GERA",
                sexo: "MASCULINO",
                estadoCivil : "SOLTERO",
                fechaNacimiento : new Date('09/20/1967'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "TORR727328328",
                rfc : "TORR89778",
                curp : "TORR76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LIC'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteGerardo = new RsCliente(persona: gerardotorrado,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'TORRSDFYUYUI',
        ).save(failOnError: true)



        // CLIENTE 7

        def oribeperalta = new RsPersona(
                email : "oribeperalta@gmail.com",
                apellidoPaterno: "PERALTA",
                apellidoMaterno: "CASTRO",
                primerNombre: "ORIBE",
                segundoNombre: "ORI",
                sexo: "MASCULINO",
                estadoCivil : "SOLTERO",
                fechaNacimiento : new Date('09/20/1967'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "ORIB727328328",
                rfc : "ORIB89778",
                curp : "ORIB76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LIC'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteOribe = new RsCliente(persona: oribeperalta,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'ORIBSDFYUYUI',
        ).save(failOnError: true)



        // CLIENTE 8

        def benjamingalindo = new RsPersona(
                email : "benjamingalindo@gmail.com",
                apellidoPaterno: "GALINDO",
                apellidoMaterno: "MENCHACA",
                primerNombre: "GALINDO",
                segundoNombre: "EL MAESTRO",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('09/13/1960'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "BENJ727328328",
                rfc : "BENJ89778",
                curp : "BENJ76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('MAESTRO'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteBenjamin = new RsCliente(persona: benjamingalindo,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'BENJSDFYUYUI',
        ).save(failOnError: true)



        // CLIENTE 9

        def alfredotena = new RsPersona(
                email : "alfredotena@gmail.com",
                apellidoPaterno: "TENA",
                apellidoMaterno: "GALINDO",
                primerNombre: "ALFREDO",
                segundoNombre: "AMERICA",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('09/13/1968'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "TENA727328328",
                rfc : "TENA89778",
                curp : "TENA76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('AMERICA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteAlfredo = new RsCliente(persona: alfredotena,
                dependencia: EntDependencia.findByClaveDependencia('IMSS'),
                numeroDeNomina: 'TENASDFYUYUI',
        ).save(failOnError: true)



        // CLIENTE 10

        def luisgarcia = new RsPersona(
                email : "luisgarcia@gmail.com",
                apellidoPaterno: "GARCIA",
                apellidoMaterno: "POSTIGO",
                primerNombre: "LUIS",
                segundoNombre: "DOCTOR",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('09/13/1971'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "LUIS727328328",
                rfc : "LUIS89778",
                curp : "LUIS76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LIC'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteLuis = new RsCliente(persona: luisgarcia,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'LUISSDFYUYUI',
        ).save(failOnError: true)
		*/




        // CLIENTE 11

        def joaquinlopez = new RsPersona(
                email : "joaquinlopez@gmail.com",
                apellidoPaterno: "LOPEZ",
                apellidoMaterno: "DORIGA",
                primerNombre: "JOAQUIN",
                segundoNombre: "TEACHER",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('09/13/1960'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "JOAQ727328328",
                rfc : "JOAQ89778",
                curp : "JOAQ76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('MAESTRO'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteJoaquin = new RsCliente(persona: joaquinlopez,
                dependencia: EntDependencia.findByClaveDependencia('IMSS'),
                numeroDeNomina: 'JOAQSDFYUYUI',
                apellidoPaterno: joaquinlopez.apellidoPaterno,
                primerNombre: joaquinlopez.primerNombre,
        ).save(failOnError: true)



        // CLIENTE 12

        def javieralatorre = new RsPersona(
                email : "javieralatorre@hotmail.com",
                apellidoPaterno: "ALA",
                apellidoMaterno: "TORRE",
                primerNombre: "JAVIER",
                segundoNombre: "HECHOS",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('09/23/1970'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "JAVI727328328",
                rfc : "JAVIQ89778",
                curp : "JAVIQ76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('INGENIERO'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteJaviera = new RsCliente(persona: javieralatorre,
                dependencia: EntDependencia.findByClaveDependencia('IMSS'),
                numeroDeNomina: 'JAVISDFYUYUI',
                apellidoPaterno: javieralatorre.apellidoPaterno,
                primerNombre: javieralatorre.primerNombre,
        ).save(failOnError: true)




        // CLIENTE 13

        def alejandrovillalvazo = new RsPersona(
                email : "alejandrovillalvazo@hotmail.com",
                apellidoPaterno: "VILLALVAZO",
                apellidoMaterno: "BUSTO",
                primerNombre: "ALEJANDRO",
                segundoNombre: "ALEX",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('09/23/1980'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "ALEX727328328",
                rfc : "ALEXQ89778",
                curp : "ALEXQ76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('INGENIERO'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteAlejandro = new RsCliente(persona: alejandrovillalvazo,
                dependencia: EntDependencia.findByClaveDependencia('IMSS'),
                numeroDeNomina: 'ALEXSDFYUYUI',
                apellidoPaterno: alejandrovillalvazo.apellidoPaterno,
                primerNombre: alejandrovillalvazo.primerNombre
        ).save(failOnError: true)



        // CLIENTE 14

        def adelamicha = new RsPersona(
                email : "adelamicha@hotmail.com",
                apellidoPaterno: "MICHA",
                apellidoMaterno: "BUSTO",
                primerNombre: "ADELA",
                segundoNombre: "ADELINA",
                sexo: "FEMENINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('09/23/1982'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "ADEL727328328",
                rfc : "ADELQ89778",
                curp : "ADELQ76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('ADMIN'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteAdela = new RsCliente(persona: adelamicha,
                dependencia: EntDependencia.findByClaveDependencia('IMSS'),
                numeroDeNomina: 'ADELSDFYUYUI',
                apellidoPaterno: adelamicha.apellidoPaterno,
                primerNombre: adelamicha.primerNombre
        ).save(failOnError: true)



        // CLIENTE 15

        def eduardosalazar = new RsPersona(
                email : "eduardosalazar@hotmail.com",
                apellidoPaterno: "SALAZAR",
                apellidoMaterno: "BUSTO",
                primerNombre: "EDUARDO",
                segundoNombre: "LALO",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('09/23/1982'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "ADEL727328328",
                rfc : "ADELQ89778",
                curp : "ADELQ76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('ADMIN'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteEduardo = new RsCliente(persona: eduardosalazar,
                dependencia: EntDependencia.findByClaveDependencia('IMSS'),
                numeroDeNomina: 'EDUSALBUS',
                apellidoPaterno: eduardosalazar.apellidoPaterno,
                primerNombre: eduardosalazar.primerNombre
        ).save(failOnError: true)



        // CLIENTE 16

        def carmenaristegui = new RsPersona(
                email : "carmenaristegui@gmail.com",
                apellidoPaterno: "ARISTEGUI",
                apellidoMaterno: "BUSTO",
                primerNombre: "CARMEN",
                segundoNombre: "CARMELA",
                sexo: "FEMENINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('03/27/1982'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "CARM727328328",
                rfc : "CARMQ89778",
                curp : "CARMQ76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADORA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteCarmen = new RsCliente(persona: carmenaristegui,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'CARMSDFYUYUI',
                apellidoPaterno: carmenaristegui.apellidoPaterno,
                primerNombre: carmenaristegui.primerNombre
        ).save(failOnError: true)



        // CLIENTE 17

        def araceliPaz = new RsPersona(
                email : "arelypaz@hotmail.com",
                apellidoPaterno: "PAZ",
                apellidoMaterno: "SALAS",
                primerNombre: "ARELY",
                segundoNombre: "ARACELI",
                sexo: "FEMENINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('09/20/1982'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "ARAP727328328",
                rfc : "ARAPQ89778",
                curp : "ARAPQ76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteAraceli = new RsCliente(persona: araceliPaz,
                dependencia: EntDependencia.findByClaveDependencia('IMSS'),
                numeroDeNomina: 'ARAPSDFYUYUI',
                apellidoPaterno: araceliPaz.apellidoPaterno,
                primerNombre: araceliPaz.primerNombre
        ).save(failOnError: true)




        // CLIENTE 18

        def lidyaCacho = new RsPersona(
                email : "lidya09@hotmail.com",
                apellidoPaterno: "CACHO",
                apellidoMaterno: "PASTRANA",
                primerNombre: "LIDYA",
                segundoNombre: "LIDYACA",
                sexo: "FEMENINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('06/20/1972'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "LIDY727328328",
                rfc : "LIDYQ89778",
                curp : "LIDYQ76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteLydiac = new RsCliente(persona: lidyaCacho,
                dependencia: EntDependencia.findByClaveDependencia('IMSS'),
                numeroDeNomina: 'LIDYSDFYUYUI',
                apellidoPaterno: lidyaCacho.apellidoPaterno,
                primerNombre: lidyaCacho.primerNombre
        ).save(failOnError: true)



        // CLIENTE 19

        def hanniaNovel = new RsPersona(
                email : "hannia@hotmail.com",
                apellidoPaterno: "NOVEL",
                apellidoMaterno: "PEREZ",
                primerNombre: "HANNIA",
                segundoNombre: "HANNIAARA",
                sexo: "FEMENINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('09/20/1972'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "HANN727328328",
                rfc : "HANNQ89778",
                curp : "HANNQ76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteHannia = new RsCliente(persona: hanniaNovel,
                dependencia: EntDependencia.findByClaveDependencia('IMSS'),
                numeroDeNomina: 'HANNSDFYUYUI',
                apellidoPaterno: hanniaNovel.apellidoPaterno,
                primerNombre: hanniaNovel.primerNombre
        ).save(failOnError: true)


        // CLIENTE 20

        def anamariaNovelli = new RsPersona(
                email : "anama13@gmail.com",
                apellidoPaterno: "NOVELLI",
                apellidoMaterno: "ESTRADA",
                primerNombre: "ANA",
                segundoNombre: "MARIA",
                sexo: "FEMENINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('03/27/1962'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "ANAM727328328",
                rfc : "ANAMQ89778",
                curp : "ANAMQ76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADORA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteAnam = new RsCliente(persona: anamariaNovelli,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'ANAMSDFYUYUI',
                apellidoPaterno: anamariaNovelli.apellidoPaterno,
                primerNombre: anamariaNovelli.primerNombre
        ).save(failOnError: true)



        // CLIENTE 21

        def anaGuevara = new RsPersona(
                email : "guevara@gmail.com",
                apellidoPaterno: "GUEVARA",
                apellidoMaterno: "BUSTOS",
                primerNombre: "ANA",
                segundoNombre: "GABRIELA",
                sexo: "FEMENINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('05/27/1982'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "ANAG727328328",
                rfc : "ANAGQ89778",
                curp : "ANAGQ76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADORA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteAnag = new RsCliente(persona: anaGuevara,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'ANAGSDFYUYUI',
                apellidoPaterno: anaGuevara.apellidoPaterno,
                primerNombre: anaGuevara.primerNombre
        ).save(failOnError: true)


        // CLIENTE 22

        def estebanArce = new RsPersona(
                email : "esteban03@gmail.com",
                apellidoPaterno: "ARCE",
                apellidoMaterno: "GORDILLO",
                primerNombre: "ESTEBAN",
                segundoNombre: "ESTEB",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('05/27/1962'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "ESTE727328328",
                rfc : "ESTEQ89778",
                curp : "ESTEQ76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteEsteb = new RsCliente(persona: estebanArce,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'ESTBSDFYUYUI',
                apellidoPaterno: estebanArce.apellidoPaterno,
                primerNombre: estebanArce.primerNombre
        ).save(failOnError: true)





        // CLIENTE 23

        def marianoOsorio = new RsPersona(
                email : "mariano09@gmail.com",
                apellidoPaterno: "OSORIO",
                apellidoMaterno: "ARCE",
                primerNombre: "MARIANO",
                segundoNombre: "MAR",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('05/27/1982'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "MARI727328328",
                rfc : "MARIQ89778",
                curp : "MARIQ76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteMariano = new RsCliente(persona: marianoOsorio,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'MARISDFYUYUI',
                apellidoPaterno: marianoOsorio.apellidoPaterno,
                primerNombre: marianoOsorio.primerNombre
        ).save(failOnError: true)




        // CLIENTE 24

        def jorgeZarza = new RsPersona(
                email : "zaeza09@gmail.com",
                apellidoPaterno: "zarza",
                apellidoMaterno: "cortes",
                primerNombre: "jorge",
                segundoNombre: "jor",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('05/17/1982'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "jorg727328328",
                rfc : "jorgQ89778",
                curp : "jorgQ76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteJorgez = new RsCliente(persona: jorgeZarza,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'jorgSDFYUYUI',
                apellidoPaterno: jorgeZarza.apellidoPaterno,
                primerNombre: jorgeZarza.primerNombre
        ).save(failOnError: true)



        // CLIENTE 25

        def eugenioderbez = new RsPersona(
                email : "eugenioder09@gmail.com",
                apellidoPaterno: "DERBEZ",
                apellidoMaterno: "ONTERO",
                primerNombre: "EUGENIO",
                segundoNombre: "EU",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('06/12/1978'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "EUGE7328328",
                rfc : "EUGE879778",
                curp : "EUGE6878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteEugenio = new RsCliente(persona:eugenioderbez,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'EUGENIOHUYTG',
                apellidoPaterno: eugenioderbez.apellidoPaterno,
                primerNombre: eugenioderbez.primerNombre
        ).save(failOnError: true)


        // CLIENTE 26

        def omarchaparro = new RsPersona(
                email : "omarchaparro@gmail.com",
                apellidoPaterno: "chaparro",
                apellidoMaterno: "guevara",
                primerNombre: "omar",
                segundoNombre: "ramo",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('08/25/1954'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "omarw27328328",
                rfc : "omarom89778",
                curp : "omarom6878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteOmar = new RsCliente(persona: omarchaparro,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'OMARDFYUYUI',
                apellidoPaterno: omarchaparro.apellidoPaterno,
                primerNombre: omarchaparro.primerNombre
        ).save(failOnError: true)



        // CLIENTE 27

        def javierlopez = new RsPersona(
                email : "javierlopez@gmail.com",
                apellidoPaterno: "LOPEZ",
                apellidoMaterno: "CHABELO",
                primerNombre: "JAVIER",
                segundoNombre: "CHABE",
                sexo: "MASCULINO",
                estadoCivil : "SOLTERO",
                fechaNacimiento : new Date('03/02/1956'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "CHABE57328328",
                rfc : "CHABE589778",
                curp : "CHABE5878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteJavierL = new RsCliente(persona: javierlopez,
                dependencia: EntDependencia.findByClaveDependencia('IMSS'),
                numeroDeNomina: 'CHABE5FYUYUI',
                apellidoPaterno: javierlopez.apellidoPaterno,
                primerNombre: javierlopez.primerNombre
        ).save(failOnError: true)


        // CLIENTE 28

        def adrianarodriguez = new RsPersona(
                email : "adrianarodrigu@gmail.com",
                apellidoPaterno: "RODRGUEZ",
                apellidoMaterno: "RODRGUEZ",
                primerNombre: "ADRIANA",
                segundoNombre: "ADI",
                sexo: "FEMENINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('06/01/1985'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "ADRIANA7328328",
                rfc : "ADRIANA79778",
                curp : "ADRIANA778968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteAdriana = new RsCliente(persona: adrianarodriguez,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'ADISDFYUYUI',
                apellidoPaterno: adrianarodriguez.apellidoPaterno,
                primerNombre: adrianarodriguez.primerNombre
        ).save(failOnError: true)


        // CLIENTE 29

        def carlosperez = new RsPersona(
                email : "carlosperez@gmail.com",
                apellidoPaterno: "PEREZ",
                apellidoMaterno: "RUIZ",
                primerNombre: "CARLOS",
                segundoNombre: "LEE",
                sexo: "MASCULINO",
                estadoCivil : "SOLTERO",
                fechaNacimiento : new Date('01/25/1987'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "LEE44466",
                rfc : "LEE44466",
                curp : "LEE44466",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteCarlosl = new RsCliente(persona: carlosperez,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'LEE41235',
                apellidoPaterno: carlosperez.apellidoPaterno,
                primerNombre: carlosperez.primerNombre
        ).save(failOnError: true)



        // CLIENTE 30

        def paulinarubio = new RsPersona(
                email : "paulinarubio@gmail.com",
                apellidoPaterno: "RUBIO",
                apellidoMaterno: "RUBIO",
                primerNombre: "PAULINA",
                segundoNombre: "PAU",
                sexo: "FEMENINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('02/30/1967'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "PAU321215",
                rfc : "PAU3212155TR",
                curp : "PAU321215UG",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clientePaulina = new RsCliente(persona: paulinarubio,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'PAU678990',
                apellidoPaterno: paulinarubio.apellidoPaterno,
                primerNombre: paulinarubio.primerNombre
        ).save(failOnError: true)



        // CLIENTE 31

        def romeosantos = new RsPersona(
                email : "romeosantos@gmail.com",
                apellidoPaterno: "SANTOS",
                apellidoMaterno: "JEREZ",
                primerNombre: "ROMEO",
                segundoNombre: "JUAN",
                sexo: "MASCULINO",
                estadoCivil : "SOLTERO",
                fechaNacimiento : new Date('12/05/1997'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "ROMEO328328",
                rfc : "ROMEO328328YT",
                curp : "ROMEO328328",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteRomeo = new RsCliente(persona: romeosantos,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'ROMEO342',
                apellidoPaterno: romeosantos.apellidoPaterno,
                primerNombre: romeosantos.primerNombre
        ).save(failOnError: true)



        // CLIENTE 32

        def henrisanchez = new RsPersona(
                email : "henrisanchez@gmail.com",
                apellidoPaterno: "SANCHEZ",
                apellidoMaterno: "ROJAS",
                primerNombre: "HENRI",
                segundoNombre: "HERNAN",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('02/28/1973'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "HENRI123457",
                rfc : "HENRI123457",
                curp : "HENRI123457",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteHenri = new RsCliente(persona: henrisanchez,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'HNERI462',
                apellidoPaterno: henrisanchez.apellidoPaterno,
                primerNombre: henrisanchez.primerNombre
        ).save(failOnError: true)



        // CLIENTE 33

        def luissolis = new RsPersona(
                email : "luissolis@gmail.com",
                apellidoPaterno: "SOLIS",
                apellidoMaterno: "SOLAR",
                primerNombre: "LUIS",
                segundoNombre: "RYAN",
                sexo: "MASCULINO",
                estadoCivil : "SOLTERO",
                fechaNacimiento : new Date('18/09/1956'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "RYAN4567828",
                rfc : "RYAN4567828",
                curp : "RYAN4567828",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteLuisR = new RsCliente(persona: luissolis,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'LUI456',
                apellidoPaterno: luissolis.apellidoPaterno,
                primerNombre: luissolis.primerNombre
        ).save(failOnError: true)



        // CLIENTE 34

        def elsaruiz = new RsPersona(
                email : "elsaruizs@gmail.com",
                apellidoPaterno: "RUIZ",
                apellidoMaterno: "RIOS",
                primerNombre: "ELSA",
                segundoNombre: "ISABEL",
                sexo: "FEMENINO",
                estadoCivil : "SOLTERO",
                fechaNacimiento : new Date('07/03/1943'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "ELSA789328",
                rfc : "ELSA789328",
                curp : "ELSA789328",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteElsa = new RsCliente(persona: elsaruiz,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'ELSA543',
                apellidoPaterno: elsaruiz.apellidoPaterno,
                primerNombre: elsaruiz.primerNombre
        ).save(failOnError: true)



        // CLIENTE 35

        def guillermoochoal = new RsPersona(
                email : "memo@gmail.com",
                apellidoPaterno: "OCHOA",
                apellidoMaterno: "SALAZ",
                primerNombre: "GUILLERMO",
                segundoNombre: "PACO",
                sexo: "MASCULINO",
                estadoCivil : "SOLTERO",
                fechaNacimiento : new Date('18/09/1986'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "PACO4567828",
                rfc : "PACO4567828",
                curp : "PACO4567828",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteGuillermoo = new RsCliente(persona: guillermoochoal,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'PACO456',
                apellidoPaterno: guillermoochoal.apellidoPaterno,
                primerNombre: guillermoochoal.primerNombre
        ).save(failOnError: true)


        // CLIENTE 36

        def rafaelMarquez = new RsPersona(
                email : "rmarquez@gmail.com",
                apellidoPaterno: "MARQUEZ",
                apellidoMaterno: "ROSAS",
                primerNombre: "RAFAEL",
                segundoNombre: "RAFA",
                sexo: "MASCULINO",
                estadoCivil : "SOLTERO",
                fechaNacimiento : new Date('07/03/1943'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "RAFA789328",
                rfc : "RAFA789328",
                curp : "RAFA789328",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteRafam = new RsCliente(persona: rafaelMarquez,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'RAFA543',
                apellidoPaterno: rafaelMarquez.apellidoPaterno,
                primerNombre: rafaelMarquez.primerNombre
        ).save(failOnError: true)



        // CLIENTE 37

        def gerardoTorrado = new RsPersona(
                email : "gerardo4@gmail.com",
                apellidoPaterno: "TORRADO",
                apellidoMaterno: "PASTRANA",
                primerNombre: "GERARDO",
                segundoNombre: "BORREGO",
                sexo: "MASCULINO",
                estadoCivil : "SOLTERO",
                fechaNacimiento : new Date('18/09/1976'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "GERA4567828",
                rfc : "GERA4567828",
                curp : "GERA4567828",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteGerardot = new RsCliente(persona: gerardoTorrado,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'GERA456',
                apellidoPaterno: gerardoTorrado.apellidoPaterno,
                primerNombre: gerardoTorrado.primerNombre
        ).save(failOnError: true)



        // CLIENTE 38

        def juanDiaz = new RsPersona(
                email : "juand@gmail.com",
                apellidoPaterno: "DIAZ",
                apellidoMaterno: "TORRES",
                primerNombre: "JUAN",
                segundoNombre: "JUANGA",
                sexo: "MASCULINO",
                estadoCivil : "SOLTERO",
                fechaNacimiento : new Date('18/09/1986'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "JUAN4567828",
                rfc : "JUAN4567828",
                curp : "JUAN4567828",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteJuang = new RsCliente(persona: juanDiaz,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'JUAN456',
                apellidoPaterno: juanDiaz.apellidoPaterno,
                primerNombre: juanDiaz.primerNombre
        ).save(failOnError: true)

        // CLIENTE 39

        def carlosPerez = new RsPersona(
                email : "carpe@gmail.com",
                apellidoPaterno: "PEREZ",
                apellidoMaterno: "ROSALES",
                primerNombre: "CARLOS",
                segundoNombre: "CARPE",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('06/12/1980'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "CARP7328328",
                rfc : "CARP879778",
                curp : "CARP6878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteCarlo = new RsCliente(persona:carlosPerez,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'CARPNIOHUYTG',
                apellidoPaterno: carlosPerez.apellidoPaterno,
                primerNombre: carlosPerez.primerNombre
        ).save(failOnError: true)


        // CLIENTE 40
      
        def cristianBenitez = new RsPersona(
                email : "cristianbenitez@gmail.com",
                apellidoPaterno: "BENITES",
                apellidoMaterno: "SALCEDO",
                primerNombre: "CRISTIAN",
                segundoNombre: "CHUCHO",
                sexo: "MASCULINO",
                estadoCivil : "SOLTERO",
                fechaNacimiento : new Date('10/09/1976'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
                numeroIdentificacionOficial : "CRIS4567828",
                rfc : "CRIS4567828",
                curp : "CRIS4567828",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteCristianb = new RsCliente(persona: cristianBenitez,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'CRIS456',
                apellidoPaterno: cristianBenitez.apellidoPaterno,
                primerNombre: cristianBenitez.primerNombre
        ).save(flush: true, failOnError: true)

        return true

    }

    boolean altaPrestamos () {

        /*
        Prestamo prestamoUno = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('JCHSDFYUYUI'),
                correoSolicitante:     "javierhernandez@gmail.com",
                folioSolicitud : 	   1,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   10000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('01/16/2013'),
        ).save()

        Prestamo prestamoDos = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('CSALSDFYUYUI'),
                correoSolicitante:     "carsalcido@gmail.com",
                folioSolicitud : 	   2,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   10000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('01/17/2013'),
        ).save(failOnError: true)

        Prestamo prestamoTres = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('MAZASDFYUYUI'),
                correoSolicitante:     "franciscorodriguez@gmail.com",
                folioSolicitud : 	   3,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   10000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('01/18/2013'),
        ).save(failOnError: true)
        

        Prestamo prestamoCuatro = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('PACOSDFYUYUI'),
                correoSolicitante:     "guillermoochoa@gmail.com",
                folioSolicitud : 	   4,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   10000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('01/19/2013'),
        ).save(failOnError: true)

        Prestamo prestamoCinco = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('CHUYSDFYUYUI'),
                correoSolicitante:     "jesuscorona@gmail.com",
                folioSolicitud : 	   5,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   10000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('01/20/2013'),
        ).save(failOnError: true)

        Prestamo prestamoSeis = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('TORRSDFYUYUI'),
                correoSolicitante:     "gerardotorrado@gmail.com",
                folioSolicitud : 	   6,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   10000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('01/21/2013'),
        ).save(failOnError: true)

        Prestamo prestamoSiete = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('ORIBSDFYUYUI'),
                correoSolicitante:     "oribeperalta@gmail.com",
                folioSolicitud : 	   7,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   10000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('01/22/2013'),
        ).save(failOnError: true)

        Prestamo prestamoOcho = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('BENJSDFYUYUI'),
                correoSolicitante:     "benjamingalindo@gmail.com",
                folioSolicitud : 	   8,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   10000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('01/23/2013'),
        ).save(failOnError: true)

        Prestamo prestamoNueve = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('TENASDFYUYUI'),
                correoSolicitante:     "alfredotena@gmail.com",
                folioSolicitud : 	   9,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   10000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('01/24/2013'),
        ).save(failOnError: true)

        Prestamo prestamoDiez = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('LUISSDFYUYUI'),
                correoSolicitante:     "luisgarcia@gmail.com",
                folioSolicitud : 	   10,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   10000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('01/25/2013'),
        ).save(failOnError: true)
	*/

        Prestamo prestamoOnce = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('JOAQSDFYUYUI'),
                correoSolicitante:     "joaquinlopez@gmail.com",
                folioSolicitud : 	   11,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   15000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('01/26/2013'),
        ).save(failOnError: true)

        Prestamo prestamoDoce = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('JAVISDFYUYUI'),
                correoSolicitante:     "javieralatorre@hotmail.com",
                folioSolicitud : 	   12,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   15000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('01/27/2013'),
        ).save(failOnError: true)

        Prestamo prestamoTrece = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('ALEXSDFYUYUI'),
                correoSolicitante:     "alejandrovillalvazo@hotmail.com",
                folioSolicitud : 	   13,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   15000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('01/28/2013'),
        ).save(failOnError: true)

        Prestamo prestamoCatorce = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('ADELSDFYUYUI'),
                correoSolicitante:     "adelamicha@hotmail.com",
                folioSolicitud : 	   14,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   15000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('01/29/2013'),
        ).save(failOnError: true)

        Prestamo prestamoQuince = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('EDUSALBUS'),
                correoSolicitante:     "eduardosalazar@hotmail.com",
                folioSolicitud : 	   15,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   15000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('01/30/2013'),
        ).save(failOnError: true)

        Prestamo prestamoDieciseis = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('CARMSDFYUYUI'),
                correoSolicitante:     "carmenaristegui@gmail.com",
                folioSolicitud : 	   16,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   15000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('01/31/2013'),
        ).save(failOnError: true)

        Prestamo prestamoDiecisiete = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('ARAPSDFYUYUI'),
                correoSolicitante:     "arelypaz@hotmail.com",
                folioSolicitud : 	   17,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   17000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/01/2013'),
        ).save(failOnError: true)

        Prestamo prestamoDieciocho = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('LIDYSDFYUYUI'),
                correoSolicitante:     "lidya09@hotmail.com",
                folioSolicitud : 	   18,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   17000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/02/2013'),
        ).save(failOnError: true)

        Prestamo prestamoDiecinueve = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('HANNSDFYUYUI'),
                correoSolicitante:     "hannia@hotmail.com",
                folioSolicitud : 	   19,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   17000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/03/2013'),
        ).save(failOnError: true)

        Prestamo prestamoVeinte = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('ANAMSDFYUYUI'),
                correoSolicitante:     "anama13@gmail.com",
                folioSolicitud : 	   20,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   17000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/04/2013'),
        ).save(failOnError: true)

        Prestamo prestamoVeintiUno = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('ANAGSDFYUYUI'),
                correoSolicitante:     "guevara@gmail.com",
                folioSolicitud : 	   21,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   17000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/05/2013'),
        ).save(failOnError: true)

        Prestamo prestamoVeintiDos = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('ESTBSDFYUYUI'),
                correoSolicitante:     "esteban03@gmail.com",
                folioSolicitud : 	   22,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   17000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/06/2013'),
        ).save(failOnError: true)

        Prestamo prestamoVeintiTres = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('MARISDFYUYUI'),
                correoSolicitante:     "mariano09@gmail.com",
                folioSolicitud : 	   23,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   14000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/07/2013'),
        ).save(failOnError: true)

        Prestamo prestamoVeintiCuatro = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('jorgSDFYUYUI'),
                correoSolicitante:     "zaeza09@gmail.com",
                folioSolicitud : 	   24,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   14000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/08/2013'),
        ).save(failOnError: true)

        Prestamo prestamoVeintiCinco = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('EUGENIOHUYTG'),
                correoSolicitante:     "eugenioder09@gmail.com",
                folioSolicitud : 	   25,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   14000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/09/2013'),
        ).save(failOnError: true)

        Prestamo prestamoVeintiSeis = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('OMARDFYUYUI'),
                correoSolicitante:     "omarchaparro@gmail.com",
                folioSolicitud : 	   26,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   14000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/10/2013'),
        ).save(failOnError: true)

        Prestamo prestamoVeintiSiete = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('CHABE5FYUYUI'),
                correoSolicitante:     "javierlopez@gmail.com",
                folioSolicitud : 	   27,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   14000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/11/2013'),
        ).save(failOnError: true)

        Prestamo prestamoVeintiOcho = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('ADISDFYUYUI'),
                correoSolicitante:     "adrianarodrigu@gmail.com",
                folioSolicitud : 	   28,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   14000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/12/2013'),
        ).save(failOnError: true)

        Prestamo prestamoVeintiNueve = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('LEE41235'),
                correoSolicitante:     "carlosperez@gmail.com",
                folioSolicitud : 	   29,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   14000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/13/2013'),
        ).save(failOnError: true)

        Prestamo prestamoTreinta = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('PAU678990'),
                correoSolicitante:     "paulinarubio@gmail.com",
                folioSolicitud : 	   30,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   14000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/14/2013'),
        ).save(failOnError: true)

        Prestamo prestamoTreintaYUno = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('ROMEO342'),
                correoSolicitante:     "romeosantos@gmail.com",
                folioSolicitud : 	   31,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   17000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/15/2013'),
        ).save(failOnError: true)

        Prestamo prestamoTreintaYDos = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('HNERI462'),
                correoSolicitante:     "henrisanchez@gmail.com",
                folioSolicitud : 	   32,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   17000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/16/2013'),
        ).save(failOnError: true)

        Prestamo prestamoTreintaYTres = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('LUI456'),
                correoSolicitante:     "luissolis@gmail.com",
                folioSolicitud : 	   33,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   14000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/17/2013'),
        ).save(failOnError: true)

        Prestamo prestamoTreintaYCuatro = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('ELSA543'),
                correoSolicitante:     "elsaruizs@gmail.com",
                folioSolicitud : 	   34,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   14000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/18/2013'),
        ).save(failOnError: true)

        Prestamo prestamoTreintaYCinco = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('PACO456'),
                correoSolicitante:     "memo@gmail.com",
                folioSolicitud : 	   35,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   14000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/19/2013'),
        ).save(failOnError: true)

        Prestamo prestamoTreintaYSeis = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('RAFA543'),
                correoSolicitante:     "rmarquez@gmail.com",
                folioSolicitud : 	   36,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   14000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/20/2013'),
        ).save(failOnError: true)

        Prestamo prestamoTreintaYSiete = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('GERA456'),
                correoSolicitante:     "gerardo4@gmail.com",
                folioSolicitud : 	   37,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   14000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/21/2013'),
        ).save(failOnError: true)

        Prestamo prestamoTreintaYOcho = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('JUAN456'),
                correoSolicitante:     "juand@gmail.com",
                folioSolicitud : 	   38,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   14000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/22/2013'),
        ).save(failOnError: true)

        Prestamo prestamoTreintaYNueve = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('CARPNIOHUYTG'),
                correoSolicitante:     "carpe@gmail.com",
                folioSolicitud : 	   39,
                dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
                promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   14000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/23/2013'),
        ).save(failOnError: true)

        Prestamo prestamoCuarenta = new Prestamo(
                cliente : 			   RsCliente.findByNumeroDeNomina('CRIS456'),
                correoSolicitante:     "cristianbenitez@gmail.com",
                folioSolicitud : 	   40,
                dependencia : 		   EntDependencia.findByClaveDependencia('CFE'),
                promocion: 			   ProPromocion.findByClavePromocion('OPORTUNIDAD'),
                sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
                fechaSolicitud:		   new Date('01/01/2013'),
                montoSolicitado: 	   14000,
                percepcionesMensuales: 20000,
                deduccionesMensuales:  25000,
                estatusSolicitud:      PrestamoEstatus.ACTIVO,
                formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos:   false,
                aprobado:              false,
                reenviarSolicitud:     false,
                incluirEnListasCobro:  true,
                fechaCobro:            new Date('02/24/2013'),
        ).save(flush: true, failOnError: true)

        return true

    }

    Boolean altaAccesorios () {

        ArrayList prestamosExistentes = Prestamo.findAll()
        prestamosExistentes.each() {

            new PrestamoAccesorio(
                    accesorio		:	SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('SEGUNICO')),
                    valor			:	200,
                    unidad			:	SimCatUnidad.findByClaveUnidad('PORCENTUAL'),
                    periodicidad	:	SimCatPeriodicidad.findByClavePeriodicidad('MES'),
                    prestamo		: 	Prestamo.findByFolioSolicitud(it.folioSolicitud)
            ).save(failOnError: true)
            new PrestamoAccesorio(
                    accesorio		:	SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('SEGUNICOA')),
                    valor			:	10,
                    unidad			:	SimCatUnidad.findByClaveUnidad('UNIDAD'),
                    periodicidad	:	SimCatPeriodicidad.findByClavePeriodicidad('SEMANA'),
                    prestamo		: 	Prestamo.findByFolioSolicitud(it.folioSolicitud)
            ).save(failOnError: true)
            new PrestamoAccesorio(
                    accesorio		:	SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('SEGUNICOB')),
                    valor			:	15,
                    unidad			:	SimCatUnidad.findByClaveUnidad('ALMILLAR'),
                    periodicidad	:	SimCatPeriodicidad.findByClavePeriodicidad('CATORCENA'),
                    prestamo		: 	Prestamo.findByFolioSolicitud(it.folioSolicitud)
            ).save(failOnError: true)
            new PrestamoAccesorio(
                    accesorio		:	SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('SEGUNICOC')),
                    valor			:   20,
                    unidad			:	SimCatUnidad.findByClaveUnidad('PORCENTUAL'),
                    periodicidad	:	SimCatPeriodicidad.findByClavePeriodicidad('QUINCENA'),
                    prestamo		: 	Prestamo.findByFolioSolicitud(it.folioSolicitud)
            ).save(failOnError: true)

            log.info("Los accesorios del prestamo ${it.folioSolicitud} se dieron de alta correctamente.")

        }

        return true
    }
}
