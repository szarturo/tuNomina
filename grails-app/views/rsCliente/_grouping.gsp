<jqgrid:grid
    id="groupingContact"
    url="'${createLink(action: 'listJSON')}'"
    editurl="'${createLink(action: 'editJSON')}'"
    colNames="'First Name', 'Last Name', 'E-mail', 'Phone', 'id'"
    colModel="{name:'firstName', editable: true},
               {name:'lastName', editable: true},
               {name:'email', editable: true},
               {name:'phone', editable: true},
               {name:'id', hidden: true}"
    datatype="'json'"
    autowidth="true"
    sortname="'lastName'"
    sortorder="'asc'"
    scrollOffset="0"
    height="300"
    rowNum="25"
    rowList="25, 50, 75, 100"
    viewrecords="true"
    gridview="false"
    caption="'Grouping'"
    hidegrid="true"
    grouping="true"
    groupingView="groupField: {'lastName'},
                  groupColumnShow : {true},
                  groupCollapse: false,
                  groupSummary: {false}"
    groupText="&lt;b&gt;{0} - {1} Item(s)&lt;/b&gt;"
    onSelectRow="eventHandler"
    showPager="true">
  <jqgrid:filterToolbar id="groupingContact" searchOnEnter="false" />
  <jqgrid:navigation id="groupingContact" refresh="true" />
  <jqgrid:resize id="groupingContact" resizeOffset="-2" />
  <jqgrid:searchButton id="groupingContact" messageId="message" />
  <jqgrid:deleteButton id="groupingContact" messageId="message" function="customAction" />
  <jqgrid:editButton id="groupingContact" messageId="message" function="customAction"/>
  <jqgrid:addButton id="groupingContact" messageId="message" function="customAction"/>
</jqgrid:grid>
