# Client Social media

These files are the frontend part.

## Technologies

- Languages: Typescript, Sass
- Tool development: [Vite](https://vite.dev/ "Vite"), NodeJS
- Libraries: React, React Router Dom, [Redux Toolkit](https://redux-toolkit.js.org/ "Redux Toolkit")

## Instalation

- Clone or download this repository

In the frontend files write the following commands:

`npm installl`

`npm run dev` - Starts the application in development mode.
`npm run build` - Generates the static files for production.

## Paths and Screenshots

The paths was established by React Router Dom

### /Login

Users can log in or register through this path.

[![Login](../screenshots/Login.PNG "title")](../screenshots/Login.PNG "title")
[![Register](../screenshots/register.PNG)](../screenshots/register.PNG "Register")

---

### /home and /followsimage

These paths share the same design, however `/home` displays whatever images while `/followlist` displays images that user is following.

[![Home1](../screenshots/home1.PNG)](../screenshots/home1.PNG "Home1")
[![Home2](../screenshots/home2.PNG)](../screenshots/home2.PNG "Home2")

---

### /oneimage

This path displays a complete image along with comments.

[![One image](../screenshots/one_image1.PNG)](../screenshots/one_image1.PNG "One image")
[![One image](../screenshots/one_image2.PNG)](../screenshots/one_image2.PNG "One image")

---

### /perfil

This path displays perfil design.

[![fprefil](../screenshots/perfil.PNG)](../screenshots/perfil.PNG "prefil")

---

### /friend

This path displays perfil design of other user.

[![friend prefil](../screenshots/perfil_friend.PNG)](../screenshots/perfil_friend.PNG "friend prefil")

### /followlist

This path displays two types of follows list: Followings or followers using a query `mode={followers|followings}`.

[![Follow](../screenshots/followers_list.PNG)](../screenshots/followers_list.PNG "friend prefil")

