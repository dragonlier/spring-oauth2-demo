# Generating oauth2.0 access token.
# 如果是在浏览器的话，直接输入地址：http://localhost:8081

# 0. login

#  0.1 Send request to UI Server
curl -v http://localhost:8081/login

#  0.2 Receive response from UI Server
	*   Trying 127.0.0.1...
	* Connected to localhost (127.0.0.1) port 8081 (#0)
	> GET /login HTTP/1.1
	> Host: localhost:8081
	> User-Agent: curl/7.47.0
	> Accept: */*
	> 
	< HTTP/1.1 302 
	< X-Content-Type-Options: nosniff
	< X-XSS-Protection: 1; mode=block
	< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	< Pragma: no-cache
	< Expires: 0
	< X-Frame-Options: DENY
	< Set-Cookie: JSESSIONID=DF8AE3A0DC464C300A89E1CF3A4168FF;path=/;HttpOnly
	< Set-Cookie: XSRF-TOKEN=f808d65e-8866-478d-ac95-2d23de2c7721;path=/
	< Location: http://localhost:8084/demo-auth/oauth/authorize?client_id=acme&redirect_uri=http://localhost:8081/login&response_type=code&state=eFD9U4
	< Content-Length: 0
	< Date: Tue, 11 Apr 2017 03:08:31 GMT
	< 
	* Connection #0 to host localhost left intact

# 1. authorize

#  1.1 Send request to Authentication and Authorization Server
curl -v "lhj:123456@localhost:8084/demo-auth/oauth/authorize?client_id=acme&redirect_uri=http://localhost:8081/login&response_type=code&state=eFD9U4" \
  -b "JSESSIONID=DF8AE3A0DC464C300A89E1CF3A4168FF;XSRF-TOKEN=f808d65e-8866-478d-ac95-2d23de2c7721"

#  1.2 Receive response from Server
	*   Trying 127.0.0.1...
	* Connected to localhost (127.0.0.1) port 8084 (#0)
	* Server auth using Basic with user 'lhj'
	> GET /demo-auth/oauth/authorize?client_id=acme&redirect_uri=http://localhost:8081/login&response_type=code&state=eFD9U4 HTTP/1.1
	> Host: localhost:8084
	> Authorization: Basic bGhqOjEyMzQ1Ng==
	> User-Agent: curl/7.47.0
	> Accept: */*
	> Cookie: JSESSIONID=DF8AE3A0DC464C300A89E1CF3A4168FF;XSRF-TOKEN=f808d65e-8866-478d-ac95-2d23de2c7721
	> 
	< HTTP/1.1 302 
	< X-Content-Type-Options: nosniff
	< X-XSS-Protection: 1; mode=block
	< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	< Pragma: no-cache
	< Expires: 0
	< X-Frame-Options: DENY
	< Strict-Transport-Security: max-age=31536000 ; includeSubDomains
	< X-Application-Context: auth-server:8084
	< Location: http://localhost:8081/login?code=t2xGHG&state=eFD9U4
	< Content-Language: en-US
	< Content-Length: 0
	< Date: Tue, 11 Apr 2017 03:13:49 GMT
	< 
	* Connection #0 to host localhost left intact

# 2. login again

#  2.1 Send request to UI Server
curl -v "http://localhost:8081/login?code=t2xGHG&state=eFD9U4" \
  -b "JSESSIONID=DF8AE3A0DC464C300A89E1CF3A4168FF;XSRF-TOKEN=f808d65e-8866-478d-ac95-2d23de2c7721"

#  2.2 Receive response from UI Server 
	*   Trying 127.0.0.1...
	* Connected to localhost (127.0.0.1) port 8081 (#0)
	> GET /login?code=t2xGHG&state=eFD9U4 HTTP/1.1
	> Host: localhost:8081
	> User-Agent: curl/7.47.0
	> Accept: */*
	> Cookie: JSESSIONID=DF8AE3A0DC464C300A89E1CF3A4168FF;XSRF-TOKEN=f808d65e-8866-478d-ac95-2d23de2c7721
	> 
	< HTTP/1.1 302 
	< X-Content-Type-Options: nosniff
	< X-XSS-Protection: 1; mode=block
	< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	< Pragma: no-cache
	< Expires: 0
	< X-Frame-Options: DENY
	< Set-Cookie: XSRF-TOKEN=f808d65e-8866-478d-ac95-2d23de2c7721;path=/
	< Set-Cookie: JSESSIONID=BD7217690D088FB419D8E8A0800F443C;path=/;HttpOnly
	< Location: http://localhost:8081/
	< Content-Length: 0
	< Date: Tue, 11 Apr 2017 03:27:05 GMT
	< 
	* Connection #0 to host localhost left intact

# 3. Access resource

#  3.1.1 Send request to UI Server
curl -v "http://localhost:8081/" \
  -b "XSRF-TOKEN=f808d65e-8866-478d-ac95-2d23de2c7721;JSESSIONID=BD7217690D088FB419D8E8A0800F443C"

#  3.1.2 Receive response from UI Server
	*   Trying 127.0.0.1...
	* Connected to localhost (127.0.0.1) port 8081 (#0)
	> GET / HTTP/1.1
	> Host: localhost:8081
	> User-Agent: curl/7.47.0
	> Accept: */*
	> Cookie: XSRF-TOKEN=f808d65e-8866-478d-ac95-2d23de2c7721;JSESSIONID=BD7217690D088FB419D8E8A0800F443C
	> 
	< HTTP/1.1 200 
	< X-Content-Type-Options: nosniff
	< X-XSS-Protection: 1; mode=block
	< Cache-Control: no-store
	< Pragma: 
	< Expires: 
	< X-Frame-Options: DENY
	< Set-Cookie: XSRF-TOKEN=102d4fef-b539-466a-b984-d8aee8b48ce6;path=/
	< X-Application-Context: ui-server:8081
	< Last-Modified: Mon, 10 Apr 2017 10:27:03 GMT
	< Accept-Ranges: bytes
	< Content-Type: text/html;charset=UTF-8
	< Content-Language: en-US
	< Content-Length: 1229
	< Date: Tue, 11 Apr 2017 03:29:41 GMT
	< 
	<!doctype html>
	* Connection #0 to host localhost left intact

#  3.2.1 Send request to UI Server
curl -v "http://localhost:8081/resource" \
  -b "XSRF-TOKEN=102d4fef-b539-466a-b984-d8aee8b48ce6;JSESSIONID=BD7217690D088FB419D8E8A0800F443C"

#  3.2.2 Receive response from UI Server
#  会话的权限认证，实质是通过JSESSIONID来确认的。
	*   Trying 127.0.0.1...
	* Connected to localhost (127.0.0.1) port 8081 (#0)
	> GET /resource HTTP/1.1
	> Host: localhost:8081
	> User-Agent: curl/7.47.0
	> Accept: */*
	> Cookie: XSRF-TOKEN=102d4fef-b539-466a-b984-d8aee8b48ce6;JSESSIONID=BD7217690D088FB419D8E8A0800F443C
	> 
	< HTTP/1.1 200 
	< X-Content-Type-Options: nosniff
	< X-XSS-Protection: 1; mode=block
	< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	< Pragma: no-cache
	< Expires: 0
	< X-Frame-Options: DENY
	< Set-Cookie: XSRF-TOKEN=102d4fef-b539-466a-b984-d8aee8b48ce6;path=/
	< X-Application-Context: ui-server:8081
	< Date: Tue, 11 Apr 2017 03:35:46 GMT
	< Content-Type: application/json;charset=UTF-8
	< Transfer-Encoding: chunked
	< 
	* Connection #0 to host localhost left intact
	{"id":"b25ff672-09bc-467c-ac3a-cf682bef469d","content":"Hello World"}

# 4. user

#  4.1 Send request to UI Server
curl -v "http://localhost:8081/user" 
  -b "JSESSIONID=BD7217690D088FB419D8E8A0800F443C"

#  4.2 Receive response from UI Server
	*   Trying 127.0.0.1...
	* Connected to localhost (127.0.0.1) port 8081 (#0)
	> GET /user HTTP/1.1
	> Host: localhost:8081
	> User-Agent: curl/7.47.0
	> Accept: */*
	> Cookie: JSESSIONID=BD7217690D088FB419D8E8A0800F443C
	> 
	< HTTP/1.1 200 
	< X-Content-Type-Options: nosniff
	< X-XSS-Protection: 1; mode=block
	< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	< Pragma: no-cache
	< Expires: 0
	< X-Frame-Options: DENY
	< Set-Cookie: XSRF-TOKEN=102d4fef-b539-466a-b984-d8aee8b48ce6;path=/
	< X-Application-Context: ui-server:8081
	< Date: Tue, 11 Apr 2017 03:47:14 GMT
	< Content-Type: application/json;charset=UTF-8
	< Transfer-Encoding: chunked
	< 
	{"authorities":[{"authority":"ROLE_ACTUATOR"},{"authority":"ROLE_USER"}],
	  "details":{
	    "remoteAddress":"127.0.0.1","sessionId":null,
	    "tokenValue":"a6da1010-c834-4aff-a496-4fed8c2e1d68",
	    "tokenType":"bearer","decodedDetails":null
	  },
	  "authenticated":true,
	  "userAuthentication":{
	    "authorities":[{"authority":"ROLE_ACTUATOR"},{"authority":"ROLE_USER"}],
	    "details":null,"authenticated":true,
	    "principal":{
	      "password":null,"username":"lhj",
	      "authorities":[{"authority":"ROLE_ACTUATOR"},{"authority":"ROLE_USER"}],
	      "accountNonExpired":true,"accountNonLocked":true,
	      "credentialsNonExpired":true,"enabled":true
	    },
	    "credentials":"","name":"lhj"
	  },
	  "oauth2Request":{
	    "clientId":"acme",
	    "scope":["openid","read","write"],
	    "requestParameters":{
	      "code":"3GuHmQ","grant_type":"authorization_code",
	      "response_type":"code","redirect_uri":"http://localhost:8081/login",
	      "state":"4X7EPY","client_id":"acme"
	    },
	    "resourceIds":[],
	    "authorities":[{"authority":"ROLE_USER"}],
	    "approved":true,"refresh":true,
	    "redirectUri":"http://localhost:8081/login",
	    "responseTypes":["code"],"extensions":{},"grantType":"authorization_code",
	    "refreshTokenRequest":{
	      "clientId":"acme","scope":[],
	      "requestParameters":{
	        "grant_type":"refresh_token",
	        "refresh_token":"495a4e19-ca1b-4d58-8d2a-826d99030993"
	      },
	      "grantType":"refresh_token"
	    }
	  },
	  "credentials":"",
	  "principal":{
	    "password":null,"username":"lhj",
	    "authorities":[{"authority":"ROLE_ACTUATOR"},{"authority":"ROLE_USER"}],
	    "accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true
	  },
	  "clientOnly":false,
	  "name":"lhj"
	}
	* Connection #0 to host localhost left intact

# 4. logout

#  4.1 Send request to UI Server
curl -v -X POST "http://localhost:8081/logout" \
  -b "XSRF-TOKEN=102d4fef-b539-466a-b984-d8aee8b48ce6;JSESSIONID=BD7217690D088FB419D8E8A0800F443C"
curl -v -X POST 'http://localhost:8081/logout' \
  -H 'X-Requested-With: XMLHttpRequest' \
  -H 'X-XSRF-TOKEN: 102d4fef-b539-466a-b984-d8aee8b48ce6' \
  -b 'XSRF-TOKEN=102d4fef-b539-466a-b984-d8aee8b48ce6;JSESSIONID=BD7217690D088FB419D8E8A0800F443C'

#  4.2 Receive response from UI Server
	*   Trying 127.0.0.1...
	* Connected to localhost (127.0.0.1) port 8081 (#0)
	> POST /logout HTTP/1.1
	> Host: localhost:8081
	> User-Agent: curl/7.47.0
	> Accept: */*
	> Cookie: XSRF-TOKEN=102d4fef-b539-466a-b984-d8aee8b48ce6;JSESSIONID=BD7217690D088FB419D8E8A0800F443C
	> X-Requested-With: XMLHttpRequest
	> X-XSRF-TOKEN: 102d4fef-b539-466a-b984-d8aee8b48ce6
	> 
	< HTTP/1.1 302 
	< X-Content-Type-Options: nosniff
	< X-XSS-Protection: 1; mode=block
	< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	< Pragma: no-cache
	< Expires: 0
	< X-Frame-Options: DENY
	< Set-Cookie: XSRF-TOKEN=102d4fef-b539-466a-b984-d8aee8b48ce6;path=/
	< Location: http://localhost:8081/
	< Content-Length: 0
	< Date: Tue, 11 Apr 2017 04:01:22 GMT
	< 
	* Connection #0 to host localhost left intact

# --2. token

#  --2.1 Send request to Authentication and Authorization Server
curl -v acme:acme123@localhost:8084/demo-auth/oauth/token \
  -d grant_type=authorization_code \
  -d client_id=acme \
  -d redirect_uri=http://localhost:8081/login \
  -d code=7MiBiK

#  --2.2 Receive response from Server
	*   Trying 127.0.0.1...
	* Connected to localhost (127.0.0.1) port 8084 (#0)
	* Server auth using Basic with user 'acme'
	> POST /demo-auth/oauth/token HTTP/1.1
	> Host: localhost:8084
	> Authorization: Basic YWNtZTphY21lMTIz
	> User-Agent: curl/7.47.0
	> Accept: */*
	> Content-Length: 97
	> Content-Type: application/x-www-form-urlencoded
	> 
	* upload completely sent off: 97 out of 97 bytes
	< HTTP/1.1 200 
	< X-Content-Type-Options: nosniff
	< X-XSS-Protection: 1; mode=block
	< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	< Pragma: no-cache
	< Expires: 0
	< X-Frame-Options: DENY
	< X-Application-Context: auth-server:8084
	< Content-Type: application/json;charset=UTF-8
	< Transfer-Encoding: chunked
	< Date: Mon, 10 Apr 2017 17:21:41 GMT
	< 
	* Connection #0 to host localhost left intact
	{"access_token":"fe56b3db-4968-42b0-a148-9a05b6196d4d","token_type":"bearer","refresh_token":"d84895fe-39a5-402f-8e90-fc02ca7df904","expires_in":29,"scope":"openid read write"}

# 3. resource
