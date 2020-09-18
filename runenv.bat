@ECHO OFF

FOR /F "tokens=1* delims==" %%A IN (.env) DO (
set %%A=%%B
)
echo Environment has been set
