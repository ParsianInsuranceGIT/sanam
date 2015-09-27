package com.bitarts.parsian.service;

import com.bitarts.parsian.model.estelam.Estelam;
import com.bitarts.parsian.model.estelam.UserComment;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 4:18 PM
 */
public interface IEstelamService extends IBaseService  {
    public static final String SERVICE_NAME = "estelamService";

    public void addNewEstelam(Estelam estelam);

    public Estelam getEstelamById(Integer id);

    void updateEstelam(Estelam estelam);

    public void saveComment(UserComment userComment);

    public List<UserComment> findUserComments();

    public Estelam adapte(Estelam estelam);
}
