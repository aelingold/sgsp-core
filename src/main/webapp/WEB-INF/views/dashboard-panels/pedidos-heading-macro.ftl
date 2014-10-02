<#macro pedidosheading order>
	<div class="panel-heading">
    	<h3 class="panel-title">
    		Necesito un ${order.workAreaDescription}
    		<#if order.workDateType='URGENT'>
    			<span class="pull-right urgente">Es urgente</span>
    		</#if>
    	</h3>
  	</div>
</#macro>	