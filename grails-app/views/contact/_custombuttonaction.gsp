<jqgrid:grid
      id="contactCustomActions"
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

      <jqgrid:filterToolbar id="contactCustomActions" searchOnEnter="false" />
      <jqgrid:navigation id="contactCustomActions" />
      <jqgrid:resize id="contactCustomActions" resizeOffset="-2" />
      <jqgrid:refreshButton id="contactCustomActions" title="Reload Contact List"/>
      <jqgrid:searchButton id="contactCustomActions" messageId="message" title="Toggle Search Bar"/>
      <jqgrid:deleteButton id="contactCustomActions" messageId="message" function="customAction" />
      <jqgrid:editButton id="contactCustomActions" messageId="message" function="customAction"/>
      <jqgrid:addButton id="contactCustomActions" messageId="message" function="customAction"/>
</jqgrid:grid>