select n.kodenamayandekargozar "کد نماینده",
       2 "کارمزد",
        (
          case 
            when n.type_enum='FORUSHANDE' then trunc(0.7 * ((trunc(replace(kh.amount,',')) * g.karmozd_real)/cr.amount_long))         
            when n.type_enum='NAMAYANDE_HOGHUGHI' or n.type_enum='NAMAYANDE_HAGHIGHI' or n.type_enum='BAJE_NAMAYANDE_HOGHUGHI' 
              then trunc(0.95 * ((trunc(replace(kh.amount,',')) * g.karmozd_real)/cr.amount_long)) 
            when n.type_enum='KARGOZAR_HAGHIGHI' or n.type_enum='KARGOZAR_HOGHUGHI'
              then
                case 
                  when b.bimename_tarikhsodur>='1392/04/01' then trunc(0.95 * ((trunc(replace(kh.amount,',')) * g.karmozd_real)/cr.amount_long))
                  when b.bimename_tarikhsodur<'1392/04/01' then trunc(0.9 * ((trunc(replace(kh.amount,',')) * g.karmozd_real)/cr.amount_long))
                end
          end 
        ) - kg.karmozd_amount "مبلغ"                  ,
        1 "بیمه نامه",
        b.bimename_shomare "شماره بیمه نامه",
        'مابه تفاوت' "عنوان",
        g.sarresid_date "سررسید قسط",
        k.serial "سریال پروژه",
        kg.id "-"
  from tbl_karmozd_ghest kg  
  join tbl_karmozd k on k.karmozd_id=kg.karmozd_id
  join tbl_khate_sanad kh on kh.id=kg.khate_sanad_id
  join tbl_ghest g on g.id=kg.ghest_id
  join tbl_ghestbandi gb on g.ghestbandi_id=gb.id
  join tbl_credebit cr on cr.ghest_id=g.id
  join tbl_bimename b on b.bimename_id=gb.bimename_id
  join tbl_pishnehad p on p.bimename_bimename_id=b.bimename_id
  join tbl_estelam es on es.estelam_id=p.estelam_id
  join tbl_namayande n on n.id=kg.namayandeid
where 
  p.c_valid=1 and b.bimename_ready='yes' and b.state_id<=510  and es.nahve_pardakht_hagh_bime!='yekja'
  and (kg.type_karmozd='ADI' or kg.type_karmozd='TALIGHI' or kg.type_karmozd='PARDAKHTE_CODE_MOVAGHAT')
  and (
        (
          n.type_enum='FORUSHANDE' 
          and 
          (
            kg.karmozd_amount <> trunc(0.7 * ((trunc(replace(kh.amount,',')) * g.karmozd_real)/cr.amount_long)) 
            and kg.karmozd_amount <> trunc(0.7 * ((trunc(replace(kh.amount,',')) * g.karmozd_real)/cr.amount_long))+1 
            and kg.karmozd_amount <> trunc(0.7 * ((trunc(replace(kh.amount,',')) * g.karmozd_real)/cr.amount_long))-1
          )
        )        
        or 
        (
          (n.type_enum='NAMAYANDE_HOGHUGHI' or n.type_enum='NAMAYANDE_HAGHIGHI' or n.type_enum='BAJE_NAMAYANDE_HOGHUGHI') 
          and 
          (
            kg.karmozd_amount <> trunc(0.95 * ((trunc(replace(kh.amount,',')) * g.karmozd_real)/cr.amount_long))
            and kg.karmozd_amount <> trunc(0.95 * ((trunc(replace(kh.amount,',')) * g.karmozd_real)/cr.amount_long))+1
            and kg.karmozd_amount <> trunc(0.95 * ((trunc(replace(kh.amount,',')) * g.karmozd_real)/cr.amount_long))-1
          )
        )        
        or
        (
          (n.type_enum='KARGOZAR_HAGHIGHI' or n.type_enum='KARGOZAR_HOGHUGHI')
          and 
          (
            (
              b.bimename_tarikhsodur>='1392/04/01' 
              and 
              (
                kg.karmozd_amount <> trunc(0.95 * ((trunc(replace(kh.amount,',')) * g.karmozd_real)/cr.amount_long))
                and kg.karmozd_amount <> trunc(0.95 * ((trunc(replace(kh.amount,',')) * g.karmozd_real)/cr.amount_long))+1
                and kg.karmozd_amount <> trunc(0.95 * ((trunc(replace(kh.amount,',')) * g.karmozd_real)/cr.amount_long))-1
              )
            )
            or
            (
              b.bimename_tarikhsodur<'1392/04/01' 
              and 
              (
                kg.karmozd_amount <> trunc(0.9 * ((trunc(replace(kh.amount,',')) * g.karmozd_real)/cr.amount_long))
                and kg.karmozd_amount <> trunc(0.9 * ((trunc(replace(kh.amount,',')) * g.karmozd_real)/cr.amount_long))+1
                and kg.karmozd_amount <> trunc(0.9 * ((trunc(replace(kh.amount,',')) * g.karmozd_real)/cr.amount_long))-1
              )
            )
          )
        )
      )                        
;