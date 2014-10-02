<#include "pedidos-heading-macro.ftl">
<#include "pedidos-body-macro.ftl">
<#macro pedidos order>
	<div class="panel panel-default order-${order.statusType}">
		<@pedidosheading order/>
	  	<div class="panel-body">
			<@pedidosbody order/>
	  	</div>								  	
	</div>
</#macro>	