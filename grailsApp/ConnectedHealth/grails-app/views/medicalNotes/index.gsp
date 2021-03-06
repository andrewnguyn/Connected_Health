<%@ page import="views.ViewHelpers" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Medical notes for ${patient.firstName + " " + patient.lastName}</title>
</head>

<body>
<div class="patientSection">
<div class="nav" role="navigation">
    <fieldset class="buttons buttonsRed">
        <a href="/ConnectedHealth/patients/${patient.id}/medicalnotes/create" class="create">Add medical note</a>
        <a href="/ConnectedHealth/patients/${patient.id}/show" class="list">Back to patient profile</a>
    </fieldset>
</div>
<div id="list-medicalNotes" class="content scaffold-list" role="main">
    <h1>Medical notes for ${patient.firstName + " " + patient.lastName}</h1>

    <table  class="table table-bordered tablePaddings" style="width: 90%">
        <thead>
        <tr>
            <th style="width: 75%">Note</th>
            <th>Date created</th>
        </tr>
        </thead>
        <tbody>

        <g:each in="${medicalNotes}">
            <tr>
                <td>
                    <span class="property-value"><a href="/ConnectedHealth/patients/${patient.id}/medicalnotes/${it.id}/show">${it.content}</a></span>
                </td>
                <td>
                    ${ViewHelpers.formatDate(it.created)}
                </td>
            </tr>
        </g:each>

        </tbody>
    </table>


</div>
    </div>
</body>
</html>