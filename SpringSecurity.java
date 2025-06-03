1. Authentication vs Authorization
Authentication = Who are you?
Authorization = What can you do?

🧠 Example:

Login with username/password → Authentication

Accessing admin dashboard → Authorization

✅ 2. JWT (JSON Web Token)
A compact, secure way to send user identity between systems after login.

🧠 Example:

After login, server sends a token like: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

You send this token in headers: Authorization: Bearer <token>

Server reads the token to know who you are and what roles you have.

💡 Use: Stateless authentication — no session stored on server.

✅ 3. OAuth 2.0
OAuth lets one app use another app’s login.

🧠 Example:

“Login with Google/Facebook”

You log in via Google. Google sends your details to the app — safely.

💡 Use: For delegation (e.g., app X wants access to your Gmail, but you keep your password private)

✅ 4. OpenID Connect (OIDC)
OIDC = OAuth 2.0 + user identity

🧠 Example:
OAuth gives access to Gmail.
OpenID gives user info like email, name, picture.

💡 Use: “Login with Google” where user identity is needed too.

✅ 5. SAML (Security Assertion Markup Language)
Old-school, XML-based login system used in big enterprises.

🧠 Example:

Used in Single Sign-On (SSO) in corporate networks.

You log into your company dashboard, then access all apps (HR, Email, CRM) without logging in again.

💡 Use: B2B, enterprise-level apps (often with ADFS, Okta, etc.)

✅ 6. HTTPS / SSL / TLS
🔒 Encrypts data between browser and server.
So nobody can see what you’re typing (like passwords).

🧠 Example:
When your browser shows https://example.com with a lock icon.

💡 Use: Always enable HTTPS in production.

✅ 7. Spring Security
Spring’s built-in security toolset.

🧠 Example:

java
Copy
Edit
http.authorizeRequests()
    .antMatchers("/admin").hasRole("ADMIN")
    .anyRequest().authenticated();
💡 Use: Controls access, handles login, sessions, password encoding, CSRF, etc.

✅ 8. Password Hashing (BCrypt)
Passwords must never be stored as plain text.

🧠 Example:
Instead of saving admin123, save hashed form:
$2a$10$7d.... ← BCrypt hashed password.

💡 Use: Always hash passwords before saving.

✅ 9. CORS (Cross-Origin Resource Sharing)
Controls which domains can call your API from the browser.

🧠 Example:

Frontend: http://myapp.com

Backend: http://api.myapp.com

If not allowed → browser blocks the request.

💡 Use:

java
Copy
Edit
@CrossOrigin(origins = "http://myapp.com")
✅ 10. CSRF (Cross-Site Request Forgery)
Protects users from unwanted actions while logged in.

🧠 Example:
You're logged into your bank. A malicious site tricks your browser into sending money.

💡 Spring Security auto-generates CSRF tokens for forms:

html
Copy
Edit
<input type="hidden" name="_csrf" value="abc123" />
✅ 11. Session Management
Session = storing user login data on the server.

🧠 Example:
After login, server creates a session ID and stores it in a cookie.

💡 Use: Traditional way before JWT. Still valid in many enterprise apps.

✅ 12. Security Headers
Tell browser how to behave safely.

🧠 Example:

X-Frame-Options: DENY → Prevent clickjacking.

Content-Security-Policy → Prevent script injections.

💡 Use: Set these via Spring Security or web server config.

✅ 13. Role-Based Access Control (RBAC)
Users have roles like ADMIN, USER. Each role has permissions.

🧠 Example:

java
Copy
Edit
@PreAuthorize("hasRole('ADMIN')")
💡 Use: Protect API endpoints by roles.

✅ 14. Method-Level Security
You can secure methods directly.

🧠 Example:

java
Copy
Edit
@Secured("ROLE_ADMIN")
public void deleteUser() { ... }
✅ 15. API Rate Limiting
Limit number of requests per user/IP to avoid abuse.

🧠 Example:
No more than 100 requests per minute per user.

💡 Use: Tools like Bucket4j, Redis, or API gateways.

✅ 16. XSS & SQL Injection Protection
Prevent attackers from injecting scripts or SQL commands.

🧠 Example:

Don’t trust user input.

Use ORM (like JPA), never build SQL manually.

💡 Use:

java
Copy
Edit
// BAD ❌
"SELECT * FROM users WHERE name = '" + name + "'"

// GOOD ✅
@Query("SELECT u FROM User u WHERE u.name = :name")
💡 Summary Table
Concept	Description	Common Use
JWT	Token-based login	APIs, mobile apps
OAuth 2.0	Login via another provider	Google login
OpenID	OAuth + identity	Get user profile
SAML	SSO in big orgs	Enterprise apps
HTTPS	Encrypt data	Always use
Spring Security	Auth + access control	Java/Spring apps
BCrypt	Hash passwords	User registration
CORS	Control browser access	Frontend ↔ Backend
CSRF	Prevent forced requests	Web forms
Session	Track login server-side	Web apps
RBAC	Access via roles	Admin/User separation
Security Headers	Browser safety	All web apps
SQL Injection/XSS	Input protection	All apps
