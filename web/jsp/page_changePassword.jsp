<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<div align="center">
    <script type="text/javascript">
        function getUrlVars()
        {
            var vars = [], hash;
            var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
            for(var i = 0; i < hashes.length; i++)
            {
                hash = hashes[i].split('=');
                vars.push(hash[1]);
                vars[hash[0]] = hash[1];
            }
            return vars;
        }

        function getEnabalePasswordCheck(){
            var result = "";
            $.ajax({
                type: "POST",
                async : false,
                data: "userIdChangePass="+getUrlVars(),
                url: "getEnableValidationUserAjax",
                success: function (msg) {
                    result = msg;
                }
            });
            if (result == 'true'){
                return true;
            }else{
                return false;
            }
        }
        function checkPassword(){
            if (getEnabalePasswordCheck()){
                var letter = /[a-zA-Z]/;
                var number = /[0-9]/;

                if (password1.value.length < 8 || password2.value.length < 8){
                    alertMessage("رمز عبور باید بیشتر یامساوی 8 حرف باشد");
                    return;
                }
                if (password1.value.length != password2.value.length){
                    alertMessage("تکرار رمز عبور درست نمی باشد");
                    return;
                }
                if (password1.value != password2.value){
                    alertMessage("تکرار رمز عبور درست نمی باشد");
                    return;
                }
                if (!letter.test(password1.value) || !number.test(password1.value)) {
                    alertMessage("در رمز عبور باید از حرف و عدد استفاده شود");
                    return;
                }
            }
            document.forms[0].submit();
        }
    </script>
    <% if (request.getAttribute("show") == "show")%>
    <font color="red">لطفا در انتخاب کلمه عبور از حروف فارسی استفاده نکنید</font>

    <s:actionerror/>
    <s:actionmessage/>

        <form action="/changePassword" id="form_change_pass" method="post">
        <input type="hidden" value="${userTemp.id}" name="userTemp.id"/>
        <table dir="rtl">
            <tr>
                <td>رمز عبور</td>
                <td><input type="password" id="password" name="password" value="<s:property value="password"/>" AUTOCOMPLETE="off"/>
                </td>
            </tr>
            <tr>
                <td>رمز عبور جدید</td>
                <td><s:password key="password1" theme="simple" label=""/></td>
            </tr>
            <tr>
                <td>تکرار رمز عبور جدید</td>
                <td><s:password key="password2" theme="simple" label=""/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <%--<s:submit theme="simple" value="ثبت"/>--%>
                    <input type="button" onclick="checkPassword()" value="ثبت"/>
                </td>
            </tr>
        </table>
    </form>
</div>