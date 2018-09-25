Feature: Perform integrated tests on the Avengers registration API

Background:
* url 'https://vwk623hry9.execute-api.us-east-1.amazonaws.com/dev'

Scenario: Get Avenger by Id

Given path 'avengers', 'aaaa-sdsad-sdsa-asds'
When method get
Then status 200
And match response ==  {id: '#string', name: "Iron Man", secretIdentity: 'Tony Stark'}


Scenario: Avanger not found

Given path 'avengers', 'avanger-not-found'
When method get
Then status 404
 

Scenario: Creates a new Avenger and search by Id

Given path 'avengers'
And request {name: 'Iron Man', secretIdentity: 'Tony Stark'}
When method post
Then status 201
And match response ==  {id: '#string', name: "Captain America", secretIdentity: 'Steve Rogers'}


Scenario: Delete Avenger
 
Given path 'avengers', 'aaaa-sdsad-sdsa-asds'
When method delete
Then status 204

Scenario: Avanger not found for delete
 
Given path 'avengers', 'aaaa'
When method delete
Then status 404









Scenario: Update Avenger

Given path 'avengers', 'aaaa-sdsad-sdsa-asds'
And request {name: 'Captain America', secretIdentity: 'Steve Rogers'}
When method put
Then status 200
And match response ==  {id: '#string', name: '#string', secretIdentity: '#string'}'


Scenario: Avanger not found for update

Given path 'avengers', 'aaaaaaa'
And request {name: 'Captain America', secretIdentity: 'Steve Rogers'}
When method put
Then status 404


Scenario: Must return 400 for invalid creation payload

Given path 'avengers'
And request {name: 'Iron Man', identity: 'Tony Stark'}
When method post
Then status 400


Scenario: Must return 400 for invalid update payload

Given path 'avengers', 1
And request {name: 'Iron Man', identity: 'Tony Stark'}
When method put
Then status 400
