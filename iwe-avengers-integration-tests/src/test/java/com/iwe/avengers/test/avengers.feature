Feature: Perform integrated tests on the Avengers registration API

Background:
* url 'https://vwk623hry9.execute-api.us-east-1.amazonaws.com/dev'

Scenario: Get Avenger by Id

Given path 'avengers', 'aaa-bbb-ddd'
When method get
Then status 200

Scenario: Creates a new Avenger

Given path 'avengers'
And request {name: 'Captain America', secretIdentity: 'Steve Rogers'}
When method post
Then status 201

