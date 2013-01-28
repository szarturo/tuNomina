<jqgrid:grid
  id="contactSubgrid"
  url="'${createLink(action: 'listJSON')}'"
  colNames="'Primer Nombre','Segundo Nombre', 'Apellido Paterno', 'Apellido Materno','RFC', 'id'"
  colModel="{name:'primerNombre', editable: true},
            {name:'segundoNombre', editable: true},
            {name:'apellidoPaterno', editable: true},
            {name:'apellidoMaterno', editable: true},
            {name:'rfc', editable: true},
            {name:'id', hidden: true}"
  sortname="'apellidoPaterno'"
  caption="'Clientes'"
  height="300"
  viewrecords="true"
  autowidth="true"
  scrollOffset="0"
  datatype="'json'"
  showPager="true"
  subGrid="true"
  subGridUrl="'${createLink(action: 'subgridJSON')}'"
  subGridModel="name: {'Folio', 'Promoción', 'Estatus'},
                width: {10, 90, 50}">

  <jqgrid:filterToolbar id="contactSubgrid" searchOnEnter="false" />
  <jqgrid:navigation id="contactSubgrid" refresh="true" />
  <jqgrid:searchButton id="contactSubgrid" />
  <jqgrid:resize id="contactSubgrid" resizeOffset="-2" />
  
</jqgrid:grid>