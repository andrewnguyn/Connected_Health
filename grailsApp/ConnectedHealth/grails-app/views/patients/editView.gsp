
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Edit Profile</title>
</head>

<body>
<div class="patientSection">

<div id="create-medicalNote" class="content scaffold-create" role="main">
    <div class="nav" role="navigation">
        <fieldset class="buttons buttonsRed">
            <a href="/ConnectedHealth/patients/${patient.id}/show" class="list">Back to patient profile</a>
        </fieldset>
    </div>

    <h1>Edit Profile</h1>

    <g:form url="/ConnectedHealth/patients/${patient.id}" method="PUT">

        <fieldset class="form">
            <div class="fieldcontain  required">



                <label for="contactEmail">
                    Contact Email
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="contactEmail" value="${patient.contactEmail}" id="contactEmail" size="25"/>

                <br/>
                <br/>

                <label for="firstName">
                    First Name
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="firstName" value="${patient.firstName}" id="firstName" size="10"/>

                <br/>
                <br/>

                <label for="lastName">
                    Last Name
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="lastName" value="${patient.lastName}" id="lastName" size="10"/>

                <br/>
                <br/>

                <label for="homeAddress">
                    Home Address
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="homeAddress" value="${patient.homeAddress}" id="homeAddress" size="50"/>

                <br/>
                <br/>

                <label for="phone">
                    Phone
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="phone" value="${patient.phone}" id="phone" rows="5"  size="10"/>


            </div>
        </fieldset>
        <br/>

        <input type="submit" name="update" class="save btn btn-danger bootButtons" value="Update" id="update">
    </g:form>
</div>
    </div>
</body>
</html>