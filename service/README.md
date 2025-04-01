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

This request uses form-data or multipart/form-data instead of JSON for file uploads.

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

---

### Find user info header

#### Path

```http
GET /user/headeruser
```

#### Headers

```json
{
  "Authorization": "Bearer {token}"
}
```

#### Body

```json
{
  "username": "string",
  "fullname": "sring",
  "urlImage": "string"
}
```

---

### Get information about the main user and another user

#### Path

```http
GET /user/userinfo
```

```http
GET /friend/infouser/{username}
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
	"username": "string",
	"fullname": "string",
	"description": "string",
	"urlImage": "string",
	"born": "string",
	"createCount": "string"
}
```

---

### Add new image post

#### Path

```http
POST /image
```

#### Headers

```json
{
  "Authorization": "Bearer {token}"
}
```

#### Body

This request uses form-data or multipart/form-data instead of JSON for file uploads.

| Name  | Value|
|-------|------|
| image | file.{image format(jpg, png...)}|
| description | string |

#### Response

```json
{
  "id": "number",
  "urlImage": "string",
  "description": "string",
  "likes": "number",
  "comments": "number",
  "createAt": "string",
  "userLike": "boolean",
  "user": {
    "username": "string",
    "fullname": "string",
    "urlImage": "string"
  }
}
```

---

### Add new comment

#### Path

```http
POST /interaction/comment/{idImage}
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
  "comment":"string"
}
```

#### Response

```json
{
  "id": "number",
  "comment": "string",
  "createAt": "string",
  "user": {
    "username": "string",
    "fullname": "string",
    "urlImage": "string"
  }
}
```

---

### Generate like

#### Path

```http
POST /interaction/like/{idImage}
```

#### Headers

```json
{
  "Authorization": "Bearer {token}",
}
```

#### Body

No Content

#### Response

```json
{
  "idImage": "number",
  "userLike": "boolean"
}
```
