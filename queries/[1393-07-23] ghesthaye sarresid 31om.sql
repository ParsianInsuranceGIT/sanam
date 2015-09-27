update tbl_ghest g set g.sarresid_date = substr(g.sarresid_date, 1, 8) || '30'
where substr(g.sarresid_date, 6, 2) > 6 
and substr(g.sarresid_date, 6, 2) < 12
and substr(g.sarresid_date, 9, 2) > 30;

update tbl_ghest g set g.sarresid_date = substr(g.sarresid_date, 1, 8) || '29'
where substr(g.sarresid_date, 6, 2) = 12
and substr(g.sarresid_date, 9, 2) > 29;