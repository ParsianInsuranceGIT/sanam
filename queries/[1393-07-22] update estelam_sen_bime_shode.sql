select pold.bimename_bimename_id from  tbl_darkhast_taghir dt 

join tbl_pishnehad pOld   on pOld.id=dt.oldpish_id
join tbl_estelam eOld     on eOld.estelam_id=pOld.estelam_id
join tbl_bimeshode bsOld  on bsOld.id=pOld.bimeshode_id
join tbl_shakhs sOld      on sOld.shakhs_id=bsOld.shakhs_id

join tbl_pishnehad pNew   on pNew.id=dt.newpish_id
join tbl_estelam eNew     on eNew.estelam_id=pNew.estelam_id
join tbl_bimeshode bsNew  on bsNew.id=pNew.bimeshode_id
join tbl_shakhs sNew      on sNew.shakhs_id=bsNew.shakhs_id

where
eold.sen_bime_shode!=enew.sen_bime_shode and sold.tarikhe_tavallod=snew.tarikhe_tavallod;

--------------------------------------------------------------------------------
update tbl_estelam e1 
set e1.sen_bime_shode=  (
                          select eold.sen_bime_shode from  tbl_darkhast_taghir dt 
                          
                          join tbl_pishnehad pOld   on pOld.id=dt.oldpish_id
                          join tbl_estelam eOld     on eOld.estelam_id=pOld.estelam_id  
                          
                          join tbl_pishnehad pNew   on pNew.id=dt.newpish_id
                          join tbl_estelam eNew     on eNew.estelam_id=pNew.estelam_id    
                          
                          where
                          enew.estelam_id=e1.estelam_id
                        )
where 
  exists 
  (
    select pold1.bimename_bimename_id from  tbl_darkhast_taghir dt1 
    
    join tbl_pishnehad pOld1   on pOld1.id=dt1.oldpish_id
    join tbl_estelam eOld1     on eOld1.estelam_id=pOld1.estelam_id
    join tbl_bimeshode bsOld1  on bsOld1.id=pOld1.bimeshode_id
    join tbl_shakhs sOld1      on sOld1.shakhs_id=bsOld1.shakhs_id
    
    join tbl_pishnehad pNew1   on pNew1.id=dt1.newpish_id
    join tbl_estelam eNew1     on eNew1.estelam_id=pNew1.estelam_id
    join tbl_bimeshode bsNew1  on bsNew1.id=pNew1.bimeshode_id
    join tbl_shakhs sNew1      on sNew1.shakhs_id=bsNew1.shakhs_id
    
    where
    eold1.sen_bime_shode!=enew1.sen_bime_shode and sold1.tarikhe_tavallod=snew1.tarikhe_tavallod and e1.estelam_id=enew1.estelam_id 
  )
  ;