<jqgrid:grid
    id="multiselectContact"
    url="'${createLink(action: 'listJSON')}'"
    editurl="'${createLink(action: 'editJSON')}'"
    colNames="'First Name', 'Last Name', 'E-mail', 'Phone', 'id'"
    colModel="{name:'firstName', editable: true},
              {name:'lastName', editable: true},
              {name:'email', editable: true},
              {name:'phone', editable: true},
              {name:'id', hidden: true}"
    sortname="'lastName'"
    caption="'Multiselect'"
    height="300"
    autowidth="true"
    scrollOffset="0"
    viewrecords="true"
    showPager="true"
    datatype="'json'"
    multiselect="true">
    <jqgrid:filterToolbar id="multiselectContact" searchOnEnter="false" />
    <jqgrid:navigation id="multiselectContact" add="true" edit="true" del="true" search="true" refresh="true" />
    <jqgrid:customButton id="multiselectContact" icon="ui-icon-check" position="last" function="showSelectedRows" />
    <jqgrid:resize id="multiselectContact" resizeOffset="-2" />
</jqgrid:grid>
