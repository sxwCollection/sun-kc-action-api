<#import "template.ftl" as layout>
<@layout.registrationLayout displayMessage=!messagesPerField.existsError('echo'); section>
    <#if section = "header">
        ${msg("rbFormTitle")}
    <#elseif section = "form">
			<h2>${msg("helloText",(username!''))}</h2>
			<p>${msg("rbText")}</p>
			<form id="kc-ring-bell-form" class="${properties.kcFormClass!}" action="${url.loginAction}" method="post">
				<div class="${properties.kcFormGroupClass!}">
					<div class="${properties.kcLabelWrapperClass!}">
						<label for="echo"class="${properties.kcLabelClass!}">${msg("rbLabel")}</label>
					</div>
					<div class="${properties.kcInputWrapperClass!}">
						<input type="tel" id="echo" name="echo" class="${properties.kcInputClass!}"
									 value="${echo}" required aria-invalid="<#if messagesPerField.existsError('echo')>true</#if>"/>
              <#if messagesPerField.existsError('echo')>
								<span id="input-error-mobile-number" class="${properties.kcInputErrorMessageClass!}" aria-live="polite">
										${kcSanitize(messagesPerField.get('echo'))?no_esc}
								</span>
              </#if>
					</div>
				</div>
				<div class="${properties.kcFormGroupClass!}">
					<div id="kc-form-buttons" class="${properties.kcFormButtonsClass!}">
						<input class="${properties.kcButtonClass!} ${properties.kcButtonPrimaryClass!} ${properties.kcButtonBlockClass!} ${properties.kcButtonLargeClass!}" type="submit" value="${msg("doSubmit")}"/>
					</div>
				</div>
			</form>
    </#if>
</@layout.registrationLayout>
