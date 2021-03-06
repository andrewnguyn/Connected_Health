<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Edit choice</title>
</head>

<body>
<div class="questionnaireSection">
    <div class="nav" role="navigation">
        <fieldset class="buttons buttonsBlue">
            <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/show" class="list">Back to Questionnaire</a>
            <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${question.id}/show"
               class="list">View question</a>
        </fieldset>
    </div>

    <div id="create-question" class="content scaffold-create" role="main">
        <h1>Edit choice</h1>

        <g:render template="/choices/form"
                  locals="${[questionnaire: questionnaire, question: question, choice: choice]}"/>
    </div>
</div>
</body>
</html>