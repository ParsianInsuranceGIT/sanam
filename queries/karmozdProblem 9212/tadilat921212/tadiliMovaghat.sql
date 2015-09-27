select 
 n.kodenamayandekargozar "کد نماینده",
       2 "کارمزد",
        kg.karmozd_amount "مبلغ"                  ,
        1 "بیمه نامه",
        b.bimename_shomare "شماره بیمه نامه",
        'مبلغ پرداخت نشده کد موقتی' "عنوان",
        g.sarresid_date "سررسید قسط",
        k.serial "سریال پروژه",
        kg.id "-"
from tbl_karmozd_ghest kg
join tbl_karmozd k on k.karmozd_id=kg.karmozd_id
  join tbl_khate_sanad kh on kh.id=kg.khate_sanad_id
  join tbl_ghest g on g.id=kg.ghest_id
  join tbl_ghestbandi gb on g.ghestbandi_id=gb.id
--  join tbl_credebit cr on cr.ghest_id=g.id
  join tbl_bimename b on b.bimename_id=gb.bimename_id
  join tbl_pishnehad p on p.bimename_bimename_id=b.bimename_id
  join tbl_estelam es on es.estelam_id=p.estelam_id
  join tbl_namayande n on n.id=kg.namayandeid
where kg.type_karmozd='PARDAKHTE_CODE_MOVAGHATI' AND KG.KARMOZD_NAMAYANDE_ID IS NULL
and p.c_valid=1 and b.bimename_ready='yes';