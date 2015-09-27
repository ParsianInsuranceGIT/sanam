package com.bitarts.parsian.security;

import com.bitarts.parsian.model.User;
import com.bitarts.parsian.service.IPishnehadService;

/**
 * Created by IntelliJ IDEA.
 * User: ramtinb
 * Date: 4/4/12
 * Time: 9:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class URISecurityManager {

    public Boolean hasPermissionToViewPisnehadAction(String actionName, Integer pishnehadId, IPishnehadService pishnehadService, User user,String tab)
    {
        Boolean isValid= false;
        if (actionName.equalsIgnoreCase("editShowForm"))
        {
            isValid = pishnehadService.hasPermissionToViewPisnehad(user,pishnehadId);
        }
        else if(actionName.equalsIgnoreCase("editShowFormReadOnly"))
        {
            isValid = pishnehadService.hasPermissionToViewAllPishnehad(user,pishnehadId,tab);
        }

        return  isValid;

//        Boolean isValid = false;
//        PaginatedListImpl<Pishnehad> lst;
//        if (actionName.equalsIgnoreCase("editShowForm")) {
//            lst = (PaginatedListImpl<Pishnehad>) ActionContext.getContext().getSession().get("reportResult");
//        } else if (actionName.equalsIgnoreCase("editShowFormReadOnly")) {
//            lst = (PaginatedListImpl<Pishnehad>) ActionContext.getContext().getSession().get("viewReportResult");
//        } else {
//            return isValid;
//        }
//        if (lst != null) {
//            for (int i = 0; i < lst.getList().size(); i++) {
//                Pishnehad p = (Pishnehad) lst.getList().get(i);
//                if (p.getId().toString().equalsIgnoreCase(requestedId)) {
//                    isValid = true;
//                    break;
//                }
//            }
//        } else isValid = true;
//
//        return isValid;
    }


}