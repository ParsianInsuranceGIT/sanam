--exec generate_ghest_number('a');
create or replace
PROCEDURE generate_ghest_number(a in varchar) is

  i number;    
  count_ghests number;            
  
begin         
    i:=0;    
    --Iterate ghest bandi   
    for gb in (select gb1.* from tbl_ghestbandi gb1 where gb1.type='G_BIMENAME')  loop          
      
      --get count other ghests
      select count(g2.id) into count_ghests
        from tbl_ghest g2 
        join tbl_ghestbandi gb2 on gb2.id=g2.ghestbandi_id    
        where gb2.bimename_id=gb.bimename_id and gb2.type='G_BIMENAME' and gb2.id!=gb.id and gb2.salebimei<gb.salebimei;
      i:=0;
      i:=count_ghests  ;
      for g in (select g1.* from tbl_ghest g1 where g1.ghestbandi_id=gb.id order by g1.sarresid_date Asc) loop
        i:=i+1;
        update tbl_ghest g3 set g3.ghest_number=i where g3.id=g.id;      
       
      end loop;
      
    end loop;              
  
  commit;   
end ;