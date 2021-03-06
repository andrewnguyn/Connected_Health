<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Add question</title>
</head>

<body>
<div class="questionnaireSection">
    <div class="nav" role="navigation">
        <fieldset class="buttons buttonsBlue">
            <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/show" class="list">Back to Questionnaire</a>
        </fieldset>
    </div>

    <div id="create-question" class="content scaffold-create" role="main">
        <h1>Create question</h1>

        <g:render template="/questions/form" locals="${[questionnaire: questionnaire]}"/>
    </div>
</div>
</body>
</html>