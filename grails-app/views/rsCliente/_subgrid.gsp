<jqgrid:grid
  id="contactSubgrid"
  url="'${createLink(action: 'listJSON')}'"
  editurl="'${createLink(action: 'editJSON')}'"
  colNames="'Primer Nombre', 'Apellido Paterno', 'Apellido Materno','RFC', 'id'"
  colModel="{name:'persona.primerNombre', editable: true},
            {name:'persona.apellidoPaterno', editable: true},
            {name:'persona.apellidoMaterno', editable: true},
            {name:'numeroDeNomina', editable: true},
            {name:'id', hidden: true}"
  sortname="'numeroDeNomina'"
  caption="'Clientes'"
  height="300"
  viewrecords="true"
  autowidth="true"
  scrollOffset="0"
  datatype="'json'"
  showPager="true"
  subGrid="true"
  subGridUrl="'${createLink(action: 'subgridJSON')}'"
  subGridModel="name: {'Line 1', 'City', 'State', 'Zip'},
                width: {200, 80, 80, 40}">

  <jqgrid:filterToolbar id="contactSubgrid" searchOnEnter="false" />
  <jqgrid:navigation id="contactSubgrid" refresh="true" />
  <jqgrid:searchButton id="contactSubgrid" />
  <jqgrid:resize id="contactSubgrid" resizeOffset="-2" />
  
</jqgrid:grid>