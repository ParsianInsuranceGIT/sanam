
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

    <link type="text/css" rel="stylesheet" href="/css/style_life.css"/>
    <link type="text/css" rel="stylesheet" href="/css/validationEngine.jquery.css"/>
    <link type="text/css" rel="stylesheet" href="/css/redmond/jquery-ui-1.8.9.custom.css"/>
    <script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.8.9.custom.min.js"></script>
    <script type="text/javascript" src="/js/jquery.tipsy.js"></script>
    <script type="text/javascript" src="/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="/js/jquery.validationEngine-fa.js"></script>
    <script type="text/javascript" src="/js/jquery.validationEngine.js"></script>
    <script type="text/javascript" src="/js/jquery.global.js"></script>
    <script type="text/javascript" src="/js/jquery.loadmask.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>

    <script type="text/javascript">
        function selectMenu(num) {

            if (num == 1) {
                $('#li1').addClass('active');
                $('#li_1').addClass('active');
                $('#li2').removeClass('active');
                $('#li3').removeClass('active');
                $('#li_2').removeClass('active');

                document.getElementById('tab1').style.display = "";
                document.getElementById('tab2').style.display = "none";
                document.getElementById('tab3').style.display = "none";
                document.getElementById('tab5').style.display = "none";

            }
            if (num == 2) {

                $('#li2').addClass('active');
                $('#li1').removeClass('active');
                $('#li3').removeClass('active');
                $('#li_2').removeClass('active');
                $('#li_1').removeClass('active');

                document.getElementById('tab2').style.display = "";
                document.getElementById('tab3').style.display = "none";
                document.getElementById('tab1').style.display = "none";
                document.getElementById('tab5').style.display = "none";
            }
            if (num == 3) {
                $('#li3').addClass('active');
                $('#li1').removeClass('active');
                $('#li2').removeClass('active');
                $('#li_2').removeClass('active');
                $('#li_1').removeClass('active');

                document.getElementById('tab3').style.display = "";
                document.getElementById('tab1').style.display = "none";
                document.getElementById('tab2').style.display = "none";
                document.getElementById('tab5').style.display = "none";
            }
            if(num==5)
            {
                $('#li3').removeClass('active');
                $('#li1').removeClass('active');
                $('#li2').removeClass('active');
                $('#li_2').addClass('active');
                $('#li_1').removeClass('active');


                document.getElementById('tab3').style.display = "none";
                document.getElementById('tab1').style.display = "none";
                document.getElementById('tab2').style.display = "none";
                document.getElementById('tab5').style.display = "";
            }

        }
    </script>

</head>
<body>

    <div id="wrapper">
        <div id="header">
            <h1></h1>

            <h2></h2>
        </div>
        <div id="menu">
            <ul>
                <li class="active" id="li_1"><a onclick="selectMenu(1);" >صفحه نخست</a></li>
                <li id="li_2"><a onclick="selectMenu(5);" >درباره بیمه عمر</a></li>
                <li id="li_3"><a src="http://www.parsianinsurance.com/" >پورتال بیمه پارسیان</a></li>
            </ul>
        </div>
        <div id="content">
            <div id="left">
                <div class="post" id="tab1">
                    <h2></h2>
                    <p></p>
                    <br/>
                </div>
                <div class="post" id="tab2" style="display: none;">
                    <h2>سامانه اطلاع رسانی (مشاهده) بیمه نامه</h2>
                    <p>
                        یمه گذار محترم لطفاً جهت مشاهده بیمه نامه عمروسرمایه گذاری خود شماره بیمه نامه خود را وارد نموده
                        تا به صفحه مورد نظر هدایت شوید.
                    </p>

                    <div align="center">
                        <form action="/bimenameCheck.action" id="form_dge">
                            <input type="hidden" id="loginVal" name="paymentOrLogin" value="login"/>
                            <label for="shomare_bimename" onclick=""><b style="color: #2e6e9e">شماره بیمه نامه :</b></label>
                            <input type="text" name="shomareBimename" id="shomare_bimename" class="validate[required,custom[shomare_bimename]]"/>
                            <input type="submit" value="ورود"/>
                            <br/>             </br>
                            <div>
                                <span dir="LTR">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <b>
                                    <span style="color:#C00000;" dir="LTR">
                                        2210/xxxxxx/xx/xxxxxx : فرمت صحیح *
                                    </span>
                                </b>
                                        </br>
                                <span dir="LTR">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <b>
                                    <span style="color:#C00000;" dir="LTR">
                                        2430/xxxxxx/xx/xxxxxx : فرمت صحیح *
                                    </span>
                                </b>
                                        </br>
                                <span dir="LTR">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <b>
                                    <span style="color:#C00000;" dir="LTR">
                                        2440/xxxxxx/xx/xxxxxx : فرمت صحیح *
                                    </span>
                                </b>
                            </div>
                        </form>
                    </div>
                    <ul>
                        <li><strong></strong></li>
                    </ul>
                    <br/>
                </div>
                <div class="post" id="tab3" style="display: none;">
                    <h2>پرداخت اینترنتی اقساط</h2>

                    <p>
                        یمه گذار محترم لطفاً جهت پرداخت اقساط خود شماره بیمه نامه خود را وارد نموده تا به صفحه مورد نظر
                        هدایت شوید.
                    </p>

                    <div align="center">
                        <form action="/bimenameCheck.action" id="form_dge_payment">
                            <input type="hidden" id="paymentVal" name="paymentOrLogin" value="payment"/>
                            <label for="shomare_bimename"><b style="color: #2e6e9e">شماره بیمه نامه :</b></label>
                            <input type="text" name="shomareBimename" id="shomare_bimename_payment" class="validate[required,custom[shomare_bimename]]"/>
                            <input type="submit" value="ورود"/>
                            <br/></br>

                            <div >
                                <span dir="LTR">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <b>
                                    <span style="color:#C00000;" dir="LTR">
                                        2210/xxxxxx/xx/xxxxxx : فرمت صحیح *
                                    </span>
                                </b>
                                </br>
                                <span dir="LTR">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <b>
                                    <span style="color:#C00000;" dir="LTR">
                                        2430/xxxxxx/xx/xxxxxx : فرمت صحیح *
                                    </span>
                                </b>
                                </br>
                                <span dir="LTR">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <b>
                                    <span style="color:#C00000;" dir="LTR">
                                        2440/xxxxxx/xx/xxxxxx : فرمت صحیح *
                                    </span>
                                </b>
                            </div>
                        </form>
                    </div>
                    <ul>
                        <li><strong></strong></li>
                    </ul>
                    <br/>
                </div>
                <div class="post" id="tab5" style="display: none;">
                    <h2>درباره بیمه عمر</h2>
                    <p></p>
                    <br/>
                </div>

            </div>
            <div id="right">
                <h2></h2>
                <ul>
                    <li class="active" id="li1"><a href="#" onclick="selectMenu(1);">صفحه نخست</a></li>
                    <li id="li2"><a href="#" onclick="selectMenu(2);">سامانه اطلاع رسانی (مشاهده بیمه نامه)</a></li>
                    <li id="li3"><a href="#" onclick="selectMenu(3);">پرداخت اینترنتی اقساط </a></li>
                    <br/><br/><br/>
                </ul>
                <h2>اطلاعیه ها</h2>
                <hr/>
                <br/>

                <div>
                    <marquee direction="up" scrollamount="3">
                    </marquee>
                </div>
            </div>
        </div>
        <div id="footer">
            <p class="copyright">مدیریت فناوری اطلاعات بیمه پارسیان - 1392 ©</p>
        </div>
    </div>
</body>
</html>