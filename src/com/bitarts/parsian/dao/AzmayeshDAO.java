package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.Azmayesh;
import com.bitarts.parsian.model.Clinic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/7/11
 * Time: 5:36 PM
 */
public class AzmayeshDAO extends BaseDAO {
    public static String ID = "id";

    public boolean saveAzmayesh(Azmayesh azmayesh, Integer clinicId) {
        Clinic theClinic = (Clinic) super.findById(Clinic.class, clinicId);

        if (azmayesh.getClinics() == null) {
            azmayesh.setClinics( new HashSet<Clinic>());
        }

        for ( Azmayesh az : theClinic.getAzmayeshs() )
        {
            if (az.getAzmayeshName().getId().equals(azmayesh.getAzmayeshName().getId()))
            {
                return false;
            }
        }
        
        azmayesh.getClinics().add(theClinic);
        super.save(azmayesh);
        theClinic.getAzmayeshs().add(azmayesh);
        super.update(theClinic);

        return true;
    }
}
