<html>

<body>

<h3>Training Portal</h3>

<!-- Read The Favourite Programming Language Cookie -->

<%

	// The Default -- If there are no cookies
	String favLang = "Java";
	
	// Get The cookies from the browser request
	Cookie[] theCookies = request.getCookies();
	
	// Find our Favourite Language Cookie
	if(theCookies != null) {
		for(Cookie tempCookie : theCookies) {
			
			if("myJsp.favLang".equals(tempCookie.getName())) {
				favLang = tempCookie.getValue();
				break;
			}
		}
	}

%>

<!-- Now Show A Personalized Page ... Use The FavLang Variable -->

<!-- Show New Books For This Lang -->

<h4> New Books for <%= favLang %> </h4>
<ul>
	<li>Blah Blah</li>
	<li>Blah Blah</li>
</ul>

<h4> New Jobs for <%= favLang %> </h4>
<ul>
	<li>Blah Blah</li>
	<li>Blah Blah</li>
</ul>

<h4> New Hot Jobs for <%= favLang %> </h4>
<ul>
	<li>Blah Blah</li>
	<li>Blah Blah</li>
</ul>

<hr>
<a href="cookiesForm.html">Personalize This Page</a>

</body>

</html>