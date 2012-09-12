<jqgrid:grid
  id="contactCustomDefaultAction"
  url="'${createLink(action: 'listJSON')}'"
  editurl="'${createLink(action: 'editJSON')}'"
  colNames="'First Name', 'Last Name', 'E-mail', 'Phone', 'id'"
  colModel="{name:'firstName', editable: true},
            {name:'lastName', editable: true},
            {name:'email', editable: true},
            {name:'phone', editable: true},
            {name:'id', hidden: true}"
  sortname="'lastName'"
  caption="'Contact List'"
  height="300"
  viewrecords="true"
  autowidth="true"
  scrollOffset="0"
  datatype="'json'"
  showPager="true">
  <jqgrid:filterToolbar id="contactCustomDefaultAction" searchOnEnter="false" />
  <jqgrid:navigation id="contactCustomDefaultAction" refresh="true" />
  <jqgrid:deleteButton id="contactCustomDefaultAction" icon="ui-icon-scissors" messageId="message" url="${createLink(action: 'editJSON')}" />
  <jqgrid:editButton id="contactCustomDefaultAction" messageId="message" url="${createLink(action: 'edit')}" />
  <jqgrid:addButton id="contactCustomDefaultAction" messageId="message" url="${createLink(action: 'create')}" />
  <jqgrid:searchButton id="contactCustomDefaultAction" />
  <jqgrid:resize id="contactCustomDefaultAction" resizeOffset="-2" />
</jqgrid:grid>