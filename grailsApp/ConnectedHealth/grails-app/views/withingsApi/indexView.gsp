<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Measurement Types</title>
</head>

<body>
<div class="nav" role="navigation">
    <fieldset class="buttons buttonsGreen">
        <a href="/ConnectedHealth/patients/${patient.id}/show" class="list">Back to patient profile</a>
    </fieldset>
</div>
<div class="content scaffold-list measurementSection" role="main">
    <h1>${patient.firstName + " " + patient.lastName}'s measurements</h1>
    <table class="table table-bordered tablePaddings" style="width: 90%">
        <tr>
            <th style="width: 25%">
                Type
            </th>
            <th>
                Description
            </th style="width: 75%">
        </tr>
        <tbody>
            <g:each in="${measurementTypes}">
                <tr>
                    <td>
                        <span class="property-value"><g:link controller="${"WithingsApi"}" action="stepsView" params="[patientID: patient.id]">${it.name}</g:link></span>
                    </td>
                    <td>
                        ${it.description}
                    </td>
                </tr>
            </g:each>
        </tbody>
    </table>


</div>
</body>
</html>