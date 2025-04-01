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

---

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

---

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

No Content

#### Response

No Content

---

### Update user info

#### Path

```http
POST /user/profile
```
#### Headers

```json
{
  "Authorization": "Bearer {token}",
  "Content-Type": "application/json"
}
```

#### Body

```json
{
  "description": "string",
  "born": "string"
}
```
#### Response

```json
{
  "username": "string",
  "fullname": "string",
  "description": "string",
  "urlImage": "string",
  "born": "string",
  "createCount": "string"
}
```

---

### Update profile picture

#### Path

```http
POST /user/imageprofile
```

#### Headers

```json
{
  "Authorization": "Bearer {token}"
}
```

#### Body

This request uses form-data or multipart form instead of JSON.

| Name  | Value                            |
|-------|----------------------------------|
| image | file.{image format(jpg, png...)} |

#### Response

```json
{
  "filename": "string",
  "urlImage": "string"
}
```

---

### Create Follow

#### Path

```http
POST /friend/{username}
```

#### Headers

```json
{
  "Authorization": "Bearer {token}"
}
```

#### Body

No content

#### Response

```json
{
	"followings": "number",
	"followers": "number"
}
```
---

### Count followers and followings user main

#### Path

```http
GET /friend/mainfollows
```

#### Headers

```json
{
  "Authorization": "Bearer {token}"
}
```

#### Response

```json
{
  "followings": "number",
  "followers": "number"
}

```

---

### Count followers and followings user

#### Path

```http
GET /friend/followsfriend/{username}
```

#### Headers

```json
{
  "Authorization": "Bearer {token}"
}
```

#### Response

```json
{
  "followings": "number",
  "followers": "number"
}

```

---

### Find Followers or Followings

#### Path

```http
GET /friend/followings/{username}
```

```http
GET /friend/followers/{username}
```

#### Headers

```json
{
  "Authorization": "Bearer {token}"
}
```

#### Response

```json
[
	{
		"username": "string",
		"fullname": "string",
		"urlImage": "string"
	}
]
```

---

### Check if user main follows other user

#### Path

```http
GET /friend/viewfollow/{username}
```

#### Headers

```json
{
  "Authorization": "Bearer {token}"
}
```

#### Response

```json
{
	"viewFollow": "boolean"
}
```
