package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.Role;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.bimename.DasteSerial;
import com.bitarts.parsian.model.bimename.Serial;
import com.bitarts.parsian.service.ILoginService;
import com.bitarts.parsian.service.INamayandeService;
import com.bitarts.parsian.service.IPishnehadService;
import com.bitarts.parsian.service.IUserService;
import com.bitarts.parsian.service.calculations.Reports.CountSequentialSerials;
import com.bitarts.parsian.viewModel.SequentialSerial;
import com.bitarts.parsian.viewModel.SerialsVM;
import com.opensymphony.xwork2.ActionContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: a-khezri
 * Date: 2/17/13
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class SerialsAction extends BaseAction implements ServletContextAware
{
    private User user;
    private ILoginService loginService;
    private PaginatedListImpl<DasteSerial> serialPaginatedList;
    private PaginatedListImpl<SerialsVM> serialsVMPgList;
    private IPishnehadService pishnehadService;
    private IUserService userService;
    private INamayandeService namayandeService;
    private Integer kodeDaste,dasteSerialId;
    private String elateEbtal,tozihat,tarikheEbtal;
    private String vaziateDaste;
    private Long serialFirst,serialLast;
    private Long namayandegiId,vahedeSodurId,bazaryabId;
    private String bimenameType;
    private List<User> bazaryabs;
    private List<Serial> serials;
    private DasteSerial dasteSerial;
    private Namayande namayande;
    private Serial serial;
    private String destFileDIR, realPath="", sourceFilePath;
    private DasteSerial lastDasteSerial;
    private SequentialSerial sequentialSerial;


    public void setServletContext(ServletContext servletContext)
    {
        realPath = servletContext.getRealPath("/");
        WebApplicationContext wac= WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.loginService=(ILoginService) wac.getBean(ILoginService.SERVICE_NAME);
        this.pishnehadService= (IPishnehadService) wac.getBean(IPishnehadService.SERVICE_NAME);
        this.userService=(IUserService) wac.getBean(IUserService.SERVICE_NAME);
        this.namayandeService=(INamayandeService) wac.getBean(INamayandeService.SERVICE_NAME);
    }

    public  String listAllSerial()
    {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        user=loginService.findUserByUsername(username);
//        serialPaginatedList=new PaginatedListImpl<DasteSerial>();
//        serialPaginatedList.setObjectsPerPage(15);
        serialsVMPgList=new PaginatedListImpl<SerialsVM>();
        serialsVMPgList.setObjectsPerPage(30);
        Integer pageNumber = 0;
        if (ActionContext.getContext().getParameters().size() > 0)
        {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs)
            {
                if (name.startsWith("d-") && name.endsWith("-p"))
                {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]) - 1;
                    break;
                }
            }
        }
//        serialPaginatedList.setPageNumber(pageNumber);
        serialsVMPgList.setPageNumber(pageNumber);
//        serialPaginatedList= pishnehadService.findAllDasteSerials(serialPaginatedList, vaziateDaste, serialFirst, serialLast, namayandegiId, bimenameType, user);
        SerialsVM svmOb= new SerialsVM();
        if(vaziateDaste!=null && !vaziateDaste.equals(""))
            svmOb.setVaziateDaste(DasteSerial.VaziateDaste.valueOf(vaziateDaste));
        svmOb.setFirstShomareSerial(serialFirst);
        svmOb.setLastShomareSerial(serialLast);
        svmOb.setNamayandeId(namayandegiId);
        svmOb.setBimenameType(bimenameType);
        svmOb.setVahedeSodurId(user.getMojtamaSodoor().getId());
        serialsVMPgList=pishnehadService.findAllDasteSerials(serialsVMPgList,svmOb);
//        getSession().put("listAllSerial",serialPaginatedList);
        return SUCCESS;
    }

    public String ebtalSerials()
    {
        int count= (int) ((serialLast-serialFirst)+1);
        Long[] shomareSerials=new Long[count];
        Long s=serialFirst;
        for(int i=0;s<=serialLast;i++,s++)
        {
            shomareSerials[i]=s;
        }
        List<Serial> serialList = pishnehadService.findSerialByShomareSerial(shomareSerials);
        if(serialList.size()!=0 && !serialList.equals(null))
        {
            for(Serial serialOb:serialList)
            {
                serialOb.setVaziatSerial(Serial.VaziatSerial.valueOf("EBTAL_SHODE"));
                serialOb.setTozihat(tozihat);
                serialOb.setElateEbtal(Serial.ElateEbtal.valueOf(elateEbtal));
                serialOb.setTarikhEbtal(tarikheEbtal);
                serials= new ArrayList<Serial>();
                serials.add(serialOb);
            }
            pishnehadService.saveOrUpdateSerials(serials);
            addActionMessage("سریال ها با موفقیت ابطال شد");
        }
        else
        {
        }
            return SUCCESS;

    }

    public String serialsEstefadeNashode()
    {
        if (ActionContext.getContext().getParameters().size() > 0)
        {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs)
            {
                if (name.startsWith("_chk"))
                {
                    String[] dasteSerialIds = (String[]) ActionContext.getContext().getParameters().get(name);

                    Integer dasteSerialIdFirst=(Integer) Integer.parseInt(dasteSerialIds[0]);
                    setSerialFirst(pishnehadService.minOrMaxSerialByIdDaste("MIN",dasteSerialIdFirst));

                    Integer dasteSerialIdLast=(Integer) Integer.parseInt(dasteSerialIds[dasteSerialIds.length-1]);
                    setSerialLast(pishnehadService.minOrMaxSerialByIdDaste("MAX",dasteSerialIdLast));
                }
            }
        }
        serials=pishnehadService.findSerialsEstefadeNashode(serialFirst,serialLast);
        if (serials.size()>0)
        {
            sequentialSerial=fillSequentialSerialList(serials);
            if (serialFirst!=null)sequentialSerial.setFromSerial(serialFirst);
            else sequentialSerial.setFromSerial(((Integer)1).longValue());
            if (serialLast!=null)sequentialSerial.setToSerial(serialLast);
            else sequentialSerial.setToSerial(((Integer)serials.size()).longValue());
            sequentialSerial.setTotalCount(((Integer)serials.size()).longValue());
        }
        else
        {
            sequentialSerial=new SequentialSerial();   sequentialSerial.setFromSerial(serialFirst);sequentialSerial.setToSerial(serialLast);sequentialSerial.setTotalCount(((Integer) 0).longValue());
        }
        try
        {
            destFileDIR = realPath + destFileDIR + "serialsNotUsed.pdf";
            boolean b = (new File(realPath + destFileDIR)).mkdirs();
            File destFile = new File(destFileDIR);
            if (!destFile.exists()) destFile.createNewFile();
            JasperCompileManager.compileReportToFile(realPath + "report/bimename/serialsNotUsed_fSerials.jrxml",realPath + "report/bimename/serialsNotUsed_fSerials.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/bimename/serialsNotUsed_lSerials.jrxml",realPath + "report/bimename/serialsNotUsed_lSerials.jasper");
            JasperCompileManager.compileReportToFile(realPath + "report/bimename/serialsNotUsed_counts.jrxml",realPath + "report/bimename/serialsNotUsed_counts.jasper");
            JasperCompileManager.compileReportToFile(realPath + sourceFilePath, destFileDIR);
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String editDasteSerials()
    {
        dasteSerial=pishnehadService.findDasteSerialById(dasteSerialId);
        dasteSerial.setVaziateDaste(DasteSerial.VaziateDaste.valueOf(vaziateDaste));
        pishnehadService.saveOrUpdateDasteSerials(dasteSerial);
        return SUCCESS;
    }

    public String sabteSerials()
    {
        serials=new ArrayList<Serial>();
       List<Long> registeredSerials=new ArrayList<Long>();
        for(Long i=serialFirst;i<=serialLast;i++)
        {
            if(pishnehadService.isAvailableSerial(i))
            {
                registeredSerials.add(i);
            }
            else
            {
                serial=new Serial();
                serial.setDasteSerial(dasteSerial);
                serial.setVaziatSerial(Serial.VaziatSerial.FAAL);
                serial.setShomareSerial(i);
                serials.add(serial);
            }
        }
        if(registeredSerials.size()>0)
        {
            if(registeredSerials.size()==1)
            {
                addActionMessage( "سریال " +registeredSerials.get(0) + " قبلاً ایجاد شده است.");
            }
            else
            {
                String strMessage=" سریال های ";
                for(int i=0;i<registeredSerials.size();i++)
                {
                    strMessage=strMessage+ registeredSerials.get(i) + " و ";
                }
                strMessage.replace("و","");
                addActionMessage(strMessage+"قبلاً ایجاد شده اند.");
            }
            return SUCCESS;
        }

        else
        {
            dasteSerial.setVaziateDaste(DasteSerial.VaziateDaste.valueOf(vaziateDaste));
            dasteSerial.setNamayandegi(namayandeService.getNamayandeById(namayandegiId));
            dasteSerial.setVahedeSodur(namayandeService.getNamayandeById(vahedeSodurId));
//            dasteSerial.setBazaryab(userService.getUserById(bazaryabId));
            pishnehadService.saveOrUpdateDasteSerials(dasteSerial);
            pishnehadService.saveOrUpdateSerials(serials);
            addActionMessage("دسته سریال جدید با موفقیت ایجاد شد");
            return SUCCESS;
        }
    }

    public SequentialSerial fillSequentialSerialList(List<Serial> serials)
    {
        List<Serial> fSerialList=new ArrayList<Serial>();
        List<Serial> lSerialList=new ArrayList<Serial>();
        List<CountSequentialSerials> countList =new ArrayList<CountSequentialSerials>();

        Serial serialOb;
        Serial fSerial;
        Serial lSerial;
        CountSequentialSerials count=new CountSequentialSerials();

        SequentialSerial sequentialSerial;
        fSerial=serials.get(0);
        lSerial=serials.get(0);
        count.setCount(((Integer)1).longValue());

        for(int i =1;i<serials.size();i++)
        {
            serialOb=serials.get(i);

            if(serialOb.getShomareSerial()==(lSerial.getShomareSerial()+1))
            {
                lSerial=serialOb;
                count.setCount(count.getCount()+1);
            }
            else
            {
                fSerialList.add(fSerial);
                lSerialList.add(lSerial);
                countList.add(count);
                fSerial=serialOb;
                lSerial=serialOb;
                count=new CountSequentialSerials();
                count.setCount(((Integer)1).longValue());
            }
        }
        fSerialList.add(fSerial);
        lSerialList.add(lSerial);
        countList.add(count);
        sequentialSerial=new SequentialSerial();
        sequentialSerial.setCounts(countList);
        sequentialSerial.setLSerials(lSerialList);
        sequentialSerial.setFSerials(fSerialList);
        return sequentialSerial;
    }
//-------------------------Getter & Setter------------------------------------------------------------------------------


    public void setUser(User user)
    {
        this.user=user;
    }

    public User getUser()
    {
        return user;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public SequentialSerial getSequentialSerial() {
        return sequentialSerial;
    }

    public void setSequentialSerial(SequentialSerial sequentialSerial)
    {
        this.sequentialSerial=sequentialSerial;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public DasteSerial getLastDasteSerial()
    {
        return pishnehadService.lastDasteSerial();
    }

    public void setLastDasteSerial(DasteSerial lastDasteSerial) {
        this.lastDasteSerial = lastDasteSerial;
    }

    public String getDestFileDIR()
    {
        return destFileDIR;
    }

    public void setDestFileDIR(String destFileDIR)
    {
        this.destFileDIR = destFileDIR;
    }

    public Integer getKodeDaste() {
        return kodeDaste;
    }

    public void setKodeDaste(Integer kodeDaste) {
        this.kodeDaste = kodeDaste;
    }

    public Integer getDasteSerialId() {
        return dasteSerialId;
    }

    public void setDasteSerialId(Integer dasteSerialId) {
        this.dasteSerialId = dasteSerialId;
    }

    public String getTozihat() {
        return tozihat;
    }

    public void setTozihat(String tozihat) {
        this.tozihat = tozihat;
    }

    public String getTarikheEbtal() {
        return tarikheEbtal;
    }

    public void setTarikheEbtal(String tarikheEbtal) {
        this.tarikheEbtal = tarikheEbtal;
    }

    public String getElateEbtal() {
        return elateEbtal;
    }

    public void setElateEbtal(String elateEbtal) {
        this.elateEbtal = elateEbtal;
    }

    public Serial getSerial() {
        return serial;
    }

    public void setSerial(Serial serial) {
        this.serial = serial;
    }

    public Long getBazaryabId() {
        return bazaryabId;
    }

    public void setBazaryabId(Long bazaryabId) {
        this.bazaryabId = bazaryabId;
    }

    public Long getVahedeSodurId() {
        return vahedeSodurId;
    }

    public void setVahedeSodurId(Long vahedeSodurId) {
        this.vahedeSodurId = vahedeSodurId;
    }

    public Namayande getNamayande() {
        return namayande;
    }

    public void setNamayande(Namayande namayande) {
        this.namayande = namayande;
    }

    public List<Serial> getSerials()
    {
        return serials;
    }

    public void setSerials(List<Serial> serials)
    {
        this.serials = serials;
    }

    public DasteSerial getDasteSerial()
    {
        return dasteSerial;
    }

    public void setDasteSerial(DasteSerial dasteSerial)
    {
        this.dasteSerial = dasteSerial;
    }

    public List<User> getBazaryabs()
    {
        if(bazaryabs==null)
        {
            PaginatedListImpl<User> userPaginatedList=new PaginatedListImpl<User>();
            int roleId=11;
            List<Role> roleList=userService.getRoleList();
            for(Role role:roleList)
            {
                if(role.getRoleName().equals("ROLE_BAZARYAB"))      //get RoleId By RoleName. . .
                    roleId=role.getId();
            }
            bazaryabs =userService.findAllUserByRoleId(roleId);
        }
        return bazaryabs;
    }

    public void setBazaryabs(List<User> bazaryabs)
    {
        this.bazaryabs = bazaryabs;
    }

    public Long getNamayandegiId()
    {
        return namayandegiId;
    }

    public void setNamayandegiId(Long namayandegiId)
    {
        this.namayandegiId = namayandegiId;
    }

    public String getBimenameType()
    {
        return bimenameType;
    }

    public void setBimenameType(String bimenameType)
    {
        this.bimenameType = bimenameType;
    }

    public String getVaziateDaste()
    {
        return vaziateDaste;
    }

    public void setVaziateDaste(String vaziateDaste)
    {
        this.vaziateDaste = vaziateDaste;
    }

    public Long getSerialFirst()
    {
        return serialFirst;
    }

    public void setSerialFirst(Long serialFirst)
    {
        this.serialFirst = serialFirst;
    }

    public Long getSerialLast()
    {
        return serialLast;
    }

    public void setSerialLast(Long serialLast)
    {
        this.serialLast = serialLast;
    }

    public PaginatedListImpl<SerialsVM> getSerialsVMPgList()
    {
        return serialsVMPgList;
    }

    public void setSerialsVMPgList(PaginatedListImpl<SerialsVM> serialsVMPgList)
    {
        this.serialsVMPgList = serialsVMPgList;
    }
}
