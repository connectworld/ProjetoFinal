<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Produto</title>
	 <link href="view/bootstrapHome/css/bootstrap.min.css" rel="stylesheet" media="screen" />
    <script type="text/javascript" src="view/js/jquery-2.1.4.js"></script>
    <script type="text/javascript" src="view/js/jquery.alphanumeric.js"></script>
	<script src="view/bootstrapHome/js/bootstrap.min.js"></script>
	 <!-- <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
	<script src="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>   -->

</head>
<body>
<!--####
### How to add in your boostrap project
1) Add jQuery "<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>"
2) Download fancybox (https://github.com/fancyapps/fancyBox)
3) Or use CDN (http://cdnjs.com/libraries/fancybox)
####--!>

<!-- References: https://github.com/fancyapps/fancyBox -->
 
<c:import url="/view/principal/menu.jsp"></c:import>
<div class="container">
	<div class="row">
	<c:forEach var="produto" items="${listaProduto}" varStatus="id">
		<div class='list-group gallery'>
            <div class='col-sm-4 col-xs-6 col-md-3 col-lg-3'>
                <a class="thumbnail fancybox" rel="ligthbox" href="#" style="text-decoration: none; font-family: arial; font-size: 15pt; font-weight: bold;">
                    <img class="img-responsive" alt="" src="view/img/${produto.imagem}" />
                    <br>
                    <!-- <div class='text-right'> -->
                        <!-- <small class='text-muted'>Image Title</small> -->
                        <p>
                        	Preço:
                        	${produto.precoVenda}
                        </p>
                        <p>
                        	${produto.descricao}
                        </p>
                    <!-- </div> --> <!-- text-right / end -->
                </a>
                
            </div> <!-- col-6 / end --> 
        </div> <!-- list-group / end -->
        </c:forEach>
	</div> <!-- row / end -->
</div> <!-- container / end -->
</body>
</html>