package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.Sequence;
import oracle.jdbc.OracleTypes;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Aug 8, 2011
 * Time: 5:35:33 PM
 */
public class SequenceDAO extends BaseDAO {
    public static final String ID = "id";
    public static final String SEQUENCE_NAME = "sequenceName";
    public static final String SEQUENCE_COUNT = "sequenceCount";
    public static final String DESCRIPTION = "description";


    public boolean isRepetitiousIssuanceCode(String issuanceCode)
    {
        long count  =(Long) getSession().createQuery("select count(n) from Namayande n where n.issuanceCode=:code").setString("code",issuanceCode).uniqueResult();
        if (count==0)
            return false;
        return true;
    }

    public Long getLastSerialBankInfo()
    {
        return (Long) getSession().createQuery("select max(b.serialId) from BankInfo b").uniqueResult();
    }

    public boolean isAvailableSequenceForNamayande(Long namayandeId, String year,String seq)
    {
        Criteria criteria=getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Sequence.class,"s").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("s.sequenceName","SUB_SHOMARE_MOSHTARI"))
                .add(Restrictions.eq("s.sequenceCount",seq))
                .add(Restrictions.eq("s.year",year));
        criteria.createCriteria("s.sequenceNamayande","n").add(Restrictions.eq("n.id",namayandeId));
        if(criteria.list().size()==1)
            return true;
        else
            return false;
    }

    public String getLastSequence(Long namayandeId, String year)
    {
        return (String)
                getSession().createQuery("select max(s.sequenceCount) from Sequence s join s.sequenceNamayande n where s.sequenceName = :seqName and s.year = :y and n.id = :nId ")
                    .setString("seqName","SUB_SHOMARE_MOSHTARI")
                    .setString("y",year)
                    .setLong("nId",namayandeId)
                .uniqueResult();
    }

    public Sequence getSequence(String sequenceName){
        try {
            return (Sequence) super.findObjectByProperty(Sequence.class, SEQUENCE_NAME, sequenceName);
        } catch (Exception e) {
           return null; 
        }
    }
    public Sequence getSequence(String sequenceName, String kodeNamayande){
        Criterion criterion = Restrictions.and(
                Restrictions.eq("s.sequenceName", sequenceName),
                Restrictions.eq("n.kodeNamayandeKargozar", kodeNamayande));
        List<Sequence> result = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Sequence.class,"s").createCriteria("s.sequenceNamayande","n")
                .add(criterion).list();
        if(result.size() == 0)
            return null;
        else
            return result.get(0);
    }
    public Sequence getSequenceElhaghieForBimename(String sequenceName, int bimenameId){
        Criterion criterion = Restrictions.and(
                Restrictions.eq("s.sequenceName", sequenceName),
                Restrictions.eq("b.id", bimenameId));
        List<Sequence> result = getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createCriteria(Sequence.class,"s").createCriteria("s.bimename_elhaghie","b")
                .add(criterion).list();
        if(result.size() == 0)
            return null;
        else
            return result.get(0);
    }
    public void updateSequence(Sequence sequence){
        super.saveOrUpdate(sequence);
    }

    public String nextShomareRadifElhaghie() {
        Long elhaghieCount = (Long) getSession().createQuery("select count(*) from Elhaghiye e where e.state.id=:sid").setParameter("sid", Constant.ELHAGHIYE_FINAL_STATE).uniqueResult();
        elhaghieCount++;
        return String.valueOf(elhaghieCount);
    }

    public String getSequenceCountSPNamayande(String sequenceName, Long namayandeId) {
        try {
            CallableStatement st = getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareCall("{? = call GET_SEQUENCE(?, ?, ?, ?, ?)}");
            st.registerOutParameter(1, OracleTypes.VARCHAR);
            st.setInt(2, 2);
            st.setString(3, sequenceName);
            st.setLong(4, namayandeId);
            st.setNull(5, OracleTypes.NUMBER);
            st.setNull(6, OracleTypes.VARCHAR);
            st.execute();
            return st.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "";
    }

    public String getSequenceCountSPNamayandeYear(String sequenceName, Long namayandeId, String year) {
        try {
            CallableStatement st = getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareCall("{? = call GET_SEQUENCE(?, ?, ?, ?, ?)}");
            st.registerOutParameter(1, OracleTypes.VARCHAR);
            st.setInt(2, 4);
            st.setString(3, sequenceName);
            st.setLong(4, namayandeId);
            st.setNull(5, OracleTypes.NUMBER);
            st.setString(6, year);
            st.execute();
            return st.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "";
    }

    public String getSequenceCountSPBimename(String sequenceName, int bimenameId) {
        try {
            CallableStatement st = getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareCall("{? = call GET_SEQUENCE(?, ?, ?, ?, ?)}");
            st.registerOutParameter(1, OracleTypes.VARCHAR);
            st.setInt(2, 3);
            st.setString(3, sequenceName);
            st.setNull(4, OracleTypes.NUMBER);
            st.setLong(5, bimenameId);
            st.setNull(6, OracleTypes.VARCHAR);
            st.execute();
            return st.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "";
    }

    public String getSequenceCountSP(String sequenceName) {
        try {
            CallableStatement st = getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareCall("{? = call GET_SEQUENCE(?, ?, ?, ?, ?)}");
            st.registerOutParameter(1, OracleTypes.VARCHAR);
            st.setInt(2, 1);
            st.setString(3, sequenceName);
            st.setNull(4, OracleTypes.NUMBER);
            st.setNull(5, OracleTypes.NUMBER);
            st.setNull(6, OracleTypes.VARCHAR);
            st.execute();
            return st.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "";
    }
}
