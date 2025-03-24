/* eslint-disable react-hooks/exhaustive-deps */

import { XCircleIcon } from '@heroicons/react/24/solid';
import { useAppDispatch, useAppSelector } from '../redux/hook';
import '../styles/formsuserinfo.scss';
import { userActions } from '../redux/slices/userSlice';
import React from 'react';
import { userApi } from '../redux/extraReducers/userApi';


export function EdtiProfileForms() {
    const userstate = useAppSelector(state => state.user);
    const dispatch = useAppDispatch();
    const [saveuserinfo, setUserinfo] = React.useState<SaveUserInfo>({ born: '', description: '' });
    const [imageFile, setImageFile] = React.useState<File | null>();
    const [preUrl, setPreUrl] = React.useState('');
    const addFile = (e: React.ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files ? e.target.files[0] : null;
        setImageFile(file);
    }

    React.useEffect(() => {
        if (imageFile != null) {
            const reader = new FileReader();
            reader.onload = (e) => {
                const u = e.target?.result ? e.target.result : '';
                setPreUrl(u.toString());
            }
            reader.readAsDataURL(imageFile);
        } else {
            setPreUrl('');
        }
    }, [imageFile]);


    React.useEffect(() => {
        if (userstate.userInfo.born.trim())
            setUserinfo({ born: new Date(userstate.userInfo.born).toISOString().split('T')[0], description: userstate.userInfo.description });
    }, [userstate.showProfileForm]);
    const closeForm = () => {
        dispatch(userActions.setProdileForm({ showProfileForm: false }))
    }

    const updateUserInfo = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const mira: SaveUserInfo = {
            description: saveuserinfo.description,
            born: new Date(saveuserinfo.born).toISOString().replace('00', '23')
        }
        console.log(mira);
        dispatch(userApi.updateUserInfo({ token: userstate.token, data: mira }));
    }

    const updateUserImage = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const data = new FormData();
        if (imageFile != null) {
            data.append('image', imageFile)
            dispatch(userApi.updateUserPicture({ token: userstate.token, data }));
        }
    }

    if (userstate.showProfileForm)
        return (
            <div className="area_forms_userinfo">
                <XCircleIcon className='close_button' onClick={closeForm} />
                <div className="forms_infousuario">
                    <form className='forms' onSubmit={updateUserInfo}>
                        <h3>Editar informacion</h3>
                        <label>Descripcion</label>
                        <textarea
                            id="descriptionupdateinfo"
                            className='entrada'
                            onChange={e => setUserinfo({ ...saveuserinfo, description: e.target.value })}
                            value={saveuserinfo.description}
                        ></textarea>
                        <label>Fecha de naciomiento</label>
                        <input
                            type='date'
                            className='entrada'
                            onChange={e => setUserinfo({ ...saveuserinfo, born: e.target.value })}
                            value={saveuserinfo.born}
                        />
                        <button>Acutalizar</button>
                    </form>
                    <form className='forms' onSubmit={updateUserImage}>
                        <h3>Nueva foto de perfil</h3>
                        <input
                            type="file"
                            accept='image/*'
                            className='entrada file'
                            onChange={addFile} />
                        {preUrl && (<img className='preurl' src={preUrl} />)}
                        <button>Acutalizar</button>
                    </form>
                </div>
            </div>
        );
    return null;
}
