<jqgrid:grid
  id="contactSubgrid"
  url="'${createLink(action: 'listJSON')}'"
  editurl="'${createLink(action: 'editJSON')}'"
  colNames="'First Name', 'Last Name', 'E-mail', 'Phone', 'id'"
  colModel="{name:'firstName', editable: true},
            {name:'lastName', editable: true},
            {name:'email', editable: true},
            {name:'phone', editable: true},
            {name:'id', hidden: true}"
  sortname="'lastName'"
  caption="'Subgrid'"
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