package com.bitarts.common.displaytag;

import com.opensymphony.xwork2.ActionContext;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 12/6/12
 * Time: 8:09 PM
 */
public class PagingUtil {
    public static String prifixForOldValue = "pre_";
    public static final Integer MAX_OBJECTS_PER_PAGE = 30;

    public static PaginatedListImpl getPaginatedList_oldVersion(Criteria criteria){
        PaginatedListImpl<Object> paginatedList = new PaginatedListImpl<Object>();
        paginatedList.setObjectsPerPage(MAX_OBJECTS_PER_PAGE);
        Integer pageNumber = 1;
        boolean isExport=false;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.equalsIgnoreCase("page") || (name.startsWith("d-") && name.endsWith("-p"))) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]);
                }
                if((name.startsWith("d-") && name.endsWith("-e")))
                {
                    isExport=true;
                }
            }
        }

        paginatedList.setPageNumber(pageNumber);
        if(!isExport){
            int firstResult = paginatedList.getObjectsPerPage() * (paginatedList.getPageNumber()-1);
            criteria.setFirstResult(firstResult);
            criteria.setMaxResults(paginatedList.getObjectsPerPage());
        }
        List list = criteria.list();
        if(list != null)
            paginatedList.setList(list);
        criteria.setFirstResult(0);
        criteria.setMaxResults(Integer.MAX_VALUE);
        paginatedList.setFullListSize((Integer.parseInt(criteria.setProjection(Projections.rowCount()).list().get(0).toString())));
        return paginatedList;
    }

    public static PaginatedListImpl getPaginatedList(Criteria criteria){
        PaginatedListImpl<Object> paginatedList = new PaginatedListImpl<Object>();
        paginatedList.setObjectsPerPage(MAX_OBJECTS_PER_PAGE);
        paginatedList.setFullListSize(criteria.list().size());
        Integer pageNumber = 1;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.equalsIgnoreCase("page") || (name.startsWith("d-") && name.endsWith("-p"))) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]);
                    break;
                }
            }
        }
        paginatedList.setPageNumber(pageNumber);
        int firstResult = paginatedList.getObjectsPerPage() * (paginatedList.getPageNumber()-1);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(paginatedList.getObjectsPerPage());
        List list = criteria.list();
        if(list != null)
//            paginatedList.setList(list.subList(firstResult, Math.min(firstResult+paginatedList.getObjectsPerPage(), list.size())));
            paginatedList.setList(list);
//        paginatedList.setFullListSize(list.size());
//        System.out.println("list is returned"+list.size());
        return paginatedList;
    }

    public static PaginatedListImpl getPaginatedListUnlimite(Criteria criteria){
        PaginatedListImpl<Object> paginatedList = new PaginatedListImpl<Object>();
        paginatedList.setObjectsPerPage(Integer.MAX_VALUE);
        paginatedList.setFullListSize(criteria.list().size());
        Integer pageNumber = 1;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.equalsIgnoreCase("page") || (name.startsWith("d-") && name.endsWith("-p"))) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]);
                    break;
                }
            }
        }
        paginatedList.setPageNumber(1);
        int firstResult = paginatedList.getObjectsPerPage() * (paginatedList.getPageNumber()-1);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(paginatedList.getObjectsPerPage());
        List list = criteria.list();
        if(list != null)
//            paginatedList.setList(list.subList(firstResult, Math.min(firstResult+paginatedList.getObjectsPerPage(), list.size())));
            paginatedList.setList(list);
//        paginatedList.setFullListSize(list.size());
        return paginatedList;
    }

    public static PaginatedListImpl getPaginatedList(Criteria criteria, Criteria intactCriteria, String idName){
        PaginatedListImpl<Object> paginatedList = new PaginatedListImpl<Object>();
        paginatedList.setObjectsPerPage(MAX_OBJECTS_PER_PAGE);
        Integer pageNumber = 1;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.equalsIgnoreCase("page") || (name.startsWith("d-") && name.endsWith("-p"))) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]);
//                    break;
                }
                if(name.startsWith("d-") && name.endsWith("-e"))
                {
                    pageNumber=1;
                    paginatedList.setObjectsPerPage(Integer.MAX_VALUE);
                    break;
                }
            }
        }
        paginatedList.setPageNumber(pageNumber);
        List<Object> ids = criteria.setProjection(Projections.id()).list();
//        todo should be optimized
        Set<Object> idsSet = new HashSet<Object>();
        for (Object id : ids) {
            idsSet.add(id);
        }
        paginatedList.setFullListSize(idsSet.size());
        int firstResult = paginatedList.getObjectsPerPage() * (paginatedList.getPageNumber()-1);
        firstResult = Math.min(firstResult, ids.size());
        int maxToResult = Math.min(firstResult + paginatedList.getObjectsPerPage(), ids.size());
        intactCriteria.add(Restrictions.in(idName, ids.subList(firstResult,maxToResult)));
//        intactCriteria.setFirstResult(firstResult);
//        intactCriteria.setMaxResults(paginatedList.getObjectsPerPage());
        List list = intactCriteria.list();
        if(list != null)
            paginatedList.setList(list);
        return paginatedList;
    }

    public static PaginatedListImpl getPaginatedList(List list){
        PaginatedListImpl<Object> paginatedList = new PaginatedListImpl<Object>();
        paginatedList.setObjectsPerPage(MAX_OBJECTS_PER_PAGE);
        Integer pageNumber = 1;
        boolean isExport=false;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.equalsIgnoreCase("page") || (name.startsWith("d-") && name.endsWith("-p"))) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]);
                }
                if((name.startsWith("d-") && name.endsWith("-e")))
                {
                    isExport=true;
                }
            }
        }
        if(isExport)
        {
            paginatedList.setPageNumber(1);
            ArrayList rtnList=new ArrayList();
            for(int i=0;i<list.size();i++)
            {
                rtnList.add(list.get(i));
            }
            if(list != null)
                paginatedList.setList(rtnList);
        }
        else
        {
            paginatedList.setPageNumber(pageNumber);
            int firstResult = paginatedList.getObjectsPerPage() * (paginatedList.getPageNumber()-1);
            ArrayList rtnList=new ArrayList();
            for(int i=firstResult;i<firstResult+paginatedList.getObjectsPerPage()&& i<list.size();i++)
            {
                rtnList.add(list.get(i));
            }
            if(list != null)
                paginatedList.setList(rtnList);
            rtnList=new ArrayList();
            for(int i=0;i<Integer.MAX_VALUE && i<list.size();i++)
            {
                rtnList.add(list.get(i));
            }
            paginatedList.setFullListSize(rtnList.size());
        }
        return paginatedList;
    }

    public static int getPageNumberFromContext(){
        int pageNumber=1;
        if (ActionContext.getContext().getParameters().size() > 0)
        {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.equalsIgnoreCase("page") || (name.startsWith("d-") && name.endsWith("-p"))) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]);
                    break;
                }
            }
        }
        return pageNumber;
    }

    public static int getPageNumberFromContext(String pageNumberName){
        int pageNumber=-1;
        int oldPageNumber = 1;
        if (ActionContext.getContext().getParameters().size() > 0)
        {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.equalsIgnoreCase(pageNumberName)) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]);
                    break;
                }else if(name.equalsIgnoreCase(prifixForOldValue+pageNumberName)){
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    if(((String) o[0]).contains(","))
                    {
                        String oVal=((String) o[0]).substring(((String) o[0]).lastIndexOf(",")+1);
                        oldPageNumber=Integer.parseInt(oVal);
                    }
                    else
                        oldPageNumber = Integer.parseInt((String) o[0]);
                }
            }
        }
        if(pageNumber == -1){
            pageNumber = oldPageNumber;
        }
        return pageNumber;
    }
}
