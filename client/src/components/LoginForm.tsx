


import React from "react";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { userApi } from "../redux/extraReducers/userApi";
import { ShowPasswordBottom } from "./ShowPasswordBottom";

export function LoginForm() {
  const userstate = useAppSelector(state => state.user);
  const dispatch = useAppDispatch();
  const [show, setShow] = React.useState(false);
  const [data, setData] = React.useState<LoginDto>({ username: '', password: '' })
  const submit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    dispatch(userApi.login(data));
  }
  return (
    <form className="login" onSubmit={submit}>
      <h2>Login</h2>
      <label htmlFor="usernamelogin">Username</label>
      <input
        type="text"
        id="usernamelogin"
        onChange={e => setData({ ...data, username: e.target.value })}
        value={data.username}
      />
      <label htmlFor="passwordlogin">Password</label>
      <input
        type={show ? 'text' : 'password'}
        id="passwordlogin"
        onChange={e => setData({ ...data, password: e.target.value })}
        value={data.password}
      />
      <ShowPasswordBottom onClick={()=> setShow( s => !s)} state={show} className='showpassword'/>
      <button type='submit' className="botton_forms">Entrar</button>
      {userstate.message.trim()?<p>{userstate.message}</p>:null}
    </form>
  );
}
