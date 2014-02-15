<#import "/spring.ftl" as spring />

<#import "/layout/layoutLogin.ftl" as layout>

<@layout.layoutLogin>

<#if error = true>
	<div class="error">
		Caused : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
	</div>
</#if>


<div class="row">

	<div class="col-md-6 col-md-offset-3">
		<form name="login" action="<@spring.url '/login.do'/>" method="post" role="form">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="input-group input-group-lg">
						<label>Login</label>
						<input class="form-control" type="text" name="login" placeholder="myLogin"/>
						<label>Password</label>
						<input class="form-control" type="password" name="password" placeholder="myPassword"/>
					</div>
					<button class="btn btn-default" type="submit">Me connecter</input>
				</div>
			</div>	
		</form>	
	</div>
	
</div>
</@layout.layoutLogin>


