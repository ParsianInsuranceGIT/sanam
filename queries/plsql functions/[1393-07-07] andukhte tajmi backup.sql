create or replace
function UPDATE_ANDUKHTE_TAJMI(date_input         String,
                                                         current_date_input         String,
                                                         soud_input        number,
                                                         bimename_ids_input num_array)
  return varchar2 is
  --PRAGMA AUTONOMOUS_TRANSACTION;
  Result                 varchar2(255);
  Resulttemp                 varchar2(255);

begin
  Result := 'success';
  FOR i IN (select * from table(bimename_ids_input)) LOOP

    Resulttemp := UPDATE_ANDUKHTE(date_input,current_date_input,soud_input,i.column_value);
    
    insert into log_gozaresh l(l.BIMENAME_ID,l.description)
    values (i.column_value,to_char(systimestamp,'dd-mm-yyyy hh24:mi:ss.FF'));

  END LOOP;
  return(Result);
EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
   Result := 'failed';
    return(Result);
end UPDATE_ANDUKHTE_TAJMI;