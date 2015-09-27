-- update bimename karmoz annhai ke sefr ast null shavad
-- + update 3 field majmoo dar ghestbandi
-- update ghest paid_amoun va real_amount anhaii ke sefr ast null shavad
-- + hazfe karmozd_ghest ba khate_sanad_id =6914529
-- delete karmozd 0 dar 2 projeye jadid
delete from (select  kgh.*
from tbl_karmozd_ghest kgh 
        join tbl_karmozd_namayande kn on kn.id = kgh.karmozd_namayande_id
        join tbl_namayande nm on nm.id = kn.namayande_id
where  ( kgh.karmozd_id='7299396' or kgh.karmozd_id='7240334') and
        nm.type_enum not in ('ICD','SHOBE','MOJTAMA') and
        kgh.karmozd_amount=0)
;

update tbl_bimename 
set bimename_karmozd = null
where bimename_karmozd = 0;

select  kgh.*,kn.namayande_id
from tbl_karmozd_ghest kgh 
        join tbl_karmozd_namayande kn on kn.id = kgh.karmozd_namayande_id
        join tbl_namayande nm on nm.id = kn.namayande_id
where  --( kgh.karmozd_id='7299396' or kgh.karmozd_id='7240334') and
        nm.type_enum not in ('ICD','SHOBE','MOJTAMA') and
        kgh.karmozd_amount=0;

-- check karmozd bargashtia
SELECt gh.sarresid_date,kgh.khate_sanad_id,kgh.id,kgh.karmozd_id,kn.namayande_id,kgh.karmozd_amount,b.bimename_shomare,kgh.id,kgh.karmozdbargashti_id,kr.serial
FROM tbl_karmozd_ghest kgh
              join tbl_karmozd_namayande kn on kn.id = kgh.karmozd_namayande_id
              join tbl_karmozd kr on kr.karmozd_id = kgh.karmozd_id
              join tbl_ghest gh on kgh.ghest_id = gh.id
              join tbl_ghestbandi gb on gh.ghestbandi_id = gb.id
              join tbl_bimename b on gb.bimename_id = b.bimename_id
where kgh.khate_sanad_id in (5695530,5757437,6326745,6326746)
--          and kgh.type_karmozd='KARMOZD_BARGASHTI'
order by kgh.khate_sanad_id;

-------------------------------------------------------Bargashti assign to ADI--------------- 
select distinct khate_sanad_id
from tbl_karmozd_ghest 
where KARMOZD_ID=7240334
and type_karmozd='ADI'
and karmozdbargashti_id is null
and khate_sanad_id in (select khate_sanad_id from tbl_karmozd_ghest where type_karmozd='KARMOZD_BARGASHTI' AND KARMOZD_ID=7240334);


update tbl_karmozd_ghest kg 
set kg.karmozdbargashti_id=(select distinct kg1.id from tbl_karmozd_ghest kg1 where kg1.khate_sanad_id = kg.khate_sanad_id and kg1.karmozd_id=kg.karmozd_id and kg1.type_karmozd='KARMOZD_BARGASHTI' )
where kg.karmozdbargashti_id is null
and kg.type_karmozd='ADI' 
and kg.khate_sanad_id in (5942940,5942885,6704116,6703995,6413701,6589355,6413702)
;