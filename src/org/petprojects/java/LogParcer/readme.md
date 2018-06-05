## Log Parser
Application is used to parse logs 

## Description of desired parser
Format of log parser:
ip username date event 
- ip : ip address from which user performed action
- username : user name (one or several words separated by space)
- date : date of event in format of "day.month.year hour:minute:second"
- event : one of following events
    LOGIN - user login.
    DOWNLOAD_PLUGIN - user downloads plugin.
    WRITE_MESSAGE - user sent message.
    SOLVE_TASK - user tries to solve task.
    DONE_TASK - user solved task.
For SOLVE_TASK and DONE_TASK additional parameter is present
which is indicated by a space and means id of task
- status : status where
    OK - successful event.
    FAILED - unsuccessful event.
    ERROR - an error has occurred during event.


