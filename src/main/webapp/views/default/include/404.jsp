<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/includes/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>404页面未找到</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	</head>
	<body>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="error-container">
									<div class="well">
										<h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="icon-sitemap"></i>
												404
											</span>
											Page Not Found
										</h1>

										<hr />
										<h3 class="lighter smaller">We looked everywhere but we couldn't find it!</h3>

										<div>
											<form class="form-search">
												<span class="input-icon align-middle">
													<i class="icon-search"></i>

													<input type="text" class="search-query" placeholder="Give it a search..." />
												</span>
												<button class="btn btn-sm" onclick="return false;">Go!</button>
											</form>

											<div class="space"></div>
											<h4 class="smaller">Try one of the following:</h4>

											<ul class="list-unstyled spaced inline bigger-110 margin-15">
												<li>
													<i class="icon-hand-right blue"></i>
													Re-check the url for typos
												</li>

												<li>
													<i class="icon-hand-right blue"></i>
													Read the faq
												</li>

												<li>
													<i class="icon-hand-right blue"></i>
													Tell us about it
												</li>
											</ul>
										</div>

										<hr />
										<div class="space"></div>

										<div class="center">
											<a href="#" class="btn btn-grey">
												<i class="icon-arrow-left"></i>
												Go Back
											</a>

											<a href="${ctx}/index.html" class="btn btn-primary">
												<i class="icon-dashboard"></i>
												Dashboard
											</a>
										</div>
									</div>
								</div><!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
		<%@include file="/views/default/include/script-import.jsp"%>
		<!-- inline scripts related to this page -->
</body>
</html>