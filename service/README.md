# Social media service



## Http Requests

Most requests are json except for the update image.

All request have the same `/api` base, for example `/api/user/login`,
however we are going to omit it to explain the requests

### Register new user

#### Path

```http
POST /user/register
```

#### Headers

```json
{
  "Content-Type": "application/json"
}
```

#### Body

```json
{
  "username": "string",
  "password": "string",
  "fullname": "string"
}
```
#### Response

```json
{
  "username": "string",
  "token": "string"
}
```


### Login

#### Path

```http
POST /user/login
```

#### Headers

```json
{
  "Content-Type": "application/json"
}
```

#### Body

```json
{
  "username": "string",
  "password": "string"
}
```
#### Response

```json
{
  "username": "string",
  "token": "string"
}
```

### Logout

#### Path

```http
POST /user/logout
```

#### Headers

```json
{
  "Authorization": "Bearer {token}"
}
```

#### Body

No Content (204)

