Feature: Customer API
	IN ORDER TO exchange customer data with a PIM system
	AS AN integrator
	I WANT TO have have a REST API for tranfering customer data

Scenario Outline: POST /services/private/{store}/customer
	When I make a POST request on /services/private/"<store>"/customer with '<json>'
	Then I should receive response "<code>"
	
Examples:
	| store	| json | code |
	| DEFAULT	| {"id" : 0,"emailAddress" : "carl@csticonsulting.com","billing" : {"firstName" : "Johny","lastName" : "BGood","bilstateOther" : null,"company" : null,"phone" : null,"address" : "123 my street","city" : "Boucherville","postalCode" : "H2H 2H2","stateProvince" : null,"billingAddress" : false,"latitude" : null,"longitude" : null,"zone" : "QC","country" : "CA"},"delivery" : null,"gender" : "M","language" : "en","firstName" : null,"lastName" : null,"encodedPassword" : "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8","clearPassword" : null,"storeCode" : null,"userName" : "testuser2","attributes" : null} | 201 | 
	
