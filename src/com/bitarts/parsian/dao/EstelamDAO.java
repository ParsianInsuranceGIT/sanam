package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.estelam.Estelam;
import com.bitarts.parsian.model.estelam.UserComment;

import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/7/11
 * Time: 5:36 PM
 */
public class EstelamDAO extends BaseDAO {
    public static String ID = "id";

    public void save(Estelam estelam){
        if (estelam.getNerkh_afzayesh_salaneh_sarmaye_fot() == null){
            estelam.setNerkh_afzayesh_salaneh_sarmaye_fot("0");
        }

        super.save(estelam);
    }

    public Estelam findEstelamById(Integer id) {
        return (Estelam) super.findById(Estelam.class,id);
    }

    public void updateEstelam(Estelam estelam) {
        super.saveOrUpdate(estelam);
    }

    public void saveUserComment(UserComment userComment) {
        super.save(userComment);
    }

    public List<UserComment> findAllUserComments() {
        return super.findAll(UserComment.class);
    }
}
