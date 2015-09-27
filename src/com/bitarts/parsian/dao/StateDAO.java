package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.State;
import org.hibernate.criterion.Restrictions;

import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/7/11
 * Time: 5:36 PM
 */
public class StateDAO extends BaseDAO {
    public static String ID = "id";
    public static String STATE_Name = "stateName";
    public static String TRANSITION_BEGIN = "transitionBegin";
    public static String TRANSITION_END = "transitionsEnd";
    public static String PISHNEHADAT = "pishnehadat";

    public State findById(Integer id) {
        return (State) super.findById(State.class, id);
    }

    public void save(State state) {
        super.save(state);
    }

    public List<State> findByStateMachine(String system) {
        return getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(State.class).add(Restrictions.eq("stateMachineName", system)).list();
    }

}
