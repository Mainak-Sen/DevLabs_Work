Feature: Application Login

Background:
Given browser is selected 
When Corresponding driver initialised and timeout is set 
Then Browser is invoked  and it navigates to the given URL

Scenario Outline: Mercury Tours Application login
Given User is on login page 
When User logs in into application with "<username>" and "<password>" 
Then It logs in into application and page title is changed  

Examples:
|username    |password|
|ortonikc    |Pass123$|
|Mainak      |MaaMercury@1|