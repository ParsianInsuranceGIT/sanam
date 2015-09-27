<%--
  Created by IntelliJ IDEA.
  User: Arron1
  Date: 8/17/11
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt-rt" %>

noeBedehieDarHaleSabt=${credebit.credebitType}
#
shomareCheck2=${credebit.check.id}
#
amountTajamoi=${credebit.amount}
#
namayandeName=${credebit.namayande.name}
@
girande=${credebit.pardakhteTankhah.girande}
#
amount=${credebit.amount}
#
tarikh=${credebit.createdDate}
#
tozihat=${credebit.pardakhteTankhah.tozih}
@
hesabFimabeynAmount=${credebit.amount}
#
hesabFimabeynTozihate=${credebit.description}