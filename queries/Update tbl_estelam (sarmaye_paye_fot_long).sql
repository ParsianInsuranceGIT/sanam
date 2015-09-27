update tbl_estelam es
set es.sarmaye_paye_fot_long=replace(es.sarmaye_paye_fot,',');

update tbl_credebit es
set es.amount_long=replace(es.amount,',');

update tbl_credebit es
set es.remaining_amount_long=replace(es.remaining_amount,',');

update tbl_ghest gh
set gh.HAGH_BIME_FOT_LONG=replace(gh.HAGH_BIME_FOT_YEKZA,',');

update tbl_ghestbandi gh
set gh.HAGH_BIME_AMRAZ_LONG=replace(gh.HAGH_BIME_AMRAZ,',');

update tbl_ghestbandi gh
set gh.HAGH_BIME_HADESE_LONG=replace(gh.HAGH_BIME_HADESE,',');

update tbl_ghestbandi gh
set gh.HAGH_BIME_MOAFIAT_LONG=replace(gh.HAGH_BIME_MOAFIAT,',');

update tbl_ghestbandi gh
set gh.HAGH_BIME_NAGHS_LONG=replace(gh.HAGH_BIME_NAGHS,',');