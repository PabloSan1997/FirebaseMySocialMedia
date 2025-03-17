


import React from "react";
import { useAppDispatch, useAppSelector } from "../redux/hook";
import { userActions } from "../redux/slices/userSlice";
import { userApi } from "../redux/extraReducers/userApi";

interface RegisterInputs extends RegisterDto{
    repeatpassword:string;
}

const initialStateInput:RegisterInputs = {
    repeatpassword: "",
    username: "",
    password: "",
    fullname: ""
};

export function RegisterForm() {
    const [show, setShow]=React.useState(false);
    const userstate = useAppSelector(state => state.user);
    const [inputRegister, setInputRegister] = React.useState<RegisterInputs>(initialStateInput);
    const dispatch = useAppDispatch();

    const submit = (e:React.FormEvent<HTMLFormElement>) =>{
        e.preventDefault();
        if(inputRegister.password !== inputRegister.repeatpassword){
            dispatch(userActions.writeMessage({message:'Las contraseñas no coinciden'}));
        }else{
            const inputSubmit:RegisterDto = {
                username: inputRegister.username,
                password: inputRegister.password,
                fullname: inputRegister.fullname
            }
            dispatch(userApi.register(inputSubmit));
        }
    }
    return (
        <form className="login register" onSubmit={submit}>
            <h2>Register</h2>
            <label htmlFor="usernameregister">Username</label>
            <input
                type="text"
                id="usernameregister"
                value={inputRegister.username}
                onChange={e => setInputRegister({...inputRegister, username:e.target.value})}
            />
             <label htmlFor="fullnameregister">Full name</label>
            <input
                type='text'
                id="fullnameregister"
                value={inputRegister.fullname}
                onChange={e => setInputRegister({...inputRegister, fullname:e.target.value})}
            />
            <label htmlFor="passwordregister">Password</label>
            <input
                type={show ? 'text' : 'password'}
                id="passwordregister"
                value={inputRegister.password}
                onChange={e => setInputRegister({...inputRegister, password:e.target.value})}
            />
            <label htmlFor="repeatregister">Password</label>
            <input
                type={show ? 'text' : 'password'}
                id="repeatregister"
                value={inputRegister.repeatpassword}
                onChange={e => setInputRegister({...inputRegister, repeatpassword:e.target.value})}
            />
            <button type='button' onClick={() => setShow(view => !view)}>mostrar</button>
            <button type='submit'>Entrar</button>
            {userstate.message.trim() ? <p>{userstate.message}</p> : null}
        </form>
    );
}
