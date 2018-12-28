# Best Practices

## Richardson Maturity Model

The Richardson model defines three levels to evaluate how much a application is RESTful.

#### Level 0

Expose SOAP web services in rest style:

- http://server/getResource
- http://server/postResource
- http://server/deleteResource
- http://server/doAction

Expose urls as actions.

#### Level 1

Expose resources with proper uri:

- http://server/resources
- http://server/resources/{id}

This is still a improper use of HTTP methods.

#### Level 2

Level 1 + HTTP methods:

In this level are used the previous URIs with proper HTTP requests.

#### Level 3

Level 2 + HATEOAS

In this case the next possible actions are exposed with the responses.

---

## Best Practices

#### Consumer First

Evaluate who will use the application and with which platforms.

Create a proper documentation.

#### Make Best Use Of HTTP

Use and use correctly the HTTP methods like: GET, POST, PUT, DELETE.

Ensure to send proper response status back:
- 200 SUCCESS
- 201 CREATED
- 400 BAD REQUEST
- 401 UNAUTHORIZED
- 404 NOT FOUND
- 500 SERVER ERROR

#### No Secure Info In URIs

Do not expose reserved data in the URIs.

#### Use Plurals

Use `/resources` and `/resources/{1}` rather `/resource` and `/resource/1`

#### Use Nouns For Resources

Use nouns like `users` not `get-users`.

If a verb is needed use it in a consistent approach like `/users/search` or `/posts/{id}/like`.
