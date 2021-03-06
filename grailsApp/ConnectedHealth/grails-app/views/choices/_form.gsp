<g:if test="${choice != null}">
    <% FORM_URL = "/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${question.id}/choices/${choice.id}" %>
    <% FORM_METHOD = "PUT" %>
</g:if>
<g:else>
    <% FORM_URL = "/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${question.id}/choices/create" %>
    <% FORM_METHOD = "POST" %>
</g:else>

<g:form url="${FORM_URL}" method="${FORM_METHOD}">
    <fieldset class="form">
        <div class="fieldcontain  required">
            <label for="content">
                Content
                <span class="required-indicator">*</span>
            </label>
            <g:if test="${choice != null}">
                <g:textArea name="content" id="content" rows="5" cols="40" value="${choice.content}"/>
            </g:if>
            <g:else>
                <g:textArea name="content" id="content" rows="5" cols="40"/>
            </g:else>

            <g:hiddenField name="questionnaireID" value="${questionnaire.id}"/>
            <g:hiddenField name="questionID" value="${question.id}"/>
        </div>
    </fieldset>
    <br/>
    <g:if test="${choice != null}">
        <input type="submit" name="update" class="save btn btn-primary bootButtons" value="Update" id="update">
    </g:if>
    <g:else>
        <input type="submit" name="create" class="save btn btn-primary bootButtons" value="Create" id="create">
    </g:else>
</g:form>