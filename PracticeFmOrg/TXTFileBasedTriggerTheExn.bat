@echo off
if exist "C:\DevOPS_Automation\Testing_Automation\pjtname\clientname\Email\Deployment Success - testman.txt" ( 
If Not Exist "C:\DevOPS_Automation\Testing_Automation\pjtname\clientname\Email\ExecutionInprogress.txt" (
C:\Users\username\eclipse-workspace\pjtfoldername\main.bat 
) else (echo Execution In Progress hence cannot trigger execution
)
) else (echo eml file not found which is needed to trigger exectuion
exit
)
exit

