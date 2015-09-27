(function ($) {
    $.fn.validationEngineLanguage = function () {
    };
    $.validationEngineLanguage = {
        newLang:function () {
            $.validationEngineLanguage.allRules = {
                "required":{                // Add your regex rules here, you can take telephone as an example
                    "regex":"none",
                    "alertText":"* این فیلد الزامی است",
                    "alertTextCheckboxMultiple":"* لطفا یکی از موارد را انتخاب کنید",
                    "alertTextCheckboxe":"* انتخاب این مورد الزامی است"},

                "range":{
                    "regex":/^([0-2][0|5])$/,
                    "alertText":"5 or 10"},
                "rangeDarsad":{
                    "regex":/^(\d?\d|100)$/,
                    "alertText":"عدد باید بین 0 تا 100 باشد"
                },
                "sarmaye_paye_fot":{
                    "regex":/^([1-9]\d,\d\d\d,\d\d\d|[1-9]\d\d,\d\d\d,\d\d\d|[1-9],\d\d\d,\d\d\d,\d\d\d|2,000,000,000)$/,
                    "alertText":"* حداقل ميزان سرمايه فوت برابر 10 ميليون ريال و حداکثر 2 ميليارد ريال مي باشد"
                },
                "sarmaye_paye_fot_dar_asar_hadese":{
//                    "regex":/^([3-9]\d,\d\d\d,\d\d\d|[1-9]\d\d,\d\d\d,\d\d\d|1,000,000,000)$/,
                    "nname":"val_sarmaye_paye_fot_dar_asar_hadese",
                    "alertText":"* حداقل ميزان سرمايه فوت در اثر حادثه برابر 30 ميليون ريال و حداكثر 2 ميليارد ريال و کمتر از 3 برابر سرمایه پایه فوت مي باشد"
                },
                "sarmaye_paye_fot_dar_asar_hadese_multiple_pers1":{
//                    "regex":/^([3-9]\d,\d\d\d,\d\d\d|[1-9]\d\d,\d\d\d,\d\d\d|1,000,000,000)$/,
                    "nname":"val_sarmaye_paye_fot_dar_asar_hadese_multiple_pers1",
                    "alertText":"* حداقل ميزان سرمايه فوت در اثر حادثه برابر 30 ميليون ريال و حداكثر 2 ميليارد ريال و کمتر از 3 برابر سرمایه پایه فوت مي باشد"
                },
                "sarmaye_paye_fot_dar_asar_hadese_multiple_pers2":{
//                    "regex":/^([3-9]\d,\d\d\d,\d\d\d|[1-9]\d\d,\d\d\d,\d\d\d|1,000,000,000)$/,
                    "nname":"val_sarmaye_paye_fot_dar_asar_hadese_multiple_pers2",
                    "alertText":"* حداقل ميزان سرمايه فوت در اثر حادثه برابر 30 ميليون ريال و حداكثر 1 ميليارد ريال و کمتر از 3 برابر سرمایه پایه فوت مي باشد"
                },
                "sarmaye_paye_fot_dar_asar_hadese_multiple_pers3":{
//                    "regex":/^([3-9]\d,\d\d\d,\d\d\d|[1-9]\d\d,\d\d\d,\d\d\d|1,000,000,000)$/,
                    "nname":"val_sarmaye_paye_fot_dar_asar_hadese_multiple_pers3",
                    "alertText":"* حداقل ميزان سرمايه فوت در اثر حادثه برابر 30 ميليون ريال و حداكثر 1 ميليارد ريال و کمتر از 3 برابر سرمایه پایه فوت مي باشد"
                },
                "sarmaye_paye_fot_dar_asar_hadese_multiple_pers4":{
//                    "regex":/^([3-9]\d,\d\d\d,\d\d\d|[1-9]\d\d,\d\d\d,\d\d\d|1,000,000,000)$/,
                    "nname":"val_sarmaye_paye_fot_dar_asar_hadese_multiple_pers4",
                    "alertText":"* حداقل ميزان سرمايه فوت در اثر حادثه برابر 30 ميليون ريال و حداكثر 1 ميليارد ريال و کمتر از 3 برابر سرمایه پایه فوت مي باشد"
                },
                "sarmaye_paye_fot_dar_asar_hadese_ts":{
//                    "regex":/^([3-9]\d,\d\d\d,\d\d\d|[1-9]\d\d,\d\d\d,\d\d\d|1,000,000,000)$/,
                    "nname":"val_sarmaye_paye_fot_dar_asar_hadese_ts",
                    "alertText":"* حداقل ميزان سرمايه فوت در اثر حادثه برابر 30 ميليون ريال و حداكثر 2 ميليارد ريال و کمتر از 3 برابر سرمایه پایه فوت مي باشد"
                },
                "innersarmaye_paye_fot_dar_asar_hadese":{
//                    "regex":/^([3-9]\d,\d\d\d,\d\d\d|[1-9]\d\d,\d\d\d,\d\d\d|1,000,000,000)$/,
                    "nname":"val_innersarmaye_paye_fot_dar_asar_hadese",
                    "alertText":"* حداقل ميزان سرمايه فوت در اثر حادثه برابر 30 ميليون ريال و حداكثر 1 ميليارد ريال و کمتر از 3 برابر سرمایه پایه فوت مي باشد"
                },
                "val_sarmaye_pushesh_amraaz_khaas":{
                    "nname":"val_sarmaye_pushesh_amraaz_khaas",
                    "alertText":"حداكثر سرمايه پوشش امراض خاص معادل 30 درصد سرمايه فوت تا سقف 300 ميليون ريال مي باشد"
                },
                "val_sarmaye_pushesh_amraaz_khaas_multiple_pers1":{
                    "nname":"val_sarmaye_pushesh_amraaz_khaas_multiple_pers1",
                    "alertText":"حداكثر سرمايه پوشش امراض خاص معادل 30 درصد سرمايه فوت تا سقف 300 ميليون ريال مي باشد"
                },
                "val_sarmaye_pushesh_amraaz_khaas_multiple_pers2":{
                    "nname":"val_sarmaye_pushesh_amraaz_khaas_multiple_pers2",
                    "alertText":"حداكثر سرمايه پوشش امراض خاص معادل 30 درصد سرمايه فوت تا سقف 300 ميليون ريال مي باشد"
                },
                "val_sarmaye_pushesh_amraaz_khaas_multiple_pers3":{
                    "nname":"val_sarmaye_pushesh_amraaz_khaas_multiple_pers3",
                    "alertText":"حداكثر سرمايه پوشش امراض خاص معادل 30 درصد سرمايه فوت تا سقف 300 ميليون ريال مي باشد"
                },
                "val_sarmaye_pushesh_amraaz_khaas_multiple_pers4":{
                    "nname":"val_sarmaye_pushesh_amraaz_khaas_multiple_pers4",
                    "alertText":"حداكثر سرمايه پوشش امراض خاص معادل 30 درصد سرمايه فوت تا سقف 300 ميليون ريال مي باشد"
                },
                "val_sarmaye_pushesh_amraaz_khaas_ts":{
                    "nname":"val_sarmaye_pushesh_amraaz_khaas_ts",
                    "alertText":"حداكثر سرمايه پوشش امراض خاص معادل 30 درصد سرمايه فوت تا سقف 300 ميليون ريال مي باشد"
                },
                "val_seneBimeShode_multiple_pers1":{
                    "nname":"val_seneBimeShode_multiple_pers1",
                    "alertText":"سن بيمه شده اول نبايد زير 18 سال باشد."
                },
                "val_seneBimeShode_multiple_pers2":{
                    "nname":"val_seneBimeShode_multiple_pers2",
                    "alertText":"سن بيمه شده دوم بايد بالاي 18 سال باشد."
                },
                "val_seneBimeShode_multiple_pers3":{
                    "nname":"val_empty",
                    "alertText":""
                },
                "val_seneBimeShode_multiple_pers4":{
                    "nname":"val_empty",
                    "alertText":""
                },
                "time":{
                    "regex":/^(\d\d:\d\d)*$/,
                    "alertText":"فرمت ساعت صحیح نمی باشد"
                },
                "length":{
                    "regex":"none",
                    "alertText":"*بين ",
                    "alertText2":" و ",
                    "alertText3":"رقم مجاز است"},
                "maxCheckbox":{
                    "regex":"none",
                    "alertText":"* Checks allowed Exceeded"},
                "minCheckbox":{
                    "regex":"none",
                    "alertText":"* Please select ",
                    "alertText2":" options"},
                "equals":{
                    "regex":"none",
                    "alertText":"* Fields do not match"},
                "phone":{
                    // credit: jquery.h5validate.js / orefalo
                    "regex":/^([\+][0-9]{1,3}[ \.\-])?([\(]{1}[0-9]{2,6}[\)])?([0-9 \.\-\/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$/,
                    "alertText":"* Invalid phone number"},
                "email":{
                    // Shamelessly lifted from Scott Gonzalez via the Bassistance Validation plugin http://projects.scottsplayground.com/email_address_validation/
//                    "regex":/^(((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)*$/,
                    "regex": /^((([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,})))*$/
                    ,
                    "alertText":"* آدرس ایمیل نامعتبر است"},
                "integer":{
                    "regex":/^[\+]?\d+$/,
                    "alertText":"* این عدد صحیح نمی باشد"},
//                "integer":{ old
//                    "regex": /^[\-\+]?\d+$/,
//                    "alertText":"* این عدد صحیح نمی باشد"},
                "integerOrNull":{
                    "regex":/^[\-\+]?\d*$/,
                    "alertText":"* این عدد صحیح نمی باشد"
                },
                "noZero":{
                    "regex":/^[1-9]\d*$/,
                    "alertText":"* این فیلد نمی تواند 0 باشد"
                },
                "long":{
                    "regex":/^[\+]?\d?\d?\d(,\d\d\d)*$/,
                    "alertText":"* این عدد صحیح نمی باشد"},
                "number":{
                    // Number, including positive, negative, and floating decimal. Credit: bassistance
                    "regex":/^[\-\+]?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)$/,
                    "alertText":"* این فیلد باید عدد صحیح یا اعشاری معتبر باشد"},
                "date":{
                    // Date in ISO format. Credit: bassistance
//                    "regex":/^\d{4}[\/\-]\d{1,2}[\/\-]\d{1,2}$/, FireFox
//                    "regex":/^((\d{1,2}[\/\-]\d{1,2}[\/\-]\d{4})|(\d{4}[\/\-]\d{1,2}[\/\-]\d{1,2}))*$/,   vase tarikhe 2 tarafe
//                    "regex":/^(\d{4}[\/]\d{2}[\/]\d{2})*$/,
//                    "regex":/^(\d{4})?([/])?(0[1-9]|1[012])?\2(0[1-9]|[12][0-9]|3[01])?$/,
                    "regex":/^([1-4]\d{3}\/((0[1-6]\/((3[0-1])|([1-2][0-9])|(0[1-9])))|((1[0-2]|(0[7-9]))\/(30|([1-2][0-9])|(0[1-9])))))*$/,
                    "alertText":"* فرمت تاریخ وارد شده صحیح نیست"},
                "shomare_bimename":
                {
//                    "regex":/^(\d{4}[\/]\d{6}[\/]\d{2}[\/]\d{6})*$/,
                    "regex":/^((?:(?:2440|2210)|2430)[\/]\d{6}[\/]\d{2}[\/]\d{6})*$/,
                    "alertText": "فرمت بیمه نامه وارد شده اشتباه است. "
                },
                "ipv4":{
                    "regex":/^([1-9][0-9]{0,2})+\.([1-9][0-9]{0,2})+\.([1-9][0-9]{0,2})+\.([1-9][0-9]{0,2})+$/,
                    "alertText":"* Invalid IP address"},
                "url":{
                    "regex":/^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/,
                    "alertText":"* Invalid URL"},
                "onlyNumber":{
                    "regex":/^[0-9]*$/,
                    "alertText":"* لطفا فقط عدد وارد کنید"},
                "noSpecialCaracters":{
                    "regex":/^[0-9a-zA-Z]*$/,
                    "alertText":"* کاراکترهای خاص در این فیلد نباید باشند"},
                "ajaxUser":{
                    "file":"validateUser.php",
                    "extraData":"name=eric",
                    "alertTextOk":"* This user is available",
                    "alertTextLoad":"* Loading, please wait",
                    "alertText":"* This user is already taken"},
                "ajaxName":{
                    "file":"validateUser.php",
                    "alertText":"* This name is already taken",
                    "alertTextOk":"* This name is available",
                    "alertTextLoad":"* Loading, please wait"},
                "onlyLetter":{
                    "regex":/^[a-zA-Z\ \']*$/,
                    "alertText":"* در این فیلد فقط حروف باید وارد شوند"},
                "onlyPersianLetter":{
                    "regex":/^[ا-یآ-ِء\() ]*$/,
                    "alertText":"* در این فیلد فقط حروف فارسی باید وارد شوند"
                },
                "address":{
                    "regex":/^([0-9ا-یآ-ِء\ \-\./\(\)a-z]|[-][\.])*$/,
                    "alertText":"* آدرس اشتباه است"
                },
                "shenaasnaame":{
//                    "regex":/^\d{1,11}$/,
//                    "alertText":"*شماره شناسنامه حداکثر 11 رقم می باشد"
                    "regex": /^[0-9]*$/,
                    "alertText": "* لطفا فقط عدد وارد کنید"
                },
                "code_posti":{
                    "regex":/^(\d{10})*$/,
                    "alertText":"*کد پستی 10 رقم می باشد"
                },
                "max_Size":{
                    "regex":/^(\d{6})*$/,
                    "alertText":"سري شش رقمي است"
                },
                "code_shahri":{
                    "regex":/^0\d+$/,
                    "alertText":"* کد شهری باید با صفر شروع شده و با رقم های دیگر ادامه یابد"
                },
                "telephone_hamraah":{
                    "regex":/^09\d{9}$/,
                    "alertText":"* تلفن همراه 11 رقم می باشد و باید با 09 شروع شود"
                },
                "code_melli":{
                    "nname":"val_codeMelli",
                    "alertText":"* کد ملی معتبر نیست"
                },
                "code_melli_shenasayi":{
                    nname:"val_codeMelliShenasayi",
                    "alertText":"* کد ملی معتبر نیست"
                },
                "modat_bime_naame":{
                    "regex":/^([1-3][0-9]|[5-9])$/,
                    "alertText":"مدت بیمه نامه بین 5 تا 70 سال می باشد"
                },
                "modat_bime_naame_30":{
                    "regex":/^([1-6]\d|[0-9]|70)$/,
                    "alertText":"مدت بیمه نامه حداکثر 70 سال می باشد"
                },
                "modat_bime_naame_5":{
                    "regex":/^([5-9]|\d\d)$/,
                    "alertText":"مدت بیمه نامه حداقل 5 سال می باشد"
                },
                "shaba": {
                    "nname": "validateShaba",
                    "alertText": "شماره شبا اشتباه است"
                },
                "validate2fields":{
                    "nname":"validate2fields",
                    "alertText":"* You must have a firstname and a lastname"},
                "val_nerkh_afzayesh_salaneh_sarmaye_fot":{
                    "nname":"val_nerkh_afzayesh_salaneh_sarmaye_fot",
                    "alertText":"* درصد انتخاب شده با توجه به سن غیر مجاز است."
                },
                "val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple_pers1":{
                    "nname":"val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple_pers1",
                    "alertText":"* درصد انتخاب شده با توجه به سن غیر مجاز است."
                },
                "val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple_pers2":{
                    "nname":"val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple_pers2",
                    "alertText":"* درصد انتخاب شده با توجه به سن غیر مجاز است."
                },
                "val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple_pers3":{
                    "nname":"val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple_pers3",
                    "alertText":"* درصد انتخاب شده با توجه به سن غیر مجاز است."
                },
                "val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple_pers4":{
                    "nname":"val_nerkh_afzayesh_salaneh_sarmaye_fot_multiple_pers4",
                    "alertText":"* درصد انتخاب شده با توجه به سن غیر مجاز است."
                },
                "val_nerkh_afzayesh_salaneh_sarmaye_fot_ts":{
                    "nname":"val_nerkh_afzayesh_salaneh_sarmaye_fot_ts",
                    "alertText":"* درصد انتخاب شده با توجه به سن غیر مجاز است."
                },
                "val_3_9_sarmaye_paye_fot":{
                    "nname":"val_3_9_sarmaye_paye_fot",
                    "alertText": "* در صورتیکه سن بیمه شده بین 3 تا 9 سال باشد، این سرمایه باید بین 30 میلیون و 100 میلیون ریال باشد"
                },
                "val_3_9_sarmaye_paye_fot_multiple_pers1":{
                    "nname":"val_3_9_sarmaye_paye_fot_multiple_pers1",
                    "alertText": "* در صورتیکه سن بیمه شده بین 3 تا 9 سال باشد، این سرمایه باید بین 30 میلیون و 100 میلیون ریال باشد"
                },
                "val_3_9_sarmaye_paye_fot_multiple_pers2":{
                    "nname":"val_3_9_sarmaye_paye_fot_multiple_pers2",
                    "alertText": "* در صورتیکه سن بیمه شده بین 3 تا 9 سال باشد، این سرمایه باید بین 30 میلیون و 100 میلیون ریال باشد"
                },
                "val_3_9_sarmaye_paye_fot_multiple_pers3":{
                    "nname":"val_3_9_sarmaye_paye_fot_multiple_pers3",
                    "alertText": "* در صورتیکه سن بیمه شده بین 3 تا 9 سال باشد، این سرمایه باید بین 30 میلیون و 100 میلیون ریال باشد"
                },
                "val_3_9_sarmaye_paye_fot_multiple_pers4":{
                    "nname":"val_3_9_sarmaye_paye_fot_multiple_pers4",
                    "alertText": "* در صورتیکه سن بیمه شده بین 3 تا 9 سال باشد، این سرمایه باید بین 30 میلیون و 100 میلیون ریال باشد"
                },
                "val_3_9_sarmaye_paye_fot_ts":{
                    "nname":"val_3_9_sarmaye_paye_fot_ts",
                    "alertText": "* در صورتیکه سن بیمه شده بین 3 تا 9 سال باشد، این سرمایه باید بین 30 میلیون و 100 میلیون ریال باشد"
                },


                "val_mahdodeye_sarmaye_fot":{
                    "nname":"val_mahdodeye_sarmaye_fot",
                    "alertText":"سن بیمه شده با سرمایه وارد شده هم خوانی ندارد"
                },

                "val_0_2_sarmaye_paye_fot":{
                    "nname":"val_0_2_sarmaye_paye_fot",
                    "alertText": "* در صورتیکه سن بیمه شده بین صفر تا 2 سال باشد، این سرمایه باید بین 10 میلیون و 50 میلیون ریال باشد"
                },
                "val_0_2_sarmaye_paye_fot_ts":{
                    "nname":"val_0_2_sarmaye_paye_fot_ts",
                    "alertText": "* در صورتیکه سن بیمه شده بین صفر تا 2 سال باشد، این سرمایه باید بین 10 میلیون و 50 میلیون ریال باشد"
                },
                "val_0_2_sarmaye_paye_fot_multiple_pers1":{
                    "nname":"val_0_2_sarmaye_paye_fot_multiple_pers1",
                    "alertText": "* در صورتیکه سن بیمه شده بین صفر تا 2 سال باشد، این سرمایه باید بین 10 میلیون و 50 میلیون ریال باشد"
                },
                "val_0_2_sarmaye_paye_fot_multiple_pers2":{
                    "nname":"val_0_2_sarmaye_paye_fot_multiple_pers2",
                    "alertText": "* در صورتیکه سن بیمه شده بین صفر تا 2 سال باشد، این سرمایه باید بین 10 میلیون و 50 میلیون ریال باشد"
                },
                "val_0_2_sarmaye_paye_fot_multiple_pers3":{
                    "nname":"val_0_2_sarmaye_paye_fot_multiple_pers3",
                    "alertText": "* در صورتیکه سن بیمه شده بین صفر تا 2 سال باشد، این سرمایه باید بین 10 میلیون و 50 میلیون ریال باشد"
                },
                "val_0_2_sarmaye_paye_fot_multiple_pers4":{
                    "nname":"val_0_2_sarmaye_paye_fot_multiple_pers4",
                    "alertText": "* در صورتیکه سن بیمه شده بین صفر تا 2 سال باشد، این سرمایه باید بین 10 میلیون و 50 میلیون ریال باشد"
                },
                "val_10_14_sarmaye_paye_fot":{
                    "nname":"val_10_14_sarmaye_paye_fot",
                    "alertText": "* در صورتیکه سن بیمه شده بین 10 تا 14 سال باشد، این سرمایه باید بین 30 میلیون و 150 میلیون ریال باشد"
                },
                "val_10_14_sarmaye_paye_fot_multiple_pers1":{
                    "nname":"val_10_14_sarmaye_paye_fot_multiple_pers1",
                    "alertText": "* در صورتیکه سن بیمه شده بین 10 تا 14 سال باشد، این سرمایه باید بین 30 میلیون و 150 میلیون ریال باشد"
                },
                "val_10_14_sarmaye_paye_fot_multiple_pers2":{
                    "nname":"val_10_14_sarmaye_paye_fot_multiple_pers2",
                    "alertText": "* در صورتیکه سن بیمه شده بین 10 تا 14 سال باشد، این سرمایه باید بین 30 میلیون و 150 میلیون ریال باشد"
                },
                "val_10_14_sarmaye_paye_fot_multiple_pers3":{
                    "nname":"val_10_14_sarmaye_paye_fot_multiple_pers3",
                    "alertText": "* در صورتیکه سن بیمه شده بین 10 تا 14 سال باشد، این سرمایه باید بین 30 میلیون و 150 میلیون ریال باشد"
                },
                "val_10_14_sarmaye_paye_fot_multiple_pers4":{
                    "nname":"val_10_14_sarmaye_paye_fot_multiple_pers4",
                    "alertText": "* در صورتیکه سن بیمه شده بین 10 تا 14 سال باشد، این سرمایه باید بین 30 میلیون و 150 میلیون ریال باشد"
                },
                "val_10_14_sarmaye_paye_fot_ts":{
                    "nname":"val_10_14_sarmaye_paye_fot_ts",
                    "alertText": "* در صورتیکه سن بیمه شده بین 10 تا 14 سال باشد، این سرمایه باید بین 30 میلیون و 150 میلیون ریال باشد"
                },
                "val_15_19_sarmaye_paye_fot":{
                    "nname":"val_15_19_sarmaye_paye_fot",
                    "alertText": "* در صورتیکه سن بیمه شده بین 15 تا 19 سال باشد، این سرمایه باید بین 30 میلیون ریال و 400 میلیون ریال باشد"
                },
                "val_15_19_sarmaye_paye_fot_multiple_pers1":{
                    "nname":"val_15_19_sarmaye_paye_fot_multiple_pers1",
                    "alertText": "* در صورتیکه سن بیمه شده بین 15 تا 19 سال باشد، این سرمایه باید بین 30 میلیون ریال و 400 میلیون ریال باشد"
                },
                "val_15_19_sarmaye_paye_fot_multiple_pers2":{
                    "nname":"val_15_19_sarmaye_paye_fot_multiple_pers2",
                    "alertText": "* در صورتیکه سن بیمه شده بین 15 تا 19 سال باشد، این سرمایه باید بین 30 میلیون ریال و 400 میلیون ریال باشد"
                },
                "val_15_19_sarmaye_paye_fot_multiple_pers3":{
                    "nname":"val_15_19_sarmaye_paye_fot_multiple_pers3",
                    "alertText": "* در صورتیکه سن بیمه شده بین 15 تا 19 سال باشد، این سرمایه باید بین 30 میلیون ریال و 400 میلیون ریال باشد"
                },
                "val_15_19_sarmaye_paye_fot_multiple_pers4":{
                    "nname":"val_15_19_sarmaye_paye_fot_multiple_pers4",
                    "alertText": "* در صورتیکه سن بیمه شده بین 15 تا 19 سال باشد، این سرمایه باید بین 30 میلیون ریال و 400 میلیون ریال باشد"
                },
                "val_15_19_sarmaye_paye_fot_ts":{
                    "nname":"val_15_19_sarmaye_paye_fot_ts",
                    "alertText": "* در صورتیکه سن بیمه شده بین 15 تا 19 سال باشد، این سرمایه باید بین 30 میلیون ریال و 400 میلیون ریال باشد"
                },
                "val_20_sayer_sarmaye_paye_fot":{
                    "nname":"val_20_sayer_sarmaye_paye_fot",
                    "alertText": "* در صورتیکه سن بیمه شده بالای 20 سال باشد، این سرمایه باید بین 10 میلیون ریال و 2 میلیارد ریال باشد"
                },
                "val_20_sayer_sarmaye_paye_fot_multiple_pers1":{
                    "nname":"val_20_sayer_sarmaye_paye_fot_multiple_pers1",
                    "alertText": "* در صورتیکه سن بیمه شده بالای 20 سال باشد، این سرمایه باید بین 10 میلیون ریال و 2 میلیارد ریال باشد"
                },
                "val_20_sayer_sarmaye_paye_fot_multiple_pers2":{
                    "nname":"val_20_sayer_sarmaye_paye_fot_multiple_pers2",
                    "alertText": "* در صورتیکه سن بیمه شده بالای 20 سال باشد، این سرمایه باید بین 10 میلیون ریال و 2 میلیارد ریال باشد"
                },
                "val_20_sayer_sarmaye_paye_fot_multiple_pers3":{
                    "nname":"val_20_sayer_sarmaye_paye_fot_multiple_pers3",
                    "alertText": "* در صورتیکه سن بیمه شده بالای 20 سال باشد، این سرمایه باید بین 10 میلیون ریال و 2 میلیارد ریال باشد"
                },
                "val_20_sayer_sarmaye_paye_fot_multiple_pers4":{
                    "nname":"val_20_sayer_sarmaye_paye_fot_multiple_pers4",
                    "alertText": "* در صورتیکه سن بیمه شده بالای 20 سال باشد، این سرمایه باید بین 10 میلیون ریال و 2 میلیارد ریال باشد"
                },
                "val_20_sayer_sarmaye_paye_fot_ts":{
                    "nname":"val_20_sayer_sarmaye_paye_fot_ts",
                    "alertText": "* در صورتیکه سن بیمه شده بالای 20 سال باشد، این سرمایه باید بین 10 میلیون ریال و 2 میلیارد ریال باشد"
                },

                "val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot":{
                    "nname":"val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot",
                    "alertText":"حداكثر سرمايه پوشش نقص عضو معادل مينيمم سرمايه فوت و سرمايه فوت در اثر حادثه تا سقف 500 ميليون ريال مي باشد"
                },
                "val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple_pers1":{
                    "nname":"val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple_pers1",
                    "alertText":"حداكثر سرمايه پوشش نقص عضو معادل مينيمم سرمايه فوت و سرمايه فوت در اثر حادثه تا سقف 500 ميليون ريال مي باشد"
                },
                "val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple_pers2":{
                    "nname":"val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple_pers2",
                    "alertText":"حداكثر سرمايه پوشش نقص عضو معادل مينيمم سرمايه فوت و سرمايه فوت در اثر حادثه تا سقف 500 ميليون ريال مي باشد"
                },
                "val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple_pers3":{
                    "nname":"val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple_pers3",
                    "alertText":"حداكثر سرمايه پوشش نقص عضو معادل مينيمم سرمايه فوت و سرمايه فوت در اثر حادثه تا سقف 500 ميليون ريال مي باشد"
                },
                "val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple_pers4":{
                    "nname":"val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_multiple_pers4",
                    "alertText":"حداكثر سرمايه پوشش نقص عضو معادل مينيمم سرمايه فوت و سرمايه فوت در اثر حادثه تا سقف 500 ميليون ريال مي باشد"
                },
                "val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_ts":{
                    "nname":"val_sarmaye_pooshesh_naghs_ozvIsLowerOrEqualToSarmaye_paye_fot_ts",
                    "alertText":"حداكثر سرمايه پوشش نقص عضو معادل مينيمم سرمايه فوت و سرمايه فوت در اثر حادثه تا سقف 500 ميليون ريال مي باشد"
                },
                "val_mahane_hagh_bime_pardakhti":{
                    "nname":"val_hagh_bime_pardakhti1",
                    "alertText":"مبلغ حق بیمه پرداختی نامعتبر است."
                },
                "val_3mahe_hagh_bime_pardakhti":{
                    "nname":"val_hagh_bime_pardakhti2",
                    "alertText":"* حداقل حق بیمه سه ماهه، باید برابر 750 هزار ریال باشد"
                },
                "val_6mahe_hagh_bime_pardakhti":{
                    "nname":"val_hagh_bime_pardakhti3",
                    "alertText":"* حداقل حق بیمه شش ماهه، باید برابر یک میلیون و دویست هزار ریال باشد"
                },
                "val_salane_hagh_bime_pardakhti":{
                    "nname":"val_hagh_bime_pardakhti4",
                    "alertText":"* حداقل حق بیمه سالانه، باید برابر 2 میلیون ریال باشد"
                },


                "val_mahane_hagh_bime_pardakhti_ts":{
                    "nname":"val_hagh_bime_pardakhti10",
                    "alertText": "مبلغ حق بیمه پرداختی نامعتبر است."
                },
                "val_3mahe_hagh_bime_pardakhti_ts":{
                    "nname":"val_hagh_bime_pardakhti20",
                    "alertText":"* حداقل حق بیمه سه ماهه، باید برابر 750 هزار ریال باشد"
                },
                "val_6mahe_hagh_bime_pardakhti_ts":{
                    "nname":"val_hagh_bime_pardakhti30",
                    "alertText":"* حداقل حق بیمه شش ماهه، باید برابر یک میلیون و دویست هزار ریال باشد"
                },
                "val_salane_hagh_bime_pardakhti_ts":{
                    "nname":"val_hagh_bime_pardakhti40",
                    "alertText":"* حداقل حق بیمه سالانه، باید برابر 2 میلیون ریال باشد"
                },
                "val_darsad_hagh_bime_va_darsad_sarmaye_fot_ts":{
                    "nname":"val_darsad_hagh_bime_darsad_sarmaye_fot_ts",
                    "alertText":"درصد افزايش سرمايه فوت بايد كوچكتر مساوي درصد افزايش حق بيمه باشد"
                },

                "val_darsad_hagh_bime_va_darsad_sarmaye_fot":{
                    "nname":"val_darsad_hagh_bime_darsad_sarmaye_fot",
                    "alertText":"درصد افزايش سرمايه فوت بايد كوچكتر مساوي درصد افزايش حق بيمه باشد"
                },
                "val_darsad_hagh_bime_va_darsad_sarmaye_fot_multiple_all":{
                    "nname":"val_darsad_hagh_bime_darsad_sarmaye_fot_multiple_all",
                    "alertText":"درصد افزايش سرمايه فوت بايد كوچكتر مساوي درصد افزايش حق بيمه باشد"
                },
                "val_darsad_hagh_bime_va_darsad_sarmaye_fot_multiple_pers1":{
                    "nname":"val_darsad_hagh_bime_darsad_sarmaye_fot_multiple_pers1",
                    "alertText":"درصد افزايش سرمايه فوت بايد كوچكتر مساوي درصد افزايش حق بيمه باشد"
                },
                "val_darsad_hagh_bime_va_darsad_sarmaye_fot_multiple_pers2":{
                    "nname":"val_darsad_hagh_bime_darsad_sarmaye_fot_multiple_pers2",
                    "alertText":"درصد افزايش سرمايه فوت بايد كوچكتر مساوي درصد افزايش حق بيمه باشد"
                },"val_darsad_hagh_bime_va_darsad_sarmaye_fot_multiple_pers3":{
                    "nname":"val_darsad_hagh_bime_darsad_sarmaye_fot_multiple_pers3",
                    "alertText":"درصد افزايش سرمايه فوت بايد كوچكتر مساوي درصد افزايش حق بيمه باشد"
                },"val_darsad_hagh_bime_va_darsad_sarmaye_fot_multiple_pers4":{
                    "nname":"val_darsad_hagh_bime_darsad_sarmaye_fot_multiple_pers4",
                    "alertText":"درصد افزايش سرمايه فوت بايد كوچكتر مساوي درصد افزايش حق بيمه باشد"
                },
                "val_darsadMojazAfzayeshSarmayeFot":{
                    "nname":"val_darsadMojazAfzayeshSarmayeFot",
                    "alertText": "در صد وارد شده برای افزایش سرمایه فوت معتبر نیست"
                },
                "majmoeSenVaModdateBimeName":{
                    "nname":"val_majmoeSenVaModdateBimeName",
                    "alertText":"مجموع سن بيمه شده و مدت بيمه نامه نامعتبر است"
                },
                "majmoeSenVaModdateBimeName_multiple":{
                    "nname":"val_majmoeSenVaModdateBimeName_multiple",
                    "alertText":"مجموع سن بيمه شده و مدت بيمه نامه بايستي كمتر مساوي با 70 سال باشد"
                },
                "majmoeSenVaModdateBimeName_ts":{
                    "nname":"val_majmoeSenVaModdateBimeName_ts",
                    "alertText":"مجموع سن بيمه شده و مدت بيمه نامه نامعتبر است"
                },
// Pishnehad Section
                "val_1_bimeGozar_nesbatBaBimeShode":{
                    "nname":"val_1_bimeGozar_nesbatBaBimeShode",
                    "alertText":"* با توجه به اطلاعات وارد شده، نسبت صحیح نمی باشد"
                },
                "val_EKASB_nesbatBabimeShode":{
                    "nname":"val_EKASB_nesbatBabimeShode",
                    "alertText":"* با توجه به اطلاعات وارد شده، نسبت صحیح نمی باشد"
                },
                "zinfeTekrari":{
                    "nname":"val_zinfeTekrari",
                    "alertText":"* چنین استفاده کننده ای در حال حاضر وجود دارد"
                },
                "dastmozd-nonzero":{
                    "nname":"val_dastmozd_nonzero",
                    "alertText":"* درآمد ماهیانه نباید صفر باشد"
                },
                "val_kodeMelliBimeShode":{
                    "nname":"val_kodeMelliBimeShode",
                    "alertText":"* کد ملی معتبر نیست"
                },
                "val_kodeMelliBimeGozar":{
                    "nname":"val_kodeMelliBimeGozar",
                    "alertText":"* کد ملی معتبر نیست"
                },
                "val_aghsateMoavaghOneDatesRequired":{
                    "nname":"val_aghsateMoavaghOneDatesRequired",
                    "alertText":"يكي از جفت فيلدهاي تاريخ صدور، تاريخ پرداخت يا تاريخ سررسيد اجباري است."
                }
            };

        }
    };
})(jQuery);

$(document).ready(function () {
    valid = true;
    $.validationEngineLanguage.newLang();
});