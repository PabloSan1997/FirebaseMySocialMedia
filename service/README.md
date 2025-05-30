# Social media service

## How the Service Works

This service requires an authentication and authorization request except for login and register.

When a user logs in or registers, a JWT is generated and stored in a database table along with its creation date and session status set to 'true.' If the user logs out using their JWT, the status is updated to 'false,' and the timestamp of this change is recorded in an update date column.

All users have the "USER" role, which grants access to perform all main requests, including:

- Updating their own user info
- Updating their picture profile
- Posting new image with a description
- Liking a photo
- Commenting a photo
- Following another user
- Viewing images of the users their follow
- Viewiwng other users info and images
- Checking if a logged-in user follows other user.
- Deleting an image or comment.


## Technologies

- Languages: Java
- Framework: Spring boot
- Libraries: Jsonwebtoken, Security, Spring web, Validation, JPA.
- Database: Postgresql, [Firebase](https://firebase.google.com/?utm_source=google&utm_medium=cpc&utm_campaign=latam-MX-all-es-dr-SKWS-all-all-trial-b-dr-1710136-LUAC0020222&utm_content=text-ad-none-any-DEV_c-CRE_654757056716-ADGP_Hybrid%20%7C%20SKWS%20-%20BRO%20%7C%20Txt_Compute-Firebase-KWID_43700076081761274-kwd-308670941208&utm_term=KW_firebase-ST_Firebase&gad_source=1&gclid=EAIaIQobChMI0vmIuuO3jAMVvydECB3txSr_EAAYASAAEgKKTfD_BwE&gclsrc=aw.ds&hl=es-419 "Firebase") storage.

## Instalation and configuration

Clone or download this repository

In this section explains how to configure service part app of this social media.

This service use two different profiles in `application.properties`: `application-dev.properties` and `application-prod.properties`.

`application-dev.properties` is for dev mode.

`application-prod.properties` is for production using docker.

An application profile contains this information:

```
server.port=${PORT}

spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=${URL_DB}
spring.datasource.username=${USERNAME}
spring.datasource.password=${PASSWORD}
spring.jpa.show-sql=false


bucket.name=${BUCKET_NAME}
folder.name=${FOLDER_NAME}

jwt.secret.key=${SECRET_KEY}
```

The variables that belong to the JPA configuration use postgrase driver.

`bucket.name` and `folder.name` are retrieved from Firebase Storage.

`jwt.secret.key` is to sign a jsonwebtoken.

Finally, you must generate a new `private key` from Firebase, the generated JSON is placed in the `resources` folder and renamed to `ejemplos-keys.json`.

## Tables and relations

[![diagram](../diagrams/table.png "diagram")](../diagrams/table.png "diagram")

## Http Requests

Most requests are json except for the update image.

All request have the same `/api` base, for example `/api/user/login`,
however we are going to omit it to explain the requests.

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
GET /friend/followings/{username}?size={number}&page={number}
```

```http
GET /friend/followers/{username}?size={number}&page={number}
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
  "Authorization": "Bearer {token}"
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

---

### Delete image

#### Path

```http
DELETE /image/{idImage}
```

#### Headers

```json
{
  "Authorization": "Bearer {token}"
}
```

#### Response
No Content (204)

---

### Delete Comment

#### Path

```http
DELETE /interaction/comment/{idComment}
```

#### Headers

```json
{
  "Authorization": "Bearer {token}"
}
```

#### Body
No Content (204)

---

### Find images

#### Path

```http
GET /image?page={number}&size={number}
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
]
```

---

### Find Followings users images

#### Path

```http
GET /image/following?page={number}&size={number}
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
]
```

---

### Find images by username

#### Path

```http
GET /image/user/{username}?page={number}&size={number}
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
]
```

