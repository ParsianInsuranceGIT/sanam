@echo off
echo WSGEN is running
F:
cd \
cd F:\sanamCH\out\artifacts\Copy_of_sanam_war_exploded\WEB-INF\classes
"C:\Program Files\Java\jdk1.7.0_71\bin\wsgen.exe" -verbose -keep -cp . com.bitarts.parsian.service.implementation.BitartsWebService

pause
