<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <input type="hidden" id="payment_or_login" name="paymentOrLogin" value="${paymentOrLogin}"/>

            <p>
                بیمه گذار محترم لطفاً جهت مشاهده بیمه نامه عمروسرمایه گذاری و یا پرداخت اقساط خود شماره بیمه نامه خود را
                وارد نموده تا به صفحه مورد نظر هدایت شوید.
            </p>

    <div align="center"><form action="/bimenameCheck.action" id="form_dge">
            <label for="shomare_bimename"><b style="color: #2e6e9e">شماره بیمه نامه :</b></label>
            <input type="text" name="shomareBimename" id="shomare_bimename" class="validate[required,custom[shomare_bimename]]"/>
            <input type="submit" value="ورود"/>
    </form>
    </div>