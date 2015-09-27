package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.Transition;
import org.hibernate.Criteria;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/20/11
 * Time: 3:53 PM
 */
public class TransitionDAO extends BaseDAO {
    public static String ID = "id";
    public static String transitionName = "transitionName";
    public static String roles = "roles";
    public static String stateBegin = "stateBegin";
    public static String stateEnd = "stateEnd";

    public Transition findById(Integer transitionId) {
        return (Transition) super.findById(Transition.class,transitionId);
    }

    public List<Integer> getStateBeginsOfTransitions(List<Integer> roles)
    {
        Query query= getSession().createQuery("select t.stateBegin.id from Role r join r.transitions t where r.id in (:rolesId)").setParameterList("rolesId",roles)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Integer> list=query.list();
        return list;
    }
}
