
import '../styles/login.scss';
import { LoginForm } from "../components/LoginForm";
import { RegisterForm } from "../components/RegisterForm";
import { CSSProperties, useState } from 'react';
import { useAppDispatch } from '../redux/hook';
import { userActions } from '../redux/slices/userSlice';

export function Login() {
    const [styles, setSyles] = useState<CSSProperties>({marginLeft:'0'});
    const dispatch = useAppDispatch();
    const changeOption = (value:string) =>{
        setSyles({...styles, marginLeft:value});
        dispatch(userActions.writeMessage({message:''}));
    }
    return (
        <div className="area_login">
            <div className="area_buttons">
                <button className='' onClick={() => changeOption('0')}>Login</button>
                <button className='' onClick={() => changeOption('-100%')}>Register</button>
            </div>
            <div className="forms_data" style={styles}>
                <LoginForm />
                <RegisterForm />
            </div>
        </div>
    );
}
