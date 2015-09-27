update tbl_khate_Sanad kh
set  kh.BEDEHIREMAINING=( 
                            select remaining_amount_long 
                            from  tbl_credebit
                            where tbl_credebit.id=kh.bedehi_credebit_id
                        );                                             