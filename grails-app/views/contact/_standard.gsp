<jqgrid:grid
    id="contactStandard"
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
    autowidth="true"
    scrollOffset="0"
    viewrecords="true"
    showPager="true"
    datatype="'json'">
    <jqgrid:filterToolbar id="contactStandard" searchOnEnter="false" />
    <jqgrid:navigation id="contactStandard" add="true" edit="true" del="true" search="true" refresh="true" />
    <jqgrid:resize id="contactStandard" resizeOffset="-2" />
</jqgrid:grid>
